package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Map;

import com.movie.VO.MemberVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.AdminView;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.ListView;
import com.movie.view.SignUpView;

public class HomeController implements ActionListener,FocusListener {

	HomeView hv;
	ChoiceView cv;
	SignUpView sUv;
	Map<String, String> movieTmp;
	ListView lv;
	
	AdminView av;
	
	private boolean sign = false, idck;
	String name;
	
	public HomeController() {
		
	}
	
	public HomeController(MainController main) {
		this.hv = main.hv;
		this.cv = main.cv;
		this.sUv = main.sUv;
		this.movieTmp = main.movieTmp;
		this.lv = main.lv;
		
		this.av = main.av;
		
		String[][] str = new ReservationDAO().movieSearch();
		for (int i = 0; i < str.length; i++) {
			hv.movieBox.add(hv.addMoiveBox(str[i]));
			int s = i;
			hv.movieButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(sign) {
						System.out.println(str[s][1]);// 액션 이벤트
					}else {
						System.out.println("로그인 하세요");
					}
				}
			});
		}
		
		hv.signUpButton.setFocusable(true);
		eventUp();
		hv.setVisible(true);		
	}
	
	public void eventUp() {
		hv.resButton.addActionListener(this);
		hv.readResButton.addActionListener(this);
		hv.signInButton.addActionListener(this);
		hv.signUpButton.addActionListener(this);
		hv.idTextField.addFocusListener(this);
		hv.pwdTextField.addFocusListener(this);
		hv.signOutButton.addActionListener(this);
		
		sUv.signUpButton.addActionListener(this);
		sUv.cancelButton.addActionListener(this);
		sUv.checkIdButton.addActionListener(this);
		
		lv.rescancelButton.addActionListener(this);
		lv.backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lv.setVisible(false);
				hv.setVisible(true);
			}
		});
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == hv.idTextField) {
			hv.idTextField.setText("");
		}else {
			hv.pwdTextField.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == hv.idTextField) {
			if(hv.idTextField.getText().trim().equals("")) {
				hv.idTextField.setText("아이디");
			}
		}else {
			if(new String(hv.pwdTextField.getPassword()).trim().equals("")) {
				hv.pwdTextField.setText("비밀번호");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		MemberVO vo;
		if(ob == hv.signInButton) {
			vo = new ReservationDAO().loginDAO(hv.idTextField.getText(),new String(hv.pwdTextField.getPassword()));
			if(vo==null) {
				hv.showMsg("아이디 혹은 비밀번호를 확인해주세요");
			}else {
				hv.idTextField.setText("아이디");
				hv.pwdTextField.setText("비밀번호");
				if(vo.getMemberNum()==1) {
					hv.setVisible(false);
					av.setVisible(true);
					return;
				}
				hv.signIn(vo.getName());
				movieTmp.put("id", vo.getId());
				movieTmp.put("memberNum", (vo.getMemberNum()+""));
				sign = true;
			}
			
		}else if(ob == hv.signUpButton) {
			hv.setVisible(false);
			sUv.init();
			idck=false;
			sUv.setVisible(true);
		}else if(ob== sUv.cancelButton) {
			sUv.setVisible(false);
			hv.setVisible(true);
		}else if(ob == sUv.signUpButton) {
			vo = new MemberVO();
			vo.setId(sUv.idTf.getText());
			vo.setPwd(new String(sUv.pwdTf.getPassword()));
			vo.setName(sUv.nameTf.getText());
			vo.setPhone(sUv.phone1Tf+"-"+sUv.phone2Tf+"-"+
					sUv.phone3Tf);
			if(!idck) {
				sUv.showMsg("아이디 중복확인을 해주세요");
				sUv.checkIdButton.requestFocus();
				return;
			}
			if(new ReservationDAO().signUp(vo)) {
				sUv.showMsg("환영합니다");
				
				sUv.setVisible(false);
				hv.setVisible(true);
			}else {
				sUv.showMsg("회원가입에 실패했습니다.");
			}
		}else if(ob == sUv.checkIdButton) {
			if(new ReservationDAO().checkID(sUv.idTf.getText())==1) {
				sUv.showMsg("사용 가능한 아이디입니다.");
				idck = true;
			}else {
				System.out.println(new ReservationDAO().checkID(sUv.idTf.getText()));
				sUv.showMsg("사용할 수 없는 아이디입니다.");
				sUv.idTf.setText("");
				sUv.idTf.requestFocus();
			}
		}else if(ob == hv.signOutButton) {
			if(hv.showCon("로그아웃하시겠습니까?")==0) {
				movieTmp.clear();
				sign=false;
				hv.signOut();
			}
		}else if(ob == hv.resButton) {
			hv.setVisible(false);
			cv.setVisible(true);
		}else if(ob == hv.readResButton) {
	         String loginId = movieTmp.get("id"); //테스트용 id세팅입니다. 수정해야돼요
	         lv.displayTable(new ReservationDAO().findReservationInfo(loginId));
	         hv.setVisible(false);
	         lv.setVisible(true);
	         //setvisible 추가해야돼요
	      }else if(ob == lv.rescancelButton) {
	         String resNum = lv.showInput("삭제할 예매번호를 입력해주세요");
	         if(!resNum.matches("[0-9]+")) {
	            lv.showMsg("다시 입력해주세요");
	            return;
	         }
	         if(!new ReservationDAO().deleteReservation(Integer.parseInt(resNum))) { 
	            lv.showMsg("다시 입력해주세요");
	            return;
	         }
	         
	         lv.showMsg("삭제에 성공하였습니다");
	        String loginId = "hana"; //테스트용 id세팅입니다. 수정해야돼요
	         lv.displayTable(new ReservationDAO().findReservationInfo(loginId));
	      }
	}
	
	
}
