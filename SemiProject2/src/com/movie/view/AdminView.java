package com.movie.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.movie.VO.OpenMovVO;
import com.movie.VO.ScheduleVO;

public class AdminView extends JFrame{
	public JButton btMovie, btSchedule, btMovIns, btMovDel, btScheIns, btScheDel, btLogout, btMovImg, btMovSyn;
	public JPanel movPanel, movInsPanel, movTablePanel
				,schePanel, scheInsPanel, scheTablePanel;
	
	DefaultTableModel dtmM, dtmS;
	JScrollPane scroll_tableM, scroll_tableS;
	public JTable movTable, scheTable;
	public JComboBox<String> scheComMov, scheComSreen, scheComTime;
	public JTextField tfYear, tfMonth, tfDate, tfMovImage, tfMovSyn
					, tfMovTitle, tfMovDir, tfMovAct, tfMovOpen;
	JLabel laSchDate, laSchScreen, laSchTitle, laSchTime, laYear, laMonth, laDate
			, laMovTitle, laMovDir, laMovAct, laMovOpen, laMovImage, laMovSyn;
	
	public AdminView() {
		Font f = new Font("Dialog",Font.BOLD,25);
		
		btMovie = new JButton("영화");
		btMovie.setBounds(30,30,250,50);
		btMovie.setFont(f);
		btSchedule = new JButton("스케줄");
		btSchedule.setBounds(300,30,250,50);
		btSchedule.setFont(f);
		btLogout = new JButton("로그아웃");
		btLogout.setBounds(1640,30,250,50);
		btLogout.setFont(f);
		
		//영화테이블
		String[] columTitleM = { "영화번호", "영화제목", "관객수", "감독", "주연", "개봉일" };
		Object[][] rowDataM = new Object[0][6];
		dtmM = new DefaultTableModel(rowDataM, columTitleM);
		movTable = new JTable(dtmM);
		movTable.getTableHeader().setFont(new Font("Dialog",Font.BOLD,25));
		movTable.setRowHeight(40);
		movTable.setFont(new Font("Dialog",Font.BOLD,25));
		scroll_tableM = new JScrollPane(movTable);
		
		//스케줄 테이블
		String[] columTitleS = { "상영일정번호", "영화제목", "상영관", "날짜" };
		Object[][] rowDataS = new Object[0][4];
		dtmS = new DefaultTableModel(rowDataS, columTitleS);
		scheTable = new JTable(dtmS); //980 -40 -80 - 860
		scheTable.getTableHeader().setFont(new Font("Dialog",Font.BOLD,25));
		scheTable.setRowHeight(40);
		scheTable.setFont(new Font("Dialog",Font.BOLD,25));
		scheTable.getColumn("상영일정번호").setPreferredWidth(180);
		scheTable.getColumn("영화제목").setPreferredWidth(330);
		scheTable.getColumn("상영관").setPreferredWidth(100);
		scheTable.getColumn("날짜").setPreferredWidth(370);
		scroll_tableS = new JScrollPane(scheTable);
		
		//////////////////////////영화//////////////////////////
		//영화 패널
		movPanel = new JPanel(); 
		movPanel.setLayout(null);
		movPanel.setBackground(Color.blue);
		movPanel.setBounds(30, 110, 1860, 850);
		
		//영화 테이블 패널
		movTablePanel = new JPanel();
		movTablePanel.setBounds(30, 25, 980, 800);
		movTablePanel.setLayout(null);
		scroll_tableM.setBounds(0, 0, 980, 750);
		btMovDel = new JButton("영화 삭제");
		btMovDel.setBounds(375, 760, 250, 30);
		btMovDel.setFont(f);
		movTablePanel.add(scroll_tableM);
		movTablePanel.add(btMovDel);
		movPanel.add(movTablePanel);
		
		//영화 추가 패널
		movInsPanel = new JPanel();
		movInsPanel.setLayout(null);
		movInsPanel.setBounds(1060, 25, 760, 800);
		movPanel.add(movInsPanel);
		btMovIns = new JButton("영화 추가");
		btMovIns.setBounds(255, 760, 250, 30);
		btMovIns.setFont(f);
		movInsPanel.add(btMovIns);
		laMovTitle = new JLabel("영화 제목  :");
		tfMovTitle = new JTextField();
		laMovTitle.setBounds(90, 70, 200, 50);
		laMovTitle.setFont(f);
		tfMovTitle.setBounds(230, 70, 380, 50);
		tfMovTitle.setFont(f);
		laMovDir = new JLabel("감독  :");
		tfMovDir = new JTextField();
		laMovDir.setBounds(150, 180, 100, 50);
		laMovDir.setFont(f);
		tfMovDir.setBounds(230, 180, 380, 50);
		tfMovDir.setFont(f);
		laMovAct = new JLabel("주연  :");
		tfMovAct = new JTextField();
		laMovAct.setBounds(150, 290, 100, 50);
		laMovAct.setFont(f);
		tfMovAct.setBounds(230, 290, 380, 50);
		tfMovAct.setFont(f);
		laMovOpen = new JLabel("개봉일 :");
		tfMovOpen = new JTextField("YYYY-MM-DD");
		laMovOpen.setBounds(130, 400, 130, 50);
		tfMovOpen.setBounds(230, 400, 380, 50);
		laMovOpen.setFont(f);
		tfMovOpen.setFont(f);
		laMovSyn = new JLabel("줄거리 :");
		tfMovSyn = new JTextField();
		laMovSyn.setBounds(130, 510, 150, 50);
		tfMovSyn.setBounds(230, 510, 320, 50);
		laMovSyn.setFont(f);
		tfMovSyn.setFont(f);
		tfMovSyn.setEnabled(false);
		btMovSyn = new JButton("첨부");
		btMovSyn.setBounds(560, 510, 60, 50);
		btMovSyn.setFont(f);
		laMovImage = new JLabel("포스터 이미지 : ");
		tfMovImage = new JTextField();
		tfMovImage.setEnabled(false);
		laMovImage.setBounds(50, 620, 200, 50);
		tfMovImage.setBounds(230, 620, 320, 50);
		laMovImage.setFont(f);
		tfMovImage.setFont(f);
		btMovImg = new JButton("찾기");
		btMovImg.setBounds(560, 620, 60, 50);
		btMovImg.setFont(f);
		
		movInsPanel.add(laMovTitle);
		movInsPanel.add(tfMovTitle);
		movInsPanel.add(laMovDir);
		movInsPanel.add(tfMovDir);
		movInsPanel.add(laMovAct);
		movInsPanel.add(tfMovAct);
		movInsPanel.add(laMovOpen);
		movInsPanel.add(tfMovOpen);
		movInsPanel.add(laMovImage);
		movInsPanel.add(tfMovImage);
		movInsPanel.add(btMovImg);
		movInsPanel.add(btMovSyn);
		movInsPanel.add(laMovSyn);
		movInsPanel.add(tfMovSyn);
		
		//////////////////////////스케줄//////////////////////////
		//스케줄 패널
		schePanel = new JPanel();
		schePanel.setLayout(null);
		schePanel.setBackground(Color.green);
		schePanel.setBounds(30, 110, 1860, 850);
		btScheIns = new JButton();
		btScheDel = new JButton();
		
		//스케줄 테이블 패널
		scheTablePanel = new JPanel();
		scheTablePanel.setBounds(30, 25, 980, 800);
		scheTablePanel.setLayout(null);
		scroll_tableS.setBounds(0, 0, 980, 750);
		schePanel.add(scheTablePanel);
		btScheDel = new JButton("스케줄 삭제");
		btScheDel.setBounds(375, 760, 250, 30);
		btScheDel.setFont(f);
		scheTablePanel.add("Center",scroll_tableS);
		scheTablePanel.add("South",btScheDel);
		
		//스케줄 추가 패널
		scheInsPanel = new JPanel();
		scheInsPanel.setLayout(null);
		scheInsPanel.setBounds(1060, 25, 760, 800);
		schePanel.add(scheInsPanel);
		
		scheComMov = new JComboBox<>();
		scheComMov.setBounds(50, 70, 660, 50);
		scheComMov.setFont(f);
		scheInsPanel.add(scheComMov);
		laSchTitle = new JLabel("영화");
		laSchTitle.setBounds(70, 20, 50, 50);
		laSchTitle.setFont(f);
		scheInsPanel.add(laSchTitle);
		
		scheComSreen = new JComboBox<>();
		scheComSreen.setBounds(50, 270, 660, 50);
		scheComSreen.setFont(f);
		scheInsPanel.add(scheComSreen);
		laSchScreen = new JLabel("상영관");
		laSchScreen.setBounds(70, 220, 150, 50);
		laSchScreen.setFont(f);
		scheInsPanel.add(laSchScreen);
		
		laSchDate = new JLabel("날짜");
		laSchDate.setBounds(70, 400, 50, 50);
		laSchDate.setFont(f);
		scheInsPanel.add(laSchDate);
		tfYear = new JTextField("YYYY");
		laYear = new JLabel("년");
		tfMonth = new JTextField("MM");
		laMonth = new JLabel("월");
		tfDate = new JTextField("DD");
		laDate = new JLabel("일");
		tfYear.setBounds(100, 450, 120, 50);
		laYear.setBounds(240, 450, 30, 50);
		tfMonth.setBounds(320, 450, 100, 50);
		laMonth.setBounds(440,450, 30, 50);
		tfDate.setBounds(520, 450, 100, 50);
		laDate.setBounds(640, 450, 30, 50);
		tfYear.setFont(f);
		laYear.setFont(f);
		tfMonth.setFont(f);
		laMonth.setFont(f);
		tfDate.setFont(f);
		laDate.setFont(f);
		scheInsPanel.add(tfYear);
		scheInsPanel.add(tfMonth);
		scheInsPanel.add(tfDate);
		scheInsPanel.add(laYear);
		scheInsPanel.add(laMonth);
		scheInsPanel.add(laDate);
		
		scheComTime = new JComboBox<>();
		scheComTime.setBounds(50, 620, 660, 50);
		scheComTime.setFont(f);
		laSchTime = new JLabel("시간");
		laSchTime.setBounds(70, 570, 50, 50);
		laSchTime.setFont(f);
		scheInsPanel.add(scheComTime);
		scheInsPanel.add(laSchTime);
		scheComTime.addItem("<<< 시간 >>>");
		scheComTime.addItem("10:00");
		scheComTime.addItem("11:00");
		scheComTime.addItem("12:00");
		scheComTime.addItem("13:00");
		scheComTime.addItem("14:00");
		scheComTime.addItem("15:00");
		scheComTime.addItem("16:00");
		scheComTime.addItem("17:00");
		scheComTime.addItem("18:00");
		
		btScheIns = new JButton("스케줄 추가");
		btScheIns.setBounds(255, 760, 250, 30);
		btScheIns.setFont(f);
		scheInsPanel.add(btScheIns);
		
		add(movPanel);
		add(schePanel);		
		add(btMovie);
		add(btSchedule);
		add(btLogout);
		
		setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		movPanel.setVisible(true);
		schePanel.setVisible(false);
	}
	
	public void displayMovTable(ArrayList<OpenMovVO> list) {
		dtmM.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			OpenMovVO vo = list.get(i);
			Object[] rowData = { vo.getMovieNum(), vo.getMovieTitle(), vo.getTotalViewer(), vo.getDirector(), vo.getMainActor(), vo.getOpeningDate() };
			dtmM.addRow(rowData);
		}
	}// displayMovTable
	
	
	public void displayScheTable(ArrayList<ScheduleVO> list) {
		dtmS.setRowCount(0);
		for (int i = 0; i < list.size(); i++) {
			ScheduleVO vo = list.get(i);
			Object[] rowData = { vo.getScheduleNum(), vo.getMovieTitle(), vo.getScreenNum(), vo.getScreenDate()};
			dtmS.addRow(rowData);
		}
	}// displayMovTable
	
	public String choose() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		int s = chooser.showOpenDialog(this);
		if(s == 0) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
	public String chooseSynopsis() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT 파일 읽기", "txt");
		chooser.setFileFilter(filter);
		int s = chooser.showOpenDialog(this);
		if(s == 0) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
	
	public void displayComMov(ArrayList<String> list) {
		String title;
		scheComMov.removeAllItems();
		scheComMov.addItem("<<< 영화  >>>");
		for (int i = 0; i < list.size(); i++) {
			title = list.get(i);
			scheComMov.addItem(title);
		}
	}
	
	public void displayComScreen(ArrayList<String> list) {
		String screen;
		scheComSreen.removeAllItems();
		scheComSreen.addItem("<<< 상영관  >>>");
		for (int i = 0; i < list.size(); i++) {
			screen = list.get(i);
			scheComSreen.addItem(screen);
		}
	}
	
	public void showMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}// showMsg
	
	public int showCon(String msg) {
		return JOptionPane.showConfirmDialog(this, msg);
	}
	
	public void initMovie() {
		tfMovTitle.setText("");
		tfMovDir.setText("");
		tfMovAct.setText("");
		tfMovOpen.setText("YYYY-MM-DD");
		tfMovSyn.setText("");
		tfMovImage.setText("");
	}
	
	public void initSchedule() {
		tfYear.setText("YYYY");
		tfMonth.setText("MM");
		tfDate.setText("DD");
		scheComTime.setSelectedIndex(0);
	}
}