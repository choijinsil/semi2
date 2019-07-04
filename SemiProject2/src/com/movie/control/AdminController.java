package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.movie.dao.AdminDAO;
import com.movie.dao.ReservationDAO;
import com.movie.VO.MovieVO;
import com.movie.VO.OpenMovVO;
import com.movie.VO.ScheduleVO;
import com.movie.view.AdminView;
import com.movie.view.ChoiceView;
import com.movie.view.HomeView;

public class AdminController implements ActionListener,FocusListener {
	AdminView av;
	HomeView hv;
	ChoiceView cv;
	Map<String, String>movieTmp;

	public AdminController(MainController mc) {
		this.av = mc.av;
		this.hv = mc.hv;
		movieTmp= mc.movieTmp;
		cv = mc.cv;
		
		eventUp();
		AdminDAO dao = new AdminDAO();
		av.displayMovTable(dao.findMovAll());
		av.displayScheTable(dao.findScheAll());
		av.repaint();
	}

	private void eventUp() {
		av.scheComSreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(av.scheComSreen.getSelectedItem()==null) {
					return;
				}
				av.displayTime((String)av.scheComSreen.getSelectedItem());
			}
		});
		
		av.btMovie.addActionListener(this);
		av.btSchedule.addActionListener(this);
		av.btLogout.addActionListener(this);
		av.btMovIns.addActionListener(this);
		av.btScheIns.addActionListener(this);
		av.btScheDel.addActionListener(this);
		av.btMovDel.addActionListener(this);
		av.btMovImg.addActionListener(this);
		av.btMovSyn.addActionListener(this);
		
		av.tfYear.addFocusListener(this);
		av.tfMonth.addFocusListener(this);
		av.tfDate.addFocusListener(this);
		
		av.tfMovOpen.addFocusListener(this);
		
		av.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(av.showCon("로그아웃하시겠습니까?")==0) {
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
					av.setVisible(false);
					hv.setVisible(true);
					hv.signInButton.requestFocus();
					av.initMovie();
					av.initSchedule();
					av.movPanel.setVisible(true);
					av.schePanel.setVisible(false);
				}
			}
		});
		
		
		av.tfYear.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//
//				keyReleased(e);
//			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (av.tfYear.getText().length() == 4) {
					av.tfMonth.requestFocus();
				}
			}
		});
		
		av.tfMonth.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				keyReleased(e);
