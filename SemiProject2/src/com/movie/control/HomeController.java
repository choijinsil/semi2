package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Map;

import com.movie.VO.MemberVO;
import com.movie.VO.MovieVO;
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
		
		ArrayList<MovieVO> list = new ReservationDAO().movieSearch();
		hv.movieBox.removeAll();
		for (int i = 0; i < list.size(); i++) {
			hv.movieBox.add(hv.addMoiveBox(list.get(i)));
			int s = i;
			hv.movieButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(!(movieTmp.get("id")==null)) {
						cv.displayTable(new ReservationDAO().findMovieTitle());
						hv.setVisible(false);
						cv.setVisible(true);
					}else {
						hv.showMsg("예매를 하시려면 로그인 하세요");
					}
				}
			});
		}

		eventUp();
		hv.setVisible(true);
		hv.signUpButton.requestFocus();
	}
	
	public void eventUp() {
		hv.resButton.addActionListener(this);
		hv.readResButton.addActionListener(this);
		hv.signInButton.addActionListener(this);
		hv.signUpButton.addActionListener(this);
		hv.idTextField.addFocusListener(this);
		hv.pwdTextField.addFocusListener(this);
		hv.signOutButton.addActionListener(this);
		hv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		sUv.signUpButton.addActionListener(this);
		sUv.cancelButton.addActionListener(this);
		sUv.checkIdButton.addActionListener(this);
		sUv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sUv.setVisible(false);
				hv.setVisible(true);
				
			}
		});
		sUv.phone1Tf.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyReleased(KeyEvent e) {
				if(sUv.phone1Tf.getText().length()==3) {
					sUv.phone2Tf.requestFocus();
				}
			}
		});
		
		sUv.phone2Tf.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyReleased(KeyEvent e) {
				if(sUv.phone2Tf.getText().length()==4) {
					sUv.phone3Tf.requestFocus();
				}
			}
		});
		
		sUv.phone3Tf.addKeyListener(new KeyAdapter() {			
			@Override
			public void keyReleased(KeyEvent e) {
				if(sUv.phone3Tf.getText().length()==4) {
					sUv.signUpButton.requestFocus();
				}
			}
		});
		
		lv.rescancelButton.addActionListener(this);
		lv.backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lv.setVisible(false);
				hv.setVisible(true);
			}
		});
		lv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
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
				movieTmp.put("totalCnt",(vo.getTotalCnt()+""));
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
			vo.setPhone(sUv.phone1Tf.getText()+"-"+sUv.phone2Tf.getText()+"-"+
					sUv.phone3Tf.getText());//.getText()
			if(!idck) {
				sUv.showMsg("아이디 중복확인을 해주세요");
				sUv.checkIdButton.requestFocus();
				return;
			}else if(new String(sUv.pwdTf.getPassword()).equals("")||
					!new String(sUv.pwdTf.getPassword()).equals(new String(sUv.pwd2Tf.getPassword()))
					||!new String(sUv.pwdTf.getPassword()).matches("[a-zA-Z\\d]{4,8}")) {
				sUv.showMsg("비밀번호를 확인 해주세요");
				sUv.pwdTf.setText("");
				sUv.pwd2Tf.setText("");
				sUv.pwdTf.requestFocus();
				return;
			}else if(sUv.nameTf.getText().trim().equals("")) {
				sUv.showMsg("이름을 입력해주세요");
				sUv.nameTf.requestFocus();
				return;
			}else if(!sUv.phone1Tf.getText().matches("[\\d]{3}")
					||!sUv.phone2Tf.getText().matches("[\\d]{4}")
					||!sUv.phone3Tf.getText().matches("[\\d]{4}")) {
				sUv.showMsg("올바른 전화번호를 입력해주세요");
				sUv.phone1Tf.setText("");
				sUv.phone2Tf.setText("");
				sUv.phone3Tf.setText("");
				sUv.phone1Tf.requestFocus();
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
			if(!sUv.idTf.getText().matches("[a-zA-Z][a-zA-Z\\d]{3,6}")) {
				sUv.showMsg("형식에 맞지 않습니다");
				sUv.idTf.requestFocus();
				return;
			}
			if(new ReservationDAO().checkID(sUv.idTf.getText())==1) {
				sUv.showMsg("사용 가능한 아이디입니다.");
				idck = true;
			}else {
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
			 cv.displayTable(new ReservationDAO().findMovieTitle());
			hv.setVisible(false);
			cv.setVisible(true);
			
		}else if(ob == hv.readResButton) {
	         String loginId = movieTmp.get("id"); 
	         lv.displayTable(new ReservationDAO().findReservationInfo(loginId));
	         hv.setVisible(false);
	         lv.setVisible(true);
	         
	      }else if(ob == lv.rescancelButton) {
	         String resNum = lv.showInput("삭제할 예매번호를 입력해주세요");
	         if(resNum==null) {
	        	 return;
	         }
	         if(!resNum.matches("[0-9]+")) {
	            lv.showMsg("다시 입력해주세요.");
	            return;
	         }
	         if(!new ReservationDAO().deleteReservation(Integer.parseInt(resNum))) { 
	            lv.showMsg("다시 입력해주세요.");
	            return;
	         }
	         lv.showMsg("삭제에 성공하였습니다.");
	         lv.displayTable(new ReservationDAO().findReservationInfo(movieTmp.get("id")));
	      }
	}
	
	
}
