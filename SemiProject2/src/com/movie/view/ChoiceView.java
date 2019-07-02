package com.movie.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.movie.VO.MovieVO;

public class ChoiceView extends JFrame {
	public JTextField movieF, dateF, timeF;
	public JPanel fullPanel,pTotal, p1, p2, p3;
	public JButton btNext, btPrev;


	

	// JComboBox 선언

	public JComboBox<String> cbMovie, cbYear, cbMonth, cbDay, movieTime, dbDate;

	// 날짜배열 선언
	public ArrayList<String> yearArray; // 년도
	public ArrayList<String> monthArray; // 월
	public ArrayList<String> dayArray; // 일
	

//	public String[] movieTitle = { "알라딘", "숟가락 살인마", "발표왕" };

//	public String[] str = { "1시", "3시", "5시" };

	// 선택값
	public String selMovie, selDate; // 타이틀
	
	public int selMovieIdx, selMovieNum; // 타이틀 인덱스
	public String selDayIdx; // 날짜 선택 인덱스
	DateFormat transFormat;
	public Date date;

	ImageIcon[] images = { new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\aladin.jpg"),
			new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\bug.jpg"),
			new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\toy.jpg") };
	public JLabel imgLabel = new JLabel(images[0]);

	// 3관 까지 배열 만드리기
	public ChoiceView() {
//		cal();

		fullPanel = new JPanel();
		pTotal = new JPanel();
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		date = new Date();

		fullPanel.setLayout(null);
		pTotal.setLayout(new GridLayout(1, 3));
		
//		movieF = new JTextField("영화");
//		dateF = new JTextField("날짜");
//		timeF = new JTextField("시간");

		btNext = new JButton("다음");
		btPrev = new JButton("이전");
		btNext.setBounds(1690, 876, 200, 80);
		btPrev.setBounds(30, 876, 200, 80);


		cbMovie = new JComboBox<String>();
		// 영화 선택 값 넘기기, 이후 이미지 까지 변경하도록
//		cbMovie.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JComboBox<String> cb = (JComboBox<String>) e.getSource();
//				selMovie = cb.getSelectedItem().toString();
//
//				selMovieIdx = cb.getSelectedIndex();
//				//System.out.println(selMovieIdx);
////				findScreenDate(selMovie);
//				// JOptionPane.showConfirmDialog(null, "선택한 영화는" + selMovie + "입니다. 맞습니까?");
//
//				imgLabel.setIcon(images[selMovieIdx]);
//			}
//		});

//		movieTime = new JComboBox<String>(str);
		dbDate = new JComboBox<String>();
		
		
//		movieTime.setSelectedItem("1시");
//		movieTime.setSelectedItem("3시");
//		movieTime.setSelectedItem("5시");

		cbMovie.setPreferredSize(new Dimension(450, 150));
//		cbYear.setPreferredSize(new Dimension(150, 100));
//		cbMonth.setPreferredSize(new Dimension(150, 100));
//		cbDay.setPreferredSize(new Dimension(150, 100));
//		movieTime.setPreferredSize(new Dimension(150,100));
		dbDate.setPreferredSize(new Dimension(450,150));
	
		
		p1.add(cbMovie);

//		p1.add(movieF);
		p1.add(imgLabel);

//		p2.add(cbYear);
//		p2.add(cbMonth);
//		p2.add(cbDay);
		p2.add(dbDate);

//		p3.add(movieTime);
//		p3.add(timeF);

		pTotal.add(p1);
		pTotal.add(p2);
		pTotal.add(p3);
		
		pTotal.setSize(1920, 900);
		
		fullPanel.add(pTotal);
		fullPanel.add(btNext);
		fullPanel.add(btPrev);
		add(fullPanel);
		
		
		
		setVisible(true);
		setSize(800, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
 
	}

	public void getMovieTitle() {
		System.out.println(cbMovie.getSelectedIndex());
	}


	public void displayTable(ArrayList<MovieVO> list) {
		//System.out.println("list.size()>>" + list.size());
		cbMovie.removeAllItems();
		cbMovie.addItem("<<<영화>>>");
		for (int i = 0; i < list.size(); i++) {

			cbMovie.addItem(list.get(i).getMovieTitle());
		}

	}// displayTable
	
	public void displayScreenDate(ArrayList<MovieVO> list) {
		//setModel(ComboBoxModel<E> aModel)
		//DefaultComboBoxModel
		
		
//		dbDate.removeAllItems();
////		dbDate.addItem("<<<날짜>>>");
//		System.out.println("list.size()>>" + list.size());
		String[] objDate = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			//dbDate.addItem(list.get(i).getScreenDate());
			objDate[i] = list.get(i).getScreenDate();
		}

		DefaultComboBoxModel model = new DefaultComboBoxModel(objDate);
		dbDate.setModel(model);
		
	}// displayScreenDate


}