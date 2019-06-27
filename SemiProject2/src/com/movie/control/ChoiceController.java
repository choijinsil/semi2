
package com.movie.control;

import com.movie.VO.MovieVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;

import sun.applet.Main;

public class ChoiceController {
//	ChoiceView cv;
//	ReservationDAO rDao;

	public ChoiceController() {
	}

	public ChoiceController(MainController mv) {
		ChoiceView cv;
		ReservationDAO rDao;

		cv = mv.cv;
		rDao = new ReservationDAO();

		cv.displayTable(rDao.findMovieTitle());
		System.out.println(rDao.findMovieTitle().get(0).getMovieTitle());// 테스트
	}

}