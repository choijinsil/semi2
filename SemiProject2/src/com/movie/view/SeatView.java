package com.movie.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.movie.VO.SeatVO;



public class SeatView extends JFrame{
	public JButton seat1, seat2, seat3, seat4, seat5, btNext, btPrev, screen;
	JPanel seatP1, seatP2;
	public Vector<JButton> jb;
	
	public SeatView() {
		Font f = new Font("Dialog",Font.BOLD,40);
		screen = new JButton("SCREEN");
		screen.setFont(f);
		screen.setEnabled(false);
		
		seat1 = new JButton("A1");
		seat1.setBackground(Color.green);
		seat1.setFont(f);
		seat2 = new JButton("A2");
		seat2.setBackground(Color.green);
		seat2.setFont(f);
		seat3 = new JButton("A3");
		seat3.setBackground(Color.green);
		seat3.setFont(f);
		seat4 = new JButton("A4");
		seat4.setBackground(Color.green);
		seat4.setFont(f);
		seat5 = new JButton("A5");
		seat5.setBackground(Color.green);
		seat5.setFont(f);
		
		btNext = new JButton("다음");
		btPrev = new JButton("이전");
		
		btNext.setBounds(1690, 876, 200, 80);
		btNext.setFont(f);
		btPrev.setBounds(30, 876, 200, 80);
		btPrev.setFont(f);
		
		seatP1 = new JPanel();
		seatP1.setLayout(new GridLayout(1,1));
//		seatP1.add(new JLabel());
		seatP1.add(screen);
//		seatP1.add(new JLabel());
		seatP1.setBounds(360, 10, 1200, 300);
		
		seatP2 = new JPanel();
		seatP2.setLayout(new GridLayout(1, 5, 200, 150));
//		seatP2.add(new JLabel());
		seatP2.add(seat1);
		seatP2.add(seat2);
		seatP2.add(seat3);
		seatP2.add(seat4);
		seatP2.add(seat5);
//		seatP2.add(new JLabel());
		seatP2.setBounds(20, 500, 1880, 200);
	
		setLayout(null);
		add(btNext);
		add(btPrev);
		add(seatP2);
		add(seatP1);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jb = new Vector<JButton>();
		jb.add(seat1);
		jb.add(seat2);
		jb.add(seat3);
		jb.add(seat4);
		jb.add(seat5);
	}
	
	
	public void displayState(Vector<SeatVO> list) {//Vector<String> list
		String str;
		for(int i = 0; i < list.size(); i++) {
			String selSeat = list.get(i).getSeatName();
			str = selSeat.replaceAll(" ", "");
			String []str2 = str.split(",");
			for(int j = 0; j < str2.length; j++) {
				for (int k = 0; k < jb.size(); k++) {
					JButton jba = jb.get(k);
					if(str2[j].equals(jba.getText())) {
						jba.setBackground(Color.black);
						jba.setEnabled(false);
					}
				}
			}
		}
	}
	
	public void resetSeat() {
		for (int i = 0; i < jb.size(); i++) {
			jb.get(i).setBackground(Color.GREEN);
			jb.get(i).setEnabled(true);
		}
	}
	
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}// showMsg
	
}
