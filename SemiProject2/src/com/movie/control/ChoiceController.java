
package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import com.movie.VO.MovieVO;
import com.movie.VO.SeatVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.SeatView;

import sun.applet.Main;
import sun.swing.MenuItemCheckIconFactory;

public class ChoiceController{

   ChoiceView cv;
   ReservationDAO rDao;
   HomeView hv;
   SeatView sv;
   int choiceScNum;

   public ChoiceController() {
   }

   public ChoiceController(MainController mv) {

      cv = mv.cv;
      hv = mv.hv;
      sv = mv.sv;
      rDao = new ReservationDAO();

      cv.displayTable(rDao.findMovieTitle());

      cv.addWindowListener(new WindowAdapter() {
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
    						if(!(mv.movieTmp.get("id")==null)) {
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
    		  cv.setVisible(false);
    		  hv.setVisible(true);
    	}
	});
      
      
      
      cv.cbMovie.addActionListener(new ActionListener() { // 영화제목 선택
         @Override
         public void actionPerformed(ActionEvent e) {
        	 	if (cv.cbMovie.getSelectedItem()==null) {
        	 		return;
        	 	}
            cv.selMovie = cv.cbMovie.getSelectedItem().toString(); // 콤보박스값을 selMovie저장
            cv.displayScreenDate(rDao.findScreenDate(cv.selMovie));
            cv.cardLayout.show(cv.cardPanel, cv.selMovie);
            cv.cardLayout2.show(cv.cardPanel2, cv.selMovie);
         }
      });

      cv.dbDate.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            	if(cv.dbDate.getSelectedItem()==null || ((String)cv.dbDate.getSelectedItem()).equals("<<<상영일정>>>")) {
            		return;
            	}
               choiceScNum = rDao.findScheduleNum(cv.selMovie, cv.dbDate.getSelectedItem().toString());
         }
      });

      cv.btNext.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	if(cv.dbDate.getSelectedItem()==null || ((String)cv.dbDate.getSelectedItem()).equals("<<<상영일정>>>")) {
        		cv.showMsg("상영스케줄을 선택해 주세요");
        		return;
        	} 
//        	else if (((String)cv.dbDate.getSelectedItem()).equals("<<< 상영일정 >>>")) {
//        		cv.showMsg("상영스케줄을 선택해 주세요");
//        		return;
//        	}//시간 선택안하고 다음버튼 눌렀을 때 null에러 나서 잡고, 메시지 띄움
        	
            String[] str = cv.dbDate.getSelectedItem().toString().split(" ");
            mv.movieTmp.put("scheduleNum", choiceScNum + ""); // 스케쥴넘버
            mv.movieTmp.put("movieTitle", cv.selMovie); // 영화제목
            mv.movieTmp.put("screenDate", str[0]);
            mv.movieTmp.put("screenTime", str[1]);
            
            sv.resetSeat();
            sv.displayState(new ReservationDAO().seatState(Integer.parseInt(mv.movieTmp.get("scheduleNum"))));
            cv.setVisible(false);
            sv.setVisible(true);
         }
      });

      cv.btPrev.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
        	 ArrayList<MovieVO> list = new ReservationDAO().movieSearch();
     		hv.movieBox.removeAll();
     		for (int i = 0; i < list.size(); i++) {
     			hv.movieBox.add(hv.addMoiveBox(list.get(i)));
     			int s = i;
     			hv.movieButton.addActionListener(new ActionListener() {
     				@Override
     				public void actionPerformed(ActionEvent e) {
     					if(!(mv.movieTmp.get("id")==null)) {
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
            cv.setVisible(false);
            hv.setVisible(true);
            cv.cbMovie.removeAllItems();
         }
      });
   }

}