package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.movie.dao.ReservationDAO;
import com.movie.view.HomeView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class PaymentController {

   PaymentView pv;
   SeatView sv;
   HomeView hv;
   Map<String, String> movieTmp;

   public PaymentController(MainController mc) {
      pv = mc.pv;
      sv = mc.sv;
      hv = mc.hv;
      movieTmp = mc.movieTmp;
      eventUp();
   }

   private void eventUp() {
	  pv.addWindowListener(new WindowAdapter() {
		  @Override
		public void windowClosing(WindowEvent e) {
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
            }
            
            pv.showMsg("결제가 완료되었습니다.\n감사합니다.");
            rd.UpdateTotalCnt(movieTmp);
            rd.updateTotalViewer(movieTmp);
            
            pv.setVisible(false);
            hv.setVisible(true);
         }
      });
   }

}