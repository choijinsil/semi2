
package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		System.out.println(rDao.findMovieTitle().get(0).getMovieTitle());// 테스트
		System.out.println("값 잘 가져오니 테스트" + cv.selMovie);
		cv.btNext.addActionListener(this);
		cv.btPrev.addActionListener(this);
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

		
