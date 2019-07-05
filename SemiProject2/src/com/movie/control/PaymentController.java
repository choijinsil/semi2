package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.movie.VO.MovieVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class PaymentController {

   PaymentView pv;
   SeatView sv;
   HomeView hv;
   ChoiceView cv;
   Map<String, String> movieTmp;

   public PaymentController(MainController mc) {
      pv = mc.pv;
      sv = mc.sv;
      hv = mc.hv;
      cv = mc.cv;
      movieTmp = mc.movieTmp;
      eventUp();
   }

   private void eventUp() {
	  pv.addWindowListener(new WindowAdapter() {
		  @Override
		public void windowClosing(WindowEvent e) {
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
								cv.cbMovie.setSelectedIndex(s+1);
								hv.setVisible(false);
								cv.setVisible(true);
							}else {
								hv.showMsg("예매를 하시려면 로그인 하세요");
							}
						}
					});
				}
			  pv.setVisible(false);
			  hv.setVisible(true);
		}
	}); 
	   
      pv.bt_prev.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            pv.setVisible(false);
            sv.setVisible(true);
         }
      });
   
      //결제버튼 클릭
      pv.btPayment.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String passWord = pv.showCheckPassword();
            if(passWord==null) {
            	return;
            }
            ReservationDAO rd = new ReservationDAO();
            String loginId = movieTmp.get("id");
            if(rd.checkPassword(loginId,passWord)!=1) {
               pv.showMsg("비밀번호가 틀렸습니다");
               return;
            }
            if(pv.showConfirm()!=0) {
               return;
            }
            
            int cnt = Integer.parseInt(movieTmp.get("totalCnt"));   
            cnt+=1;
            String totalCnt = cnt+"";
            movieTmp.put("totalCnt", totalCnt);
            
            if(!rd.saveReservation(movieTmp)){
               pv.showMsg("결제에 실패하였습니다.\n다시 확인 부탁드립니다.");
               System.out.println(movieTmp.get("scheduleNum"));
               return;
            }
            
            pv.showMsg("결제가 완료되었습니다.\n감사합니다.");
            rd.UpdateTotalCnt(movieTmp);
            rd.updateTotalViewer(movieTmp);
            
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
    						cv.cbMovie.setSelectedIndex(s+1);
    						hv.setVisible(false);
    						cv.setVisible(true);
    					}else {
    						hv.showMsg("예매를 하시려면 로그인 하세요");
    					}
    				}
    			});
    		}
            
            pv.setVisible(false);
            hv.setVisible(true);
         }
      });
   }

}