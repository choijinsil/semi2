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

import com.movie.VO.MovieVO;

public class ReservationDAO {

	Properties pro;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ArrayList<MovieVO> findMovieTitle() { 
		
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			
			String sql = "select MOVIETITLE from movie";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				MovieVO vo = new MovieVO(rs.getString("movietitle"));
				list.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public ReservationDAO() {
		try {
			pro = new Properties();
			pro.load(new FileReader("conn/conn.properties"));
			Class.forName(pro.getProperty("driver"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void connect() {
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void disconnect() {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
