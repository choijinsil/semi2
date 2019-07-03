package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.movie.dao.ReservationDAO;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class PaymentController {

   PaymentView pv;
   SeatView sv;
   Map<String, String> movieTmp;

   public PaymentController(MainController mc) {
      pv = mc.pv;
      sv = mc.sv;
      movieTmp = mc.movieTmp;
      eventUp();
   }

   private void eventUp() {
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
               //System.out.println("비밀번호 재확인을 위해 입력한 비밀번호"+passWord);//콘솔 체크용
            
            ReservationDAO rd = new ReservationDAO();
            String loginId = movieTmp.get("id");
               //System.out.println("현재 로그인한 아이디"+loginId);//콘솔 체크용
            if(rd.checkPassword(loginId,passWord)!=1) {
                     //if(!password.equals(pass)) {//임시데이터 체크용
               pv.showMsg("비밀번호가 틀렸습니다");
               return;
            }
            if(pv.showConfirm()!=0) {
               return;
            }
               //System.out.println("확인버튼 눌렀어요");//콘솔 체크용
            
            int cnt = Integer.parseInt(movieTmp.get("totalCnt"));   
               //System.out.println("id를 통해 불러온 totalCnt"+cnt);//콘솔 체크용
            cnt+=1;
            String totalCnt = cnt+"";
               //System.out.println("totalCnt에 1더했음"+totalCnt);//콘솔 체크용
            movieTmp.put("totalCnt", totalCnt);
            
            if(!rd.saveReservation(movieTmp)){
               pv.showMsg("결제에 실패하였습니다.\n다시 확인 부탁드립니다.");
            	//System.out.println("결제끝");//콘솔 체크용
            }
            
            pv.showMsg("결제가 완료되었습니다.\n감사합니다.");
            rd.UpdateTotalCnt(movieTmp);
            rd.updateTotalViewer(movieTmp);
            //if(rd.UpdateTotalCnt(movieTmp)) {
               //System.out.println("totalCnt 수정완료");
            //}
            //if(rd.updateTotalViewer(movieTmp)) {
               //System.out.println("totalViewer 수정완료");
            //}
         }
      });

   
   
   }



}