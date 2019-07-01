package com.movie.view;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.movie.VO.MovieVO;

public class ListView extends JFrame{
   public JTable table;   
   public JScrollPane scroll_table;
   public DefaultTableModel dtm;
   
   public JButton rescancelButton, backButton;
   
   public ListView() {
      
      Object [][]rowData = new Object[0][5];
      String []columnTitle = {"예매번호","영화제목","상영일자","예매좌석"};
      dtm = new DefaultTableModel(rowData, columnTitle);
      table = new JTable(dtm);
      scroll_table = new JScrollPane(table);
      rescancelButton = new JButton("예매 취소");
      backButton = new JButton("뒤로가기");
      
      scroll_table.setBounds(30,30, 1500, 1000);
      rescancelButton.setBounds(1600, 30, 250, 50);
      backButton.setBounds(1600, 100, 250, 50);
      
      add(scroll_table);
      add(rescancelButton);
      add(backButton);
      
      setLayout(null);
      setExtendedState(JFrame.MAXIMIZED_BOTH);
//      setVisible(true);
   }

   
   public void displayTable(ArrayList<MovieVO> list) {
      dtm.setRowCount(0);
      for (int i = 0; i < list.size(); i++) {
         MovieVO mv = list.get(i);
         Object[] rowData = {mv.getResNum(),mv.getMovieTitle(),mv.getScreenDate(),mv.getResSeat()};
         dtm.addRow(rowData);
      }
   }
   
   public String showInput(String msg) {
      return JOptionPane.showInputDialog(this, msg);
   }
   
   public void showMsg(String msg) {
      JOptionPane.showMessageDialog(this, msg);
   }
}