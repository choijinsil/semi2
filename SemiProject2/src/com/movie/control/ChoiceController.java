
package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import com.movie.VO.MovieVO;
import com.movie.VO.SeatVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.SeatView;

import sun.applet.Main;

public class ChoiceController implements ActionListener {

	ChoiceView cv;
	ReservationDAO rDao;
	HomeView hv;
	SeatView sv;
	int choiceScNum;

	public ChoiceController() {
	}

	public ChoiceController(MainController mv) {
//		ChoiceView cv;
//		ReservationDAO rDao;

		cv = mv.cv;
		hv = mv.hv;
		sv = mv.sv;
		rDao = new ReservationDAO();

		cv.displayTable(rDao.findMovieTitle());
//		cv.displayScreenDate(rDao.findScreenDate(cv.cbMovie.getSelectedItem().toString()));

//		System.out.println(rDao.findMovieTitle().get(0).getMovieTitle());// 테스트
//		System.out.println("값 잘 가져오니 테스트" + cv.selMovie);
		cv.btNext.addActionListener(this);
		cv.btPrev.addActionListener(this);

		cv.cbMovie.addActionListener(new ActionListener() { // 영화제목 선택

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(">>>왼쪽콤보");
//				JComboBox<String> cb = (JComboBox<String>) e.getSource();
			
				cv.selMovie = cv.cbMovie.getSelectedItem().toString(); // 콤보박스값을 selMovie저장
//				System.out.println("선택했습니다 작동합니다");
//				cv.selMovieIdx = cv.cbMovie.getSelectedIndex();
//				System.out.println(cv.selMovieIdx);		
//				findScreenDate(selMovie);
				cv.displayScreenDate(rDao.findScreenDate(cv.selMovie));
				System.out.println("rDao.findScreenDate(cv.selMovie)>>" + rDao.findScreenDate(cv.selMovie));
//				System.out.println(cv.selMovie);
				cv.imgLabel
						.setIcon(new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\aladin.jpg"));
			}
		});

		cv.dbDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("================================");
				System.out.println(">>>오른쪽콤보");
				try {

//					System.out.println("스트링으로 받지? >>>" + cv.dbDate.getSelectedItem().toString()+"<<<");
					System.out.println("cv>>" + cv);
					System.out.println("dbDate>>" + cv.dbDate);
					System.out.println("Item>>" + cv.dbDate.getSelectedItem());
					cv.selDate = cv.dbDate.getSelectedItem().toString(); // 현재 스트링

					Date trDate = new SimpleDateFormat("yyyy/MM/dd HH").parse(cv.selDate);
//					System.out.println("trDate>>>"+ trDate);
					cv.date = trDate; // String을 Date형식에 넣기 

//					System.out.println("데이트로 받지? " + cv.date);
//					System.out.println("cv.selMovie>>"+cv.selMovie);
//					System.out.println("cv.selDate>>>"+cv.selDate);

//					ArrayList<MovieVO> arr = rDao.findScheduleNum(cv.selMovie, cv.selDate);
					choiceScNum = rDao.findScheduleNum(cv.selMovie, cv.selDate);
//					System.out.println(arr.size());
					System.out.println("choiceScNum>>>>"+choiceScNum);
//					for (int i = 0; i < arr.size(); i++) {
//						System.out.println("선택된 영화의 스케쥴 넘을 가져올거: ");
//						System.out.println(arr.get(i).getScheduleNum());
//					}

//					rDao.findScheduleNum(cv.selMovie, cv.selDate);
//					System.out.println("이 시간값으로 스케쥴 넘버 넘김>>" + cv.date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		cv.btNext.addActionListener(new ActionListener() {

			@Override 
			public void actionPerformed(ActionEvent e) {
				String[] str=cv.selDate.split(" ");
				System.out.println("====================");
				System.out.println("str[0]"+str[0]);
				System.out.println("str[1]"+str[1]);
				System.out.println("choiceScNum+\"\">>"+choiceScNum+"");
				System.out.println("cv.selMovie"+cv.selMovie);
				System.out.println("====================");
				mv.movieTmp.put("scheduleNum", choiceScNum+""); // 스케쥴넘버
				mv.movieTmp.put("movieTitle",cv.selMovie); // 영화제목
				mv.movieTmp.put("screenDate", str[0]);
				mv.movieTmp.put("screenTime", str[1]+"시");

				cv.setVisible(false);
				sv.setVisible(true);
				// 데이터 날짜 시간 잘라서, 
				// movieTitle, screenDate, screenTime
			}
		});

		cv.btPrev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cv.setVisible(false);
				hv.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == cv.btNext) {
			cv.setVisible(false);
			sv.setVisible(true);
		} else if (ob == cv.btPrev) {
			cv.setVisible(false);
			hv.setVisible(true);
		}

	}

}
