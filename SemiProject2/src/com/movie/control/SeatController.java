package com.movie.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Map;

import com.movie.VO.MovieVO;
import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class SeatController implements ActionListener {
	SeatView sv;
	ChoiceView cv;
	PaymentView pv;
	HomeView hv;
	ReservationDAO sdao;
	String seatName;
	Map<String, String> movieTmp;

	public SeatController(MainController mc) {
		this.sv = mc.sv;
		this.cv = mc.cv;
		this.pv = mc.pv;
		hv = mc.hv;
		movieTmp = mc.movieTmp;
		sdao = new ReservationDAO();
		
		eventUp();
	}

	private void eventUp() {
		sv.seat1.addActionListener(this);
		sv.seat2.addActionListener(this);
		sv.seat3.addActionListener(this);
		sv.seat4.addActionListener(this);
		sv.seat5.addActionListener(this);
		sv.btNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seatName = "";
				int cnt = 0;
				for (int i = 0; i < sv.jb.size(); i++) {
					if(sv.jb.get(i).getBackground() == Color.red) {
						if(cnt == 0) {
							seatName += sv.jb.get(i).getText();
							cnt++;
						} else {
							seatName += "," + sv.jb.get(i).getText();
							cnt++;
						}
					}
				}
				
				if(cnt == 0) {
					sv.showMsg("자리를 선택하세요!");
					return;
				}
				
				movieTmp.put("resSeat", seatName);
				movieTmp.put("quantity", cnt+"");
				
				sv.setVisible(false);
				pv.init(movieTmp);
				pv.setVisible(true);
			}
		});
		sv.btPrev.addActionListener(this);
		sv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ArrayList<MovieVO> list = new ReservationDAO().movieSearch();
				hv.movieBox.removeAll();
				for (int i = 0; i < list.size(); i++) {
					hv.movieBox.add(hv.addMoiveBox(list.get(i)));
					int s = i;
					hv.movieButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(!(movieTmp.get("id")==null)) {
								cv.displayTable(new ReservationDAO().findMovieTitle());
								cv.cbMovie.setSelectedIndex(s+1);
								hv.setVisible(false);
								cv.setVisible(true);
							}else {
								hv.showMsg("예매를 하시려면 로그인 하세요");
							}
						}
					});
				}
				sv.setVisible(false);
				hv.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == sv.seat1) {
			if(sv.seat1.getBackground() == Color.green) {
				sv.seat1.setBackground(Color.red);
			} else {
				sv.seat1.setBackground(Color.green);
			}
		
		} else if (ob == sv.seat2) {
			if(sv.seat2.getBackground() == Color.green) {
				sv.seat2.setBackground(Color.red);
			} else {
				sv.seat2.setBackground(Color.green);
			}
	
		} else if (ob == sv.seat3) {
			if(sv.seat3.getBackground() == Color.green) {
				sv.seat3.setBackground(Color.red);
			} else {
				sv.seat3.setBackground(Color.green);
			}
	
		} else if (ob == sv.seat4) {
			if(sv.seat4.getBackground() == Color.green) {
				sv.seat4.setBackground(Color.red);
			} else {
				sv.seat4.setBackground(Color.green);
			}
	
		} else if (ob == sv.seat5) {
			if(sv.seat5.getBackground() == Color.green) {
				sv.seat5.setBackground(Color.red);
			} else {
				sv.seat5.setBackground(Color.green);
			}
		}  

		else if (ob == sv.btPrev) {
			sv.resetSeat();
			sv.setVisible(false);
			cv.setVisible(true);
		}
	}

}