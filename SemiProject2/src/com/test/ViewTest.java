package com.test;

 
//수정함 mon    
import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ViewTest extends JFrame {

	private JLabel encorePay, money10000;
	private JLabel[] label;

	public TextField tfTickets, tftotalMoney, tfTicketNumber, tfMovieTitle, tfMovieDate, tfMovieTime, tfMovieSeat;
	public JButton btCheckPassword, bt_next, bt_prev;
	

	public ViewTest() {
		setLayout(null);

		// label 생성
		encorePay = new JLabel("엔코아페이");
			encorePay.setFont(new Font("고딕", Font.BOLD, 80));
		money10000 = new JLabel("1만원");
			money10000.setFont(new Font("고딕", Font.BOLD, 50));

		String[] labelTitle = { "예매번호", "영화제목", "예매날짜", "예매시간", "예매좌석" };
		label = new JLabel[labelTitle.length];
		for (int i = 0; i < labelTitle.length; i++) {
			label[i] = new JLabel(labelTitle[i] + ":");
			label[i].setFont(new Font("고딕", Font.BOLD, 50));
		}

		// label 세팅
		add(encorePay);
		encorePay.setBounds(500, 50, 600, 130);
		add(money10000);
		money10000.setBounds(500, 180, 200, 100);
		int y = 300;
		for (int i = 0; i < label.length; i++) {
			label[i].setBounds(500, y, 300, 100);
			add(label[i]);
			y += 120;
		}

		// tf생성
		tfTickets = new TextField();
		tftotalMoney = new TextField();
		tfTicketNumber = new TextField();
		tfMovieTitle = new TextField();
		tfMovieDate = new TextField();
		tfMovieTime = new TextField();
		tfMovieSeat = new TextField();

		// tf 세팅
		add(tfTickets);
		tfTickets.setBounds(700, 180, 100, 100);
		add(tftotalMoney);
		tftotalMoney.setBounds(830, 180, 500, 100);

		TextField[] tf = { tfTicketNumber, tfMovieTitle, tfMovieDate, tfMovieTime, tfMovieSeat };
		int tfy = 300;
		for (int i = 0; i < tf.length; i++) {
			add(tf[i]);
			tf[i].setBounds(800, tfy, 600, 100);
			tfy += 120;
		}

		btCheckPassword = new JButton("결제하기");
		add(btCheckPassword);
		btCheckPassword.setBounds(1500, 500, 200, 80);
		
		bt_prev = new JButton("이전");
		add(bt_prev);
		bt_prev.setBounds(30, 876, 200, 80);
		
		bt_next = new JButton("다음");
		add(bt_next);
		bt_next.setBounds(1690, 876, 200, 80);

		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	
	public static void main(String[] args) {
		new ViewTest();
	} 

}
//		reservationNumber = new JLabel("예매번호");
//		movieTitle = new JLabel("영화제목");
//		movieDate = new JLabel("예매일자");
//		movieTime = new JLabel("예매시간");
//		movieSeat = new JLabel("예매좌석");