package com.movie.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import com.movie.dao.ReservationDAO;
import com.movie.view.ChoiceView;
import com.movie.view.PaymentView;
import com.movie.view.SeatView;

public class SeatController implements ActionListener {
	SeatView sv;
	ChoiceView cv;
	PaymentView pv;
	ReservationDAO sdao;
	String seatName;
	Map<String, String> movieTmp;

	public SeatController(MainController mc) {
		this.sv = mc.sv;
		this.cv = mc.cv;
		this.pv = mc.pv;
		movieTmp = mc.movieTmp;
		sdao = new ReservationDAO();
		
		
		eventUp();
		
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
				
				System.out.println(seatName);
				
				movieTmp.put("resSeat", seatName);
				movieTmp.put("quantity", cnt+"");
//				System.out.println(cnt+"");
//				System.out.println(movieTmp.get("resSeat"));
				//데이터 넘기기
				
				sv.setVisible(false);
				pv.init(movieTmp);
				pv.setVisible(true);
				
			}
		});
	}

	private void eventUp() {
		sv.seat1.addActionListener(this);
		sv.seat2.addActionListener(this);
		sv.seat3.addActionListener(this);
		sv.seat4.addActionListener(this);
		sv.seat5.addActionListener(this);
		sv.btNext.addActionListener(this);
		sv.btPrev.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == sv.seat1) {
			//System.out.println(sv.seat1.getBackground());
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

//	public static void main(String[] args) {
//		new SeatController();
//	}
}