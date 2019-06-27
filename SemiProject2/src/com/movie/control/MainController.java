package com.movie.control;

import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class MainController {
	ChoiceView cv;
	HomeView hv;
	PaymentView pv;
	SeatView sv;
	
	ReservationDAO rd;
	
	ChoiceController cc;			
	HomeController hc;
	PaymentController pc;
	SeatController sc;

	
	

	
	public MainController() {
	// view
	cv = new ChoiceView();
	hv = new HomeView();
	pv = new PaymentView();
	sv = new SeatView();
	

	cc = new ChoiceController(this);
	hc = new HomeController();
	pc = new PaymentController();
	sc = new SeatController();
		

	
	}
	
	public static void main(String[] args) {
		new MainController();
	}
	
}
