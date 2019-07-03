package com.movie.view;

import java.awt.Font;
import java.awt.TextField;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class PaymentView extends JFrame {

	private JLabel encorePay, money10000;
	private JLabel[] label;

	public TextField tfTickets, tftotalMoney, tfMovieTitle, tfMovieDate, tfMovieTime, tfMovieSeat;
	public JButton btPayment, bt_prev;

	public PaymentView() {
		setLayout(null);

		// label 생성
		encorePay = new JLabel("엔코아페이");
		encorePay.setFont(new Font("고딕", Font.BOLD, 80));
		money10000 = new JLabel("1만원");
		money10000.setFont(new Font("고딕", Font.BOLD, 50));

		String[] labelTitle = { "영화제목", "상영일자", "상영시간", "좌석이름" }; // "예매번호",
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
		tfMovieTitle = new TextField();
		tfMovieDate = new TextField();
		tfMovieTime = new TextField();
		tfMovieSeat = new TextField();

		// tf 세팅
		add(tfTickets);
		tfTickets.setBounds(700, 180, 100, 100);
		tfTickets.setFont(new Font("Dialog", Font.BOLD, 80));
		add(tftotalMoney);
		tftotalMoney.setBounds(830, 180, 500, 100);
		tftotalMoney.setFont(new Font("Dialog", Font.BOLD, 80));

		TextField[] tf = { tfMovieTitle, tfMovieDate, tfMovieTime, tfMovieSeat }; // tfTicketNumber,
		int tfy = 300;
		for (int i = 0; i < tf.length; i++) {
			add(tf[i]);
			tf[i].setBounds(800, tfy, 600, 100);
			tf[i].setFont(new Font("Dialog", Font.BOLD, 60));
			tfy += 120;
		}

		btPayment = new JButton("결제하기");
		btPayment.setFont(new Font("Dialog", Font.BOLD, 25));
		add(btPayment);
		btPayment.setBounds(1500, 500, 200, 80);

		bt_prev = new JButton("이전");
		bt_prev.setFont(new Font("Dialog", Font.BOLD, 40));
		add(bt_prev);
		bt_prev.setBounds(30, 876, 200, 80);

		tfMovieDate.setEnabled(false);
		tfMovieTime.setEnabled(false);
		tfTickets.setEnabled(false);
		tftotalMoney.setEnabled(false);
		tfMovieSeat.setEnabled(false);
		tfMovieTitle.setEnabled(false);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public String showCheckPassword() {
		JPasswordField pwd = new JPasswordField(10);
		if (JOptionPane.showConfirmDialog(this, pwd, "비밀번호를 입력하세요", JOptionPane.OK_CANCEL_OPTION) != 0)
			return null;
		return new String(pwd.getPassword());
	}

	public String showInput(String msg) {
		return JOptionPane.showInputDialog(this, msg);
	}

	public int showConfirm() {
		return JOptionPane.showConfirmDialog(this, "결제를 진행하시겠습니까?");
	}

	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void init(Map<String, String> m) {
		tfTickets.setText(m.get("quantity"));
		tftotalMoney.setText(Integer.parseInt(m.get("quantity")) * 10000 + "");
		tfMovieTitle.setText(m.get("movieTitle"));
		tfMovieDate.setText(m.get("screenDate"));
		tfMovieTime.setText(m.get("screenTime"));
		tfMovieSeat.setText(m.get("resSeat"));
	}

}