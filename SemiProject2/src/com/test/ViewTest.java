package com.test;

/**
 * 클래스에 대한 설명을 여기에 쓴다.
 * 
 * @author 	sjcnew
 * @since 	2019. 6. 23.
 * @version 1.0
 * @see <pre>
 *  == 개정이력(Modification Information) ==
 *   
 *   수정일			수정자				수정내용
 *  ---------------------------------------------------------------------------------
 *   2019. 6. 23.		sjcnew				최초생성
 * 
 * </pre>
 */

import java.awt.Font;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewTest extends JFrame {
	
	public JLabel encorePay, Money10000, reservationNumber, movieTitle, movieDate, movieTime, movieSeat;
	public TextField tfticketNumber, tftotalMoney, tfMovieTitle, tfMotieDate, tfMovieTime, tfMovieSeat;
	public JButton bCheckPassword, bBefore, bAfter;
	
	public ViewTest() {
		encorePay = new JLabel("엔코아페이");
		Money10000 = new JLabel("1만원");
		reservationNumber = new JLabel("예매번호");
		movieTitle = new JLabel("영화제목");
		movieDate = new JLabel("예매일자");
		movieTime = new JLabel("예매시간");
		movieSeat = new JLabel("예매좌석");
		
		tfticketNumber = new TextField();
		tftotalMoney = new TextField();
		tfMovieTitle = new TextField();
		tfMotieDate = new TextField();
		tfMovieTime = new TextField();
		tfMovieSeat = new TextField();
		
		bCheckPassword = new JButton();
		bBefore = new JButton();
		bAfter = new JButton();
		
		setLayout(null);
		add(encorePay);
		encorePay.setBounds(100,20, 300, 300);
		encorePay.setFont(new Font("맑은고딕", Font.BOLD, 30));
		add(Money10000);
		Money10000.setBounds(400,20, 300, 300);
		add(tfticketNumber);
		add(reservationNumber);
		add(movieTitle);
		add(movieDate);
		add(tfMotieDate);
		add(movieTime);
		add(tfMovieTime);
		add(movieSeat);
		add(tfMovieSeat);
		add(bCheckPassword);
		add(bBefore);
		add(bAfter);
		
		
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
	
	public static void main(String[] args) {
		new ViewTest();
	}
	
}