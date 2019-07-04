package com.movie.view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.movie.VO.MovieVO;

public class ChoiceView extends JFrame {
	public JTextField movieF, dateF, timeF;
	public JPanel fullPanel, pTotal, p1, p2, p3, cardPanel, cardPanel2;
	public JButton btNext, btPrev;
	public CardLayout cardLayout, cardLayout2;
	public JTextArea synopsis;

	// JComboBox 선언

	public JComboBox<String> cbMovie, cbYear, cbMonth, cbDay, movieTime, dbDate;

	// 날짜배열 선언
	public ArrayList<String> yearArray; // 년도
	public ArrayList<String> monthArray; // 월
	public ArrayList<String> dayArray; // 일

	// 선택값
	public String selMovie, selDate; // 타이틀

	public int selMovieIdx, selMovieNum; // 타이틀 인덱스
	public String selDayIdx; // 날짜 선택 인덱스
	DateFormat transFormat;
	//public Date date;

	public JLabel imgLabel;

	// 3관 까지 배열 만드리기
	public ChoiceView() {
//      cal();
		cardLayout = new CardLayout();
		cardLayout2 = new CardLayout();
		cardPanel = new JPanel(); // 이미지 붙일 판넬
		cardPanel.setLayout(cardLayout);
		cardPanel2 = new JPanel();
		cardPanel2.setLayout(cardLayout2);
		cardPanel2.setBounds(65, 280, 1300, 500);

		fullPanel = new JPanel();
		pTotal = new JPanel();
		p1 = new JPanel();
		p1.setBounds(0, 0, 580, 875);
		p2 = new JPanel();
		p2.setBounds(600, 0, 1300, 875);
		p2.setLayout(null);
		//date = new Date();

		fullPanel.setLayout(null);
		pTotal.setLayout(null);

		btNext = new JButton("다음");
		btPrev = new JButton("이전");
		btNext.setBounds(1690, 876, 200, 80);
		btPrev.setBounds(30, 876, 200, 80);

		cbMovie = new JComboBox<String>();
		cbMovie.setFont(new Font("배달의민족 주아", 1, 37));

		dbDate = new JComboBox<String>();
		dbDate.setFont(new Font("맑은 고딕", Font.BOLD, 30));

		cbMovie.setPreferredSize(new Dimension(450, 150));
		dbDate.setBounds(65, 5, 450, 150);

		p1.add(cbMovie);
		p1.add(cardPanel);
		p2.add(dbDate);
		p2.add(cardPanel2);

		pTotal.add(p1);
		pTotal.add(p2);

		pTotal.setSize(1920, 870);
		fullPanel.add(pTotal);
		fullPanel.add(btNext);
		fullPanel.add(btPrev);
		add(fullPanel);

		setSize(800, 800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}


	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}

	public void displayTable(ArrayList<MovieVO> list) {
		cbMovie.removeAllItems();
		cardPanel.removeAll();
		cardPanel2.removeAll();
		cbMovie.addItem("<<<영화>>>");
		cardPanel.add("<<<영화>>>", new JLabel());
		cardPanel2.add("<<<영화>>>", new JLabel());
		for (int i = 0; i < list.size(); i++) {
			cbMovie.addItem(list.get(i).getMovieTitle()); // 제목추가
			imgLabel = new JLabel();
			imgLabel.setIcon(new ImageIcon(list.get(i).getMovieImage().getImage().getScaledInstance(517, 710, 1)));
			cardPanel.add(list.get(i).getMovieTitle(), imgLabel);

			synopsis = new JTextArea();
			synopsis.setText(list.get(i).getSynopsis());
			synopsis.setSize(1300, 500);
			cardPanel2.add(list.get(i).getMovieTitle(), synopsis);
			synopsis.setEditable(false);
			synopsis.setBackground(getBackground());
			synopsis.setFont(new Font("맑은 고딕", 1, 27));
		}

	}// displayTable

	public void displayScreenDate(ArrayList<MovieVO> list) {
		String[] objDate = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			objDate[i] = list.get(i).getScreenDate();
		}
		DefaultComboBoxModel model = new DefaultComboBoxModel(objDate);
		dbDate.setModel(model);
	}// displayScreenDate
}