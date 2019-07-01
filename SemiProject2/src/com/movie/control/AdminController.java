package com.movie.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.movie.dao.AdminDAO;
import com.movie.VO.OpenMovVO;
import com.movie.VO.ScheduleVO;
import com.movie.view.AdminView;
import com.movie.view.HomeView;

public class AdminController implements ActionListener,FocusListener {
	AdminView av;
	HomeView hv;

	public AdminController(MainController mc) {
		this.av = mc.av;
		this.hv = mc.hv;
		eventUp();
		AdminDAO dao = new AdminDAO();
		av.displayMovTable(dao.findMovAll());
		av.displayScheTable(dao.findScheAll());
		av.repaint();
	}

	private void eventUp() {
		av.btMovie.addActionListener(this);
		av.btSchedule.addActionListener(this);
		av.btLogout.addActionListener(this);
		av.btMovIns.addActionListener(this);
		av.btScheIns.addActionListener(this);
		av.btScheDel.addActionListener(this);
		av.btMovDel.addActionListener(this);
		av.btMovDel.addActionListener(this);
		av.btMovImg.addActionListener(this);
		av.btMovSyn.addActionListener(this);
		
		av.tfYear.addFocusListener(this);
		av.tfMonth.addFocusListener(this);
		av.tfDate.addFocusListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == av.btMovie) {
			AdminDAO dao = new AdminDAO();
			av.displayMovTable(dao.findMovAll()); // 영화테이블 표시
			av.movPanel.setVisible(true);
			av.schePanel.setVisible(false);

		} else if (ob == av.btSchedule) {
			AdminDAO dao = new AdminDAO();
//			av.displayScheTable(dao.findScheAll());// 스케줄테이블표시
			av.schePanel.setVisible(true);
			av.movPanel.setVisible(false);
			av.displayComMov(dao.scheComItemMov());
			av.displayComScreen(dao.scheComItemScreen());
		} else if (ob == av.btLogout) {
			if(av.showCon("로그아웃하시겠습니까?")==0) {
				av.setVisible(false);
				hv.setVisible(true);
			}
		} else if (ob == av.btMovIns) {
			String movTitle = av.tfMovTitle.getText();
			String movDir = av.tfMovDir.getText();
			String movAct = av.tfMovAct.getText();
			String movOpen = av.tfMovOpen.getText();
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
			System.out.println(movTitle + "," + movDir + "," + movAct + "," + movOpen);
			AdminDAO dao = new AdminDAO();
			if (dao.addMovie(vo)) {
				av.showMsg("영화가 등록되었습니다");
				av.displayMovTable(dao.findMovAll());
			}

		} else if (ob == av.btScheIns) {
			String selMov = av.scheComMov.getSelectedItem().toString();
			System.out.println(av.scheComMov.getSelectedItem().toString());
			if (av.scheComMov.getSelectedItem().toString().equals("<<< 영화  >>>")
					|| av.scheComSreen.getSelectedItem().toString().equals("<<< 상영관  >>>")
					|| av.scheComTime.getSelectedItem().toString().equals("<<< 시간 >>>")) {
				av.showMsg("값을 선택하세요!");
				return;
			}
			System.out.println(selMov);

			int selScreen = Integer.parseInt(av.scheComSreen.getSelectedItem().toString());
			String selDate = av.tfYear.getText() + "/" + av.tfMonth.getText() + "/" + av.tfDate.getText() + " "
					+ av.scheComTime.getSelectedItem().toString();
			ScheduleVO vo = new ScheduleVO();
			vo.setMovieTitle(selMov);
			vo.setScreenNum(selScreen);
			vo.setScreenDate(selDate);
			AdminDAO dao = new AdminDAO();
			if (dao.addSchedule(vo)) {
				av.showMsg("상영일정이 등록되었습니다.");
			}

		} else if (ob == av.btMovImg) {
			String paths = av.choose();
			av.tfMovImage.setText(paths);
		} else if (ob == av.btScheDel) {
			AdminDAO dao = new AdminDAO();
			int row = av.scheTable.getSelectedRow();
			System.out.println(row);
			if (row == -1) {// 데이터 선택 안했을때
				av.showMsg("삭제할 스케줄을 선택하세요");
				return;
			}

			int no = Integer.parseInt(av.scheTable.getValueAt(row, 0).toString());
			if (dao.removeSche(no)) {
				av.displayScheTable(dao.findScheAll());
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
				av.displayScheTable(dao.findScheAll());
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
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object ob = e.getSource();
		if(ob == av.tfYear) {
			if(av.tfYear.getText().trim().equals(""))
			av.tfYear.setText("yyyy");
		}else if(ob==av.tfMonth) {
			if(av.tfMonth.getText().trim().equals(""))
				av.tfMonth.setText("yyyy");
		}else if(ob==av.tfDate) {
			if(av.tfDate.getText().trim().equals(""))
				av.tfDate.setText("dd");
		}
	}

//	public static void main(String[] args) {
//		new AdminController();
//	}
}
