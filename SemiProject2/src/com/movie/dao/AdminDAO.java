package com.movie.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.movie.VO.OpenMovVO;
import com.movie.VO.ScheduleVO;

public class AdminDAO {
	Connection conn;
	PreparedStatement stmt;
	ResultSet rs;
	Properties pro;

	OpenMovVO mvo;
	ScheduleVO svo;

	public AdminDAO() {
		try {
			pro = new Properties();
			pro.load(new FileReader("SemiProject2/conn/conn.properties"));
			Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}// 생성자

	// 등록된 영화 정보
	public ArrayList<OpenMovVO> findMovAll() {
		ArrayList<OpenMovVO> list = new ArrayList<OpenMovVO>();
		connect();
		try {
			String sql = "select movieNum, movieTitle, totalViewer, director, mainActor, openingDate from Movie";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) { // 행 뽑기
				mvo = new OpenMovVO();
				mvo.setMovieNum(rs.getInt("movieNum"));
				mvo.setMovieTitle(rs.getString("movieTitle"));
				mvo.setTotalViewer(rs.getInt("totalViewer"));
				mvo.setDirector(rs.getString("director"));
				mvo.setMainActor(rs.getString("mainActor"));
				mvo.setOpeningDate(rs.getString("openingDate"));
				list.add(mvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	} // findMovAll

	// 등록된 스케줄 정보
	public ArrayList<ScheduleVO> findScheAll() {
		connect();
		ArrayList<ScheduleVO> list = new ArrayList<ScheduleVO>();
		try {
			String sql = "select s.scheduleNum scheduleNum, m.movieTitle movieTitle, s.screenNum screenNum, to_char(s.screenDate,'yyyy/mm/dd hh24 \"시\"') screenDate from Movie m, Schedule s where m.movieNum = s.movieNum order by scheduleNum desc";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				svo = new ScheduleVO();
				svo.setScheduleNum(rs.getInt("scheduleNum"));
				svo.setMovieTitle(rs.getString("movieTitle"));
				svo.setScreenNum(rs.getInt("screenNum"));
				svo.setScreenDate(rs.getString("screenDate"));
				list.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	} // findScheAll

	public ArrayList<String> scheComItemMov() {
		connect();
		ArrayList<String> movList = new ArrayList<String>();
		try {
			String sql = "select movieTitle from Movie";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				movList.add(rs.getString("movieTitle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return movList;
	}// 스케줄추가할영화선택

	public ArrayList<String> scheComItemScreen() {
		connect();
		ArrayList<String> screenList = new ArrayList<String>();
		try {
			String sql = "select screenNum from Screen";
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				screenList.add(rs.getString("screenNum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return screenList;
	}// 스케줄추가할상영관

	public boolean removeMov(int movNum) {
		connect();
		try {
			String sql = "delete from movie where movieNum = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, movNum);
			int t = stmt.executeUpdate();
			if (t == 1)
				return true;
		} catch (SQLException e) {
			return false;
		} finally {
			disconnect();
		}
		return false;
	}// removeMov

	public boolean removeSche(int scheNum) {
		connect();
		try {
			String sql = "delete from Schedule where scheduleNum = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, scheNum);
			int t = stmt.executeUpdate();
			if (t == 1)
				return true;
		} catch (SQLException e) {
			return false;
		} finally {
			disconnect();
		}
		return false;
	}// removeSche

	public boolean addMovie(OpenMovVO vo) {
		connect();
		try {
			String sql = "insert into Movie values (movieSeq.nextval,?,0,?,?,(to_date(?,'yyyy/mm/dd')),?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getMovieTitle());
			stmt.setString(2, vo.getDirector());
			stmt.setString(3, vo.getMainActor());
			stmt.setString(4, vo.getOpeningDate());
			stmt.setString(5, vo.getMovieSyn());
			stmt.setBytes(6, vo.getMovieImg());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}// 영화 추가하기

	public boolean addSchedule(ScheduleVO vo) {
		connect();
		try {
			String sql = "insert into schedule values (scheduleSeq.nextval,(select movienum from movie where movietitle = ?),?,(to_date(?,'yyyy/mm/dd HH24:mi')))";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, vo.getMovieTitle());
			stmt.setInt(2, vo.getScreenNum());
			stmt.setString(3, vo.getScreenDate());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			return false;
		} finally {
			disconnect();
		}
	}// 스케줄 추가하기
	
	public String findOpeningDateByMovieTitle(String movieTitle) {
		connect();
		try {
		String sql = "select to_char(openingDate,'yyyy-mm-dd') openingDate from movie where movietitle = ?";
		stmt = conn.prepareStatement(sql);
			stmt.setString(1, movieTitle);
		rs = stmt.executeQuery();
			rs.next();
			return rs.getString("openingDate");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return "";
	}

	private void connect() {// 연결객체생성
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {// DB자원반환
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
