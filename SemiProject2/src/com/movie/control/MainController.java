
package com.movie.control;

import java.util.HashMap;
import java.util.Map;

import com.movie.view.AdminView;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.ListView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;
import com.movie.view.SignUpView;

public class MainController {
	ChoiceView cv;
	HomeView hv;
	PaymentView pv;
	SeatView sv;
	SignUpView sUv;
	ListView lv;
	
	ChoiceController cc;			
	HomeController hc;
	PaymentController pc;
	SeatController sc;
	
	//Admin
	AdminView av;
	AdminController ac;
	
	Map<String, String> movieTmp;

	public MainController() {

	//map
	movieTmp = new HashMap<String, String>();

	// view
	cv = new ChoiceView();
	hv = new HomeView();
	pv = new PaymentView();
	sv = new SeatView();
	sUv = new SignUpView();
	lv = new ListView();
	
	//Admin
	av = new AdminView();
	ac = new AdminController(this);
	
	// controller
	cc = new ChoiceController(this);
	pc = new PaymentController(this);
	sc = new SeatController(this);
	hc = new HomeController(this);
	}
	
	public static void main(String[] args) {
		new MainController();
	}
	
}