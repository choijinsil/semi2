package com.test.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChoiceView extends JFrame {
	public JTextField movieF, dateF, timeF;
	public JPanel p1, p2, p3;

	// JComboBox 선언
	public JComboBox<String> cbYear, cbMonth, cbDay;

	// 날짜배열 선언
	public ArrayList<String> yeararray; // 년도
	public ArrayList<String> montharray; // 월
	public ArrayList<String> dayarray; // 일
	
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

		cbYear.setPreferredSize(new Dimension(150, 50));
		cbMonth.setPreferredSize(new Dimension(150, 50));
		cbDay.setPreferredSize(new Dimension(150, 50));

		p1.add(movieF);
		p2.add(cbYear);
		p2.add(cbMonth);
		p2.add(cbDay);
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
		yeararray = new ArrayList<String>();
		montharray = new ArrayList<String>();
		dayarray = new ArrayList<String>();

		// 현재 날짜
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int today = calendar.get(Calendar.DAY_OF_MONTH);

		// 년도
		for (int i = year; i <= year + 1; i++) {
			yeararray.add(String.valueOf(i));
		}
		cbYear = new JComboBox<String>(yeararray.toArray(new String[yeararray.size()]));
		cbYear.setSelectedItem(String.valueOf(year));

		// 월
		for (int i = 1; i <= 12; i++) {
			montharray.add(addZeroString(i));
		}
		cbMonth = new JComboBox<String>(montharray.toArray(new String[montharray.size()]));

		String mcom = month >= 10 ? String.valueOf(month) : "0" + month;
		cbMonth.setSelectedItem(mcom);

		// 일
		int m = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 이번달 최대 일자 구하기
		for (int i = 1; i <= m; i++) {
			dayarray.add(addZeroString(i));
		}
		cbDay = new JComboBox<String>(dayarray.toArray(new String[dayarray.size()]));

		String dcom = today >= 10 ? String.valueOf(today) : "0" + today;
		cbDay.setSelectedItem(dcom);
	}

	public static void main(String[] args) {
		new ChoiceView();

	}

}