//			}
			@Override
			public void keyReleased(KeyEvent e) {
				if (av.tfMonth.getText().matches("[\\d]+")) {
					if (Integer.parseInt(av.tfMonth.getText()) > 1) {
						av.tfDate.requestFocus();
					}
				}
				if (av.tfMonth.getText().length() == 2) {
					av.tfDate.requestFocus();
				}
			}
		});
		
		av.tfDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (av.tfDate.getText().matches("[\\d]+")) {
					if (Integer.parseInt(av.tfDate.getText()) > 3) {
						av.scheComTime.requestFocus();
					}
				}
				if (av.tfDate.getText().length() == 2) {
					av.scheComTime.requestFocus();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == av.btMovie) {
			AdminDAO dao = new AdminDAO();
			av.displayMovTable(dao.findMovAll()); // 영화테이블 표시
			av.movPanel.setVisible(true);
			av.schePanel.setVisible(false);
			av.initSchedule();

		} else if (ob == av.btSchedule) {
			AdminDAO dao = new AdminDAO();
			av.schePanel.setVisible(true);
			av.movPanel.setVisible(false);
			av.displayComMov(dao.scheComItemMov());
			av.displayComScreen(dao.scheComItemScreen());
			av.initMovie();

		} else if (ob == av.btLogout) {
			if(av.showCon("로그아웃하시겠습니까?")==0) {
				ArrayList<MovieVO> list = new ReservationDAO().movieSearch();
				hv.movieBox.removeAll();
				for (int i = 0; i < list.size(); i++) {
					hv.movieBox.add(hv.addMoiveBox(list.get(i)));
					hv.movieButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(!(movieTmp.get("id")==null)) {
								cv.displayTable(new ReservationDAO().findMovieTitle());
								hv.setVisible(false);
								cv.setVisible(true);
							}else {
								hv.showMsg("예매를 하시려면 로그인 하세요");
							}
						}
					});
				}
				av.setVisible(false);
				hv.setVisible(true);
				hv.signInButton.requestFocus();
				av.initMovie();
				av.initSchedule();
				av.movPanel.setVisible(true);
				av.schePanel.setVisible(false);
			}
			
		} else if (ob == av.btMovIns) {
			String movTitle = av.tfMovTitle.getText();
			String movDir = av.tfMovDir.getText();
			String movAct = av.tfMovAct.getText();
			String movOpen = av.tfMovOpen.getText();
			String movImg = av.tfMovImage.getText();
			String movSyn = av.tfMovSyn.getText();
			
			if (movTitle.equals("") || movDir.equals("") || movAct.equals("") || movOpen.equals("YYYY-MM-DD") || movImg.equals("")
		               || movSyn.equals("")) {
		            av.showMsg("영화 정보를 모두 입력해주세요!");
		            return;
		         }else if (!movOpen.matches("[\\d]{4}-[\\d]{2}-[\\d]{2}")) {
		            av.showMsg("올바른 일정을 입력해주세요");
		            return;
		         }else if(!movSyn.matches("^\\S+.(txt)$")) {
		            av.showMsg("영화줄거리는 'txt' 파일만 등록가능합니다");
		            return;
		         }else if(!movImg.matches("^\\S+.(jpg)$")) {
		            av.showMsg("영화포스터는 'jpg' 파일만 등록가능합니다");
		            return;
		         }
			
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime openDate;
			try {
				openDate = LocalDateTime.parse(movOpen+"T00:00:00");
			} catch (Exception e1) {
				av.showMsg("잘못된 일정입니다.\n일정을 확인하세요.");
				return;
			}
			if (!openDate.isAfter(today)) {
				av.showMsg("잘못된 일정입니다.\n일정을 확인하세요.");
				return;
			} 
			
			ByteArrayOutputStream bout = new ByteArrayOutputStream();

			try {
				FileInputStream fin = new FileInputStream(new File(av.tfMovImage.getText()));
				byte[] buf = new byte[1024];
				int read = 0;
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					bout.write(buf, 0, read);
				}
				fin.close();
			} catch (FileNotFoundException er) {
				er.printStackTrace();
			} catch (IOException er) {
				er.printStackTrace();
			}

			bout.toByteArray();
			OpenMovVO vo = new OpenMovVO();
			vo.setMovieTitle(movTitle);
			vo.setDirector(movDir);
			vo.setMainActor(movAct);
			vo.setOpeningDate(movOpen);
			vo.setMovieImg(bout.toByteArray());
			try {
				FileInputStream fin = new FileInputStream(new File(av.tfMovSyn.getText()));
				byte[] buf = new byte[1024];
				int read = 0;
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					bout.write(buf, 0, read);
				}
				fin.close();
			} catch (FileNotFoundException er) {
				er.printStackTrace();
			} catch (IOException er) {
				er.printStackTrace();
			}
			vo.setMovieSyn(bout.toString());
			AdminDAO dao = new AdminDAO();
			if (dao.addMovie(vo)) {
				av.showMsg("영화가 등록되었습니다.");
				av.displayMovTable(dao.findMovAll());
				av.initMovie();
			}

		} else if (ob == av.btScheIns) {
			AdminDAO dao = new AdminDAO();
			String selMov = av.scheComMov.getSelectedItem().toString();
			if (av.scheComMov.getSelectedItem().toString().equals("<<< 영화  >>>")
					|| av.scheComSreen.getSelectedItem().toString().equals("<<< 상영관  >>>")
					|| av.scheComTime.getSelectedItem().toString().equals("<<< 시간 >>>")) {
				av.showMsg("값을 선택하세요!");
				return;
			}
			String year = av.tfYear.getText();
			String month = av.tfMonth.getText();
			if(month.length()==1) {
				month = "0" + month;
			}
			String date = av.tfDate.getText();
			if(date.length()==1) {
				date = "0" + date;
			}
			
			if (!year.matches("[\\d]{4}") || !month.matches("[\\d]{2}") || !date.matches("[\\d]{2}")) {
				av.showMsg("잘못된 일정입니다.\n일정을 확인하세요.");
				return;
			}
			
			LocalDateTime today = LocalDateTime.now();
			String opening = dao.findOpeningDateByMovieTitle((String)av.scheComMov.getSelectedItem());
			LocalDateTime openingDate;
			LocalDateTime insertDate;
			try {
				openingDate = LocalDateTime.parse(opening+"T00:00:00");
				insertDate = LocalDateTime.parse(year+"-"+month+"-"+date+"T00:00:00");
			} catch (Exception e1) {
				av.showMsg("잘못된 일정입니다.\n영화 개봉일을 확인하세요.");
				return;
			}
			
			if(!insertDate.isAfter(openingDate)) {
				av.showMsg("잘못된 일정입니다.\n영화 개봉일을 확인하세요.");
				return;
			}
			
			if (!insertDate.isAfter(today)) {
				av.showMsg("잘못된 일정입니다.\n일정을 확인하세요.");
				return;
			} 
			
			int selScreen = Integer.parseInt(av.scheComSreen.getSelectedItem().toString());
			String selDate = av.tfYear.getText() + "/" + av.tfMonth.getText() + "/" + av.tfDate.getText() + " "
					+ av.scheComTime.getSelectedItem().toString();
			ScheduleVO vo = new ScheduleVO();
			vo.setMovieTitle(selMov);
			vo.setScreenNum(selScreen);
			vo.setScreenDate(selDate);
			if (dao.addSchedule(vo)) {
				av.showMsg("상영일정이 등록되었습니다.");
				av.displayScheTable(dao.findScheAll());
				av.initSchedule();
			} else {
				av.showMsg("등록에 실패하였습니다.\n다시 확인해주세요.");
			}

		} else if (ob == av.btMovImg) {
			String paths = av.choose();
			av.tfMovImage.setText(paths);
			
		} else if (ob == av.btScheDel) {
			AdminDAO dao = new AdminDAO();
			int row = av.scheTable.getSelectedRow();
			if (row == -1) {// 데이터 선택 안했을때
				av.showMsg("삭제할 스케줄을 선택하세요");
				return;
			}
			
			int no = Integer.parseInt(av.scheTable.getValueAt(row, 0).toString());
			if (dao.removeSche(no)) {
				av.showMsg("스케줄이 삭제되었습니다.");
				av.displayScheTable(dao.findScheAll());
			} else {
			av.showMsg("삭제할 수 없는 스케줄입니다");
			}
		} else if (ob == av.btMovDel) {
			AdminDAO dao = new AdminDAO();
			int row = av.movTable.getSelectedRow();
			if (row == -1) {// 데이터 선택 안했을때
				av.showMsg("삭제할 영화를 선택하세요");
				return;
			}
			int no = Integer.parseInt(av.movTable.getValueAt(row, 0).toString());
			if (dao.removeMov(no)) {
				av.showMsg("영화가 삭제되었습니다.");
				av.displayMovTable(dao.findMovAll());
			}else {
				av.showMsg("삭제할 수 없는 영화입니다");
				}
			
		} else if (ob == av.btMovSyn) {
			String paths = av.chooseSynopsis();
			av.tfMovSyn.setText(paths);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == av.tfYear) {
			av.tfYear.setText("");
		}else if(ob==av.tfMonth) {
			av.tfMonth.setText("");
		}else if(ob==av.tfDate) {
			av.tfDate.setText("");
		}else if(ob==av.tfMovOpen) {
	         av.tfMovOpen.setText("");
	      }
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == av.tfYear) {
			if(av.tfYear.getText().trim().equals("")) {
			av.tfYear.setText("YYYY");}
//			else if(av.tfYear.getText().length() == 5) {
//				av.tfYear.setText(av.tfYear.getText().substring(0, 4));
//			}
		}else if(ob==av.tfMonth) {
			if(av.tfMonth.getText().trim().equals("")) {
				av.tfMonth.setText("MM");}
//			else if(av.tfMonth.getText().length() == 3)
		}else if(ob==av.tfDate) {
			if(av.tfDate.getText().trim().equals(""))
				av.tfDate.setText("DD");
		}else if(ob==av.tfMovOpen) {
	         if(av.tfMovOpen.getText().trim().equals(""))
	             av.tfMovOpen.setText("YYYY-MM-DD");
	       }
	}

}
