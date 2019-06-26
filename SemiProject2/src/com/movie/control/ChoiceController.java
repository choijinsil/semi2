package com.movie.control;

import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;

public class ChoiceController {
	ChoiceView cv;
	ReservationDAO rDao;

	public ChoiceController(ChoiceView cv) {
		rDao = new ReservationDAO();
		this.cv = cv;
		System.out.println(rDao.findMovieTitle().get(1).getMovieTitle());
	}

}
