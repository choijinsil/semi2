
package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		cv.displayScreenDate(rDao.findScreenDate("알라딘"));
		
//		System.out.println(rDao.findMovieTitle().get(0).getMovieTitle());// 테스트
//		System.out.println("값 잘 가져오니 테스트" + cv.selMovie);
		cv.btNext.addActionListener(this);
		cv.btPrev.addActionListener(this);
		cv.cbMovie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox<String> cb = (JComboBox<String>) e.getSource();
				cv.selMovie = cb.getSelectedItem().toString();
//				System.out.println("선택했습니다 작동합니다");
				cv.selMovieIdx = cb.getSelectedIndex();
				//System.out.println(selMovieIdx);
//				findScreenDate(selMovie);
				// JOptionPane.showConfirmDialog(null, "선택한 영화는" + selMovie + "입니다. 맞습니까?");
				cv.displayScreenDate(rDao.findScreenDate(cv.selMovie));
//				System.out.println(cv.selMovie);
				cv.imgLabel.setIcon(new ImageIcon("C:\\Users\\Playdata\\git\\semi2\\SemiProject2\\src\\img\\aladin.jpg"));
			}
		});
		
		cv.dbDate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
					try
					{
						JComboBox<String> cb = (JComboBox<String>) e.getSource();
						System.out.println("스트링으로 받지? " + cb.getSelectedItem().toString());
						cv.selDate = cb.getSelectedItem().toString();
//						SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
						SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd hh24");
						cv.date = transFormat.parse(cv.selDate);
						System.out.println("데이트로 받지? " + cv.date);
						rDao.findScheduleNum(cv.selMovie, cv.date);
					} catch (ParseException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});

		}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob == cv.btNext) {
			cv.setVisible(false);
			sv.setVisible(true);
		}
		else if(ob == cv.btPrev) {
			cv.setVisible(false);
			hv.setVisible(true);
		}
		
	}

}

		
