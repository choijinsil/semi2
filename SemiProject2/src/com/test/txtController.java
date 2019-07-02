package com.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.test.imgDAO;

public class txtController extends JFrame implements ActionListener {
	txtDAO dao;
	JButton b, bu;
	JTextArea ta;
	JLabel la;
	
	public txtController() {
		dao = new txtDAO();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
//		la = new JLabel("aaa");
//		la.setBounds(30, 440, 400, 30);
		ta = new JTextArea();
		ta.setBounds(30, 30, 400, 400);
		b = new JButton();
		b.setBounds(30, 450, 150, 50);
		bu = new JButton();
		bu.setBounds(200, 450, 150, 50);
		add(ta);
		add(b);
		add(bu);
		ta.setText("오늘 서울은 하루종일 맑음 \n윤하가 불렀음");
//		add(la);
		setLayout(null);
		setBounds(30, 30, 600, 600);
		setVisible(true);
		b.addActionListener(this);
		bu.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new txtController();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == b) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & PNG Images", "txt");
		chooser.setFileFilter(filter);
		int s = chooser.showOpenDialog(this);
		System.out.println(s);
		System.out.println(chooser.getSelectedFile().getAbsolutePath());
//		la.setText(chooser.getSelectedFile().getAbsolutePath());
		
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			FileInputStream fin = new FileInputStream(new File(chooser.getSelectedFile().getAbsolutePath()));
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=fin.read(buf,0,buf.length))!=-1) {
				bout.write(buf, 0, read);
			}
			fin.close();
			if(dao.txtAdd(bout.toString())) {
				System.out.println("성공");
				bout.close();
			}else {
				System.out.println("실패");
			}
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		} catch (IOException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}
		} else {
			ta.setText("");
			ta.setText(dao.txtLead());
		}
//		b.setIcon(dao.imgLead());
	}
}
