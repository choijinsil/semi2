
package com.movie.control;

import com.movie.VO.MovieVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;

import sun.applet.Main;

<<<<<<< HEAD
public class ChoiceController {
=======
public class ChoiceController{
>>>>>>> branch 'master' of https://github.com/choijinsil/semi2.git
//	ChoiceView cv;
//	ReservationDAO rDao;

	public ChoiceController() {
	}
	
	public ChoiceController(MainController mv) {
		ChoiceView cv;
		ReservationDAO rDao;
		
		cv = mv.cv;
		rDao = new ReservationDAO();

<<<<<<< HEAD
	public ChoiceController(MainController mv) {
		ChoiceView cv;
		ReservationDAO rDao;

		cv = mv.cv;
		rDao = new ReservationDAO();

		cv.displayTable(rDao.findMovieTitle());
		System.out.println(rDao.findMovieTitle().get(0).getMovieTitle());// 테스트
	}

}
=======

		
		cv.displayTable(rDao.findMovieTitle());
		
		System.out.println(cv.cbMovie.getSelectedItem());
		
	}
	
}
>>>>>>> branch 'master' of https://github.com/choijinsil/semi2.git
