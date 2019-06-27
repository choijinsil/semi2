package com.movie.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.movie.VO.MovieVO;

public class ChoiceView extends JFrame {
	public JTextField movieF, dateF, timeF;
	public JPanel p1, p2, p3;
	
	// JComboBox 선언
	public JComboBox<String> cbMovie, cbYear, cbMonth, cbDay, movieTime;
	
	// 날짜배열 선언
	public ArrayList<String> yearArray; // 년도
	public ArrayList<String> monthArray; // 월
	public ArrayList<String> dayArray; // 일

//	public String[] movieTitle = { "", "숟가락 살인마", "발표왕" };
	
	public String[] str = { "1시", "3시", "5시" };

	//선택값
	public String selMovie; //타이틀
	public int selMovieIdx; //타이틀 인덱스
	
	ImageIcon[] images = { new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\aladin.jpg"),
							new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\bug.jpg"),
							new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\toy.jpg")};
	public JLabel imgLabel = new JLabel(images[0]); 
	

	
	// 3관 까지 배열 만드리기
	public ChoiceView() {
		cal();
		

		setLayout(new GridLayout(1, 3));

		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel(); 

		movieF = new JTextField("영화");
		dateF = new JTextField("날짜");
		timeF = new JTextField("시간");
		
		cbMovie = new JComboBox<String>();
		//영화 선택 값 넘기기, 이후 이미지 까지 변경하도록 
		cbMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				selMovie = cb.getSelectedItem().toString();
				selMovieIdx = cb.getSelectedIndex();
				System.out.println(selMovieIdx);
				//JOptionPane.showConfirmDialog(null, "선택한 영화는" + selMovie + "입니다. 맞습니까?");
				
				imgLabel.setIcon(images[selMovieIdx]);
			}
		});
		
		movieTime = new JComboBox<String>(str);

//		movieTime.setSelectedItem("1시");
//		movieTime.setSelectedItem("3시");
//		movieTime.setSelectedItem("5시");

		cbMovie.setPreferredSize(new Dimension(450, 100));
		cbYear.setPreferredSize(new Dimension(150, 50));
		cbMonth.setPreferredSize(new Dimension(150, 50));
		cbDay.setPreferredSize(new Dimension(150, 50));

		p1.add(cbMovie);
		p1.add(movieF);
		p1.add(imgLabel);
		
		p2.add(cbYear);
		p2.add(cbMonth);
		p2.add(cbDay);

		p3.add(movieTime);

		p3.add(timeF);

		add(p1);
		add(p2);
		add(p3);

		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		
		
	}

	// 한자리 숫자 앞에 + 0 예) 7일 -> 07일
	private String addZeroString(int k) {
		String value = Integer.toString(k);
		if (value.length() == 1) {
			value = "0" + value;
		}
		return value;
	}

	public void cal() {
		Calendar calendar = Calendar.getInstance(); // 현재 날짜/시간 등의 각종 정보 얻기
		yearArray = new ArrayList<String>();
		monthArray = new ArrayList<String>();
		dayArray = new ArrayList<String>();

		// 현재 날짜
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int today = calendar.get(Calendar.DAY_OF_MONTH);

		// 년도
		for (int i = year; i <= year + 1; i++) {
			yearArray.add(String.valueOf(i));
		}
		cbYear = new JComboBox<String>(yearArray.toArray(new String[yearArray.size()]));
		cbYear.setSelectedItem(String.valueOf(year));

		// 월
		for (int i = 1; i <= 12; i++) {
			monthArray.add(addZeroString(i));
		}
		cbMonth = new JComboBox<String>(monthArray.toArray(new String[monthArray.size()]));

		String mcom = month >= 10 ? String.valueOf(month) : "0" + month;
		cbMonth.setSelectedItem(mcom);

		// 일
		int m = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 이번달 최대 일자 구하기
		for (int i = 1; i <= m; i++) {
			dayArray.add(addZeroString(i));
		}
		cbDay = new JComboBox<String>(dayArray.toArray(new String[dayArray.size()]));

		String dcom = today >= 10 ? String.valueOf(today) : "0" + today;
		cbDay.setSelectedItem(dcom);
	}
	
	public void displayTable(ArrayList<MovieVO> list) {

		System.out.println("list.size()>>"+list.size());
		for (int i = 0; i < list.size(); i++) {
			
			
			cbMovie.addItem(list.get(i).getMovieTitle()); 
		}

	
	}// displayTable


	

}
