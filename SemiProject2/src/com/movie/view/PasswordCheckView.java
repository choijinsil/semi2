package com.movie.view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PasswordCheckView extends JFrame {
	
	private JLabel msg;
	public JTextField tfpassword;
	public JButton btcheck, btcancel;
	
	public PasswordCheckView() {
		
		msg = new JLabel("비밀번호를 입력해 주세요");
		add(msg);
		msg.setBounds(80, 50, 500, 100);
		msg.setFont(new Font("고딕",Font.BOLD,30));
		
		tfpassword = new JTextField();
		add(tfpassword);
		tfpassword.setBounds(80, 150, 350, 150);
		tfpassword.setFont(new Font("고딕",Font.BOLD,30));
		
		btcheck = new JButton("확인");
		add(btcheck);
		btcheck.setBounds(550, 100, 200, 100);
		btcheck.setFont(new Font("고딕",Font.BOLD,30));
		
		btcancel = new JButton("취소");
		add(btcancel);
		btcancel.setBounds(550, 250, 200, 100);
		btcancel.setFont(new Font("고딕",Font.BOLD,30));
		
		setLayout(null);
		setBounds(500, 300, 800, 500);
		setVisible(true);
	}
		
}
