package com.movie.view;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpView extends JFrame {
	public JPanel panel;
	public JButton signUpButton,cancelButton, checkIdButton;
	public JLabel idLabel, pwdLabel, pwd2Label, nameLabel, phoneLabel,phone2Label,phone3Label;
	public JTextField idTf, nameTf, phone1Tf, phone2Tf, phone3Tf;
	public JPasswordField  pwdTf, pwd2Tf;
	public SignUpView() {
		setLayout(null);
		idLabel = new JLabel("아이디(영어로 시작하는 4~7자리 영어와 숫자)");
		idLabel.setBounds(0, 50, 300, 40);
		pwdLabel = new JLabel("비밀번호(영문,숫자 혼합 4~8자리)");
		pwdLabel.setBounds(0, 150, 390, 40);
		pwd2Label = new JLabel("비밀번호 확인");
		pwd2Label.setBounds(0, 250, 390, 40);
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(0, 350, 390, 40);
		phoneLabel = new JLabel("휴대전화 번호");
		phoneLabel.setBounds(0, 450, 390, 40);
		phone2Label = new JLabel("-");
		phone2Label.setBounds(78, 500, 39, 40);
		phone3Label = new JLabel("-");
		phone3Label.setBounds(234, 500, 39, 40);
		
		idTf = new JTextField();
		idTf.setBounds(0, 100, 390, 40);
		pwdTf = new JPasswordField();
		pwdTf.setBounds(0, 200, 390, 40);
		pwd2Tf = new JPasswordField();
		pwd2Tf.setBounds(0, 300, 390, 40);
		nameTf = new JTextField();
		nameTf.setBounds(0, 400, 390, 40);
		phone1Tf = new JTextField();
		phone1Tf.setBounds(0, 500, 78, 40);
		phone2Tf = new JTextField();
		phone2Tf.setBounds(117, 500, 117, 40);
		phone3Tf = new JTextField();
		phone3Tf.setBounds(273, 500, 117, 40);
		
		signUpButton = new JButton("회원 가입");
		signUpButton.setBounds(0, 600, 390, 60);
		cancelButton = new JButton("취    소");
		cancelButton.setBounds(0, 700, 390, 60);
		checkIdButton = new JButton("중복확인");
		checkIdButton.setBounds(300, 50, 90, 40);
		
		panel = new JPanel();
			panel.setLayout(null);
			panel.setBounds(780, 0, 390, 1000);
			panel.add(idLabel);
			panel.add(idTf);
			panel.add(pwdLabel);
			panel.add(pwdTf);
			panel.add(pwd2Label);
			panel.add(pwd2Tf);
			panel.add(nameLabel);
			panel.add(nameTf);
			panel.add(phoneLabel);
			panel.add(phone2Label);
			panel.add(phone3Label);
			panel.add(phone1Tf);
			panel.add(phone2Tf);
			panel.add(phone3Tf);
			panel.add(signUpButton);
			panel.add(cancelButton);
			panel.add(checkIdButton);
			
		
		add(new JLabel());
		add(panel);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setVisible(true);
	}
	public void init() {
		idTf.setText("");
		pwdTf.setText("");
		pwd2Tf.setText("");
		nameTf.setText("");
		phone1Tf.setText("");
		phone2Tf.setText("");
		phone3Tf.setText("");		
	}
	public void showMsg(String str) {
		JOptionPane.showMessageDialog(this, str);
	}
//	public static void main(String[] args) {
//		new SignUpView();
//	}
	
}
