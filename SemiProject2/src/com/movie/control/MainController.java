package com.movie.control;

import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class MainController {
	ChoiceController cc;
	HomeController hc;
	PaymentController pc;
	SeatController sc;

	ReservationDAO rd;
	
	ChoiceView cv;
	HomeView hv;
	PaymentView pv;
	SeatView sv;

	public MainController() {
		cc = new ChoiceController(cv);
		hc = new HomeController();
		pc = new PaymentController();
		sc = new SeatController();
		
		// view
		cv = new ChoiceView();
		hv = new HomeView();
		pv = new PaymentView();
		sv = new SeatView();
	}
	
	public static void main(String[] args) {
		new MainController();
	}
	
}
