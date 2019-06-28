package com.movie.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class HomeView extends JFrame {
	public JPanel loginPanel, movieListPanel, guestPanel, memberPanel, resPanel, cardPanel;
	public JLabel idLabel;
	public JButton resButton,readResButton,signOutButton;
	public JButton signInButton, signUpButton;
	public JTextField idTextField;
	public JPasswordField pwdTextField;
	public Box movieBox;
	public JPanel moviePanel;
	public JButton movieButton;
	public JLabel movieNameLabel;
	public JScrollPane mListSPanel;
	public String loginId;
	
	public HomeView() {
		setTitle("일석삼조시네마");
		setLayout(null);
		
		//movieList
		movieListPanel = new JPanel();
		movieListPanel.setLayout(new BoxLayout(movieListPanel, BoxLayout.X_AXIS));
		movieBox = Box.createHorizontalBox();
		movieListPanel.add(movieBox);
		mListSPanel = new JScrollPane(movieListPanel);
		mListSPanel.setBounds(520, 170, 1400, 830);
		add(mListSPanel);
		
		
		//guest
		idTextField = new JTextField("아이디");
		pwdTextField = new JPasswordField("비밀번호");
		signInButton = new JButton("로그인");
		signUpButton = new JButton("회원가입");
		
		guestPanel = new JPanel(new GridLayout(4, 1, 50, 20));
			guestPanel.add(idTextField);
			guestPanel.add(pwdTextField);
			guestPanel.add(signInButton);
			guestPanel.add(signUpButton);
			
		
		loginPanel = new JPanel(new GridLayout(3, 1));
			loginPanel.add(new JLabel());
			loginPanel.add(guestPanel);
		loginPanel.setBounds(10, 0, 500, 1000);
		loginPanel.setBorder(new LineBorder(Color.black));
		add(loginPanel);
		
		
		//member
		idLabel = new JLabel();
			idLabel.setBounds(30, 130, 300, 40);
		resButton = new JButton("예매하기");
			resButton.setBounds(0, 350, 500, 80);
		readResButton = new JButton("예매 조회");
			readResButton.setBounds(0, 510, 500, 80);
		signOutButton = new JButton("로그아웃");
			signOutButton.setBounds(0, 710,500,80);
		
		memberPanel = new JPanel(null);
			memberPanel.add(idLabel);
			memberPanel.add(resButton);
			memberPanel.add(readResButton);
			memberPanel.add(signOutButton);
			memberPanel.setBounds(10, 0, 500, 1000);
			memberPanel.setBorder(new LineBorder(Color.black));
			memberPanel.setVisible(false);
		add(memberPanel);
			
		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		setVisible(true);
	}
	
	public void signIn(String name) {
		idLabel.setText(name);
		loginPanel.setVisible(false);
		memberPanel.setVisible(true);
		
		memberPanel.repaint();
		setVisible(true);
	}
	
	public void signOut() {
		idLabel.setText("");
		memberPanel.setVisible(false);
		loginPanel.setVisible(true);
		
		loginPanel.repaint();
		setVisible(true);
	}
	
	public void showMsg(String str) {
		JOptionPane.showMessageDialog(this, str);
	}
	
	public JPanel addMoiveBox(String[] str) {
		movieButton = new JButton();
		movieButton.setIcon(new ImageIcon(new ImageIcon("SemiProject2/src/img/"+str[1]+".jpg").getImage().getScaledInstance(517, 740,1)));
		movieButton.setBounds(0, 0, 517, 740);
		movieNameLabel=new JLabel(str[1],0);
		movieNameLabel.setBounds(0, 750, 517, 30);
		moviePanel = new JPanel();
			moviePanel.setLayout(null);
			moviePanel.setPreferredSize(new Dimension(517,800));
			moviePanel.add(movieButton);
			moviePanel.add(movieNameLabel);
			
		return moviePanel;
	}
	
	public int showCon(String str) {
		return JOptionPane.showConfirmDialog(this, str);
	}
	
//	public static void main(String[] args) {
//		new HomeView();
//	}
	
}
