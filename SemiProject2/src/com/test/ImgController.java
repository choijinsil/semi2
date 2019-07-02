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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import com.test.imgDAO;

public class ImgController extends JFrame implements ActionListener {
	imgDAO dao;
	JButton b;
	JLabel la;
	
	public ImgController() {
		dao = new imgDAO();
		
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
	   
		
		la = new JLabel("aaa");
		la.setBounds(30, 440, 400, 30);
		b = new JButton();
		b.setBounds(30, 30, 400, 400);
		add(b);
		add(la);
		setLayout(null);
		setBounds(30, 30, 600, 600);
		setVisible(true);
		b.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new ImgController();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		int s = chooser.showOpenDialog(this);
		System.out.println(s);
		System.out.println(chooser.getSelectedFile().getAbsolutePath());
		la.setText(chooser.getSelectedFile().getAbsolutePath());
		
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			FileInputStream fin = new FileInputStream(new File(la.getText()));
			byte[] buf = new byte[1024];
			int read = 0;
			while((read=fin.read(buf,0,buf.length))!=-1) {
				bout.write(buf, 0, read);
			}
			fin.close();
			if(dao.imgAdd(bout.toByteArray())) {
				System.out.println(bout.toByteArray());
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
		b.setIcon(dao.imgLead());
	}
}
