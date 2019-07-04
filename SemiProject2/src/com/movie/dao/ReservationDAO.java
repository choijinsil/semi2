package com.movie.dao;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.movie.VO.MemberVO;
import com.movie.VO.MovieVO;
import com.movie.VO.SeatVO;

public class ReservationDAO {

	Properties pro;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	public ReservationDAO() {
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
	}

	public ArrayList<MovieVO> findMovieTitle() {
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			String sql = "select m.MOVIETITLE movietitle, m.MOVIENUM movienum, m.movieImage movieImage, m.synopsis synopsis from movie m"
					+ " where m.movienum = any (select s.movienum from schedule s where s.screendate > sysdate)";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMovieTitle(rs.getString("movietitle"));
				vo.setMovieNum(rs.getInt("movienum"));
				vo.setSynopsis(rs.getString("synopsis"));

				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				InputStream in = rs.getBinaryStream("movieImage");
				byte[] buf = new byte[1024];
				int read = 0;
				while ((read = in.read(buf, 0, buf.length)) != -1) {
					bout.write(buf, 0, read);
				}

				vo.setMovieImage(new ImageIcon(bout.toByteArray()));
				in.close();
				bout.close();

				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public ArrayList<MovieVO> findScreenDate(String selMovie) {

		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			String sql = "select to_char(screenDate, 'yyyy/MM/dd hh24:mi') as screenDate"
					+ " from (select screenDate from schedule natural join movie"
					+ " where movieTitle = ?) where sysdate < screenDate";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selMovie);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setScreenDate(rs.getString("screenDate"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public int findScheduleNum(String selMovie, String selDate) {
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			String sql = "select ScheduleNum num from schedule  natural join movie "
					+ "where movieTitle = ? and screenDate = to_date (?,'yyyy/mm/dd hh24:mi')";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selMovie);
			pstmt.setString(2, selDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setScheduleNum(rs.getInt("num"));
				return vo.getScheduleNum();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;
	}

	public ArrayList<MovieVO> movieSearch() {
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			String sql = "select m.movienum num, m.movietitle title, m.movieimage image from movie m"
					+ " where m.movienum = any (select s.movienum from schedule s where s.screendate > sysdate)"
					+ " order by m.totalviewer desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMovieNum(rs.getInt("num"));
				vo.setMovieTitle(rs.getString("title"));
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				InputStream in = rs.getBinaryStream("image");
				byte[] buf = new byte[1024];
				int read = 0;
				while ((read = in.read(buf, 0, buf.length)) != -1) {
					bout.write(buf, 0, read);
				}
				vo.setMovieImage(new ImageIcon(bout.toByteArray()));
				in.close();
				bout.close();
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {

		} finally {
			disconnect();
		}
		return list;
	}

	public MemberVO loginDAO(String id, String pwd) {
		connect();
		MemberVO vo = new MemberVO();
		try {
			String sql = "select memberNum as num, memberName as name, totalcnt as cnt from membership where id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setMemberNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setTotalCnt(rs.getInt("cnt"));
				vo.setId(id);
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return null;
	}

	public boolean signUp(MemberVO vo) {
		connect();
		try {
			String sql = "insert into membership values (memberSeq.nextval,?,?,?,?,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public int checkID(String id) {
		connect();
		try {
			String sql = "select count(*) as cnt from membership where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt("cnt") < 1)
				return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return -1;
	}

	public int checkPassword(String loginId, String password) {
		connect();
		try {
			String sql = "select count(*) from membership where id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("count(*)");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;
	}

	public boolean saveReservation(Map<String, String> movieTmp) {
		connect();
		try {
			String resSeat = movieTmp.get("resSeat");
			String scheduleNum = movieTmp.get("scheduleNum");
			String memberNum = movieTmp.get("memberNum");
			String quantity = movieTmp.get("quantity");
			String totalCnt = movieTmp.get("totalCnt");

			String sql = "insert into reservation values (?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(memberNum + totalCnt));
			pstmt.setInt(2, Integer.parseInt(scheduleNum));
			pstmt.setInt(3, Integer.parseInt(memberNum));
			pstmt.setInt(4, Integer.parseInt(quantity));
			pstmt.setString(5, resSeat);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public boolean UpdateTotalCnt(Map<String, String> movieTmp) {
		connect();
		try {
			String sql = "update membership set totalCnt=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(movieTmp.get("totalCnt")));
			pstmt.setString(2, movieTmp.get("id"));
			int t = pstmt.executeUpdate();
			if (t == 1)
				return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public ArrayList<MovieVO> findReservationInfo(String loginId) {
		connect();
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		try {
			String sql = "select r.resNum resNum, m.movieTitle movieTitle, to_char(s.screenDate,'yyyy/mm/dd hh24:mi') screenDate, r.resSeat resSeat from movie m inner join schedule s on m.movienum = s.movienum inner join reservation r on s.schedulenum = r.schedulenum inner join membership ms on r.memberNum = ms.memberNum WHERE id = ? order by s.screendate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MovieVO mv = new MovieVO(rs.getInt("resNum"), rs.getString("movieTitle"), rs.getString("screenDate"),
						rs.getString("resSeat"));
				list.add(mv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public boolean updateTotalViewer(Map<String, String> movieTmp) {
		connect();
		try {
			String sql = "update movie set totalviewer = totalviewer + ? where movieNum = (select movieNum from schedule where scheduleNum = ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(movieTmp.get("quantity")));
			pstmt.setString(2, movieTmp.get("scheduleNum"));
			int t = pstmt.executeUpdate();
			if (t == 1)
				return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
	}

	public Vector<SeatVO> seatState(int scheduleNum) {
		Vector<SeatVO> vo = new Vector<>();
		connect();
		try {
			String sql = "select s.screennum screennum, r.resseat resseat from schedule s, reservation r where r.scheduleNum=? and r.scheduleNum=s.scheduleNum"; // scheduleNum
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SeatVO svo = new SeatVO();
				svo.setScreenNum("" + rs.getInt("screennum"));
				svo.setSeatName(rs.getString("resseat"));
				vo.add(svo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	public int[] findDeleteDataByResNum(int resNum) {
		connect();
		int[] deleteData = new int[2];
		try {
			String sql = "select s.movienum movienum, r.quantity quantity from reservation r inner join schedule s " + 
					"on r.schedulenum = s.schedulenum " + 
					"where r.resnum = ?";
			pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, resNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
			deleteData[0] = rs.getInt("quantity");
			deleteData[1] = rs.getInt("movienum");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return deleteData;
	}
	
	public void deleteTotalViewer(int[] deleteData) {
	connect();
	try {
	String sql = "update movie set totalViewer = totalviewer - ? where movienum = ?";
	pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, deleteData[0]);
		pstmt.setInt(2, deleteData[1]);
	 pstmt.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		disconnect();
	}
}
	
	public boolean deleteReservation(int resNum) {
		connect();
		try {
			String sql = "delete from reservation r where r.resnum = ? and (select count(*) cnt from schedule s where s.schedulenum = ( select r.schedulenum from reservation r where r.resnum = ?) and s.screendate > sysdate)=1";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, resNum);
			pstmt.setInt(2, resNum);
			int t = pstmt.executeUpdate();
			if (t == 1) 
				return true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return false;
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

//	public int checkdelReservation(int resNum) {
//  connect();
//  try {
//     String sql = "select count(*) cnt from schedule s where s.schedulenum = ( select r.schedulenum from reservation r where r.resnum = ?) and s.screendate > sysdate";
//     pstmt = conn.prepareStatement(sql);
//     pstmt.setInt(1, resNum);
//     rs = pstmt.executeQuery();
//     if (rs.next())
//        return rs.getInt("cnt");
//  } catch (NumberFormatException e) {
//     e.printStackTrace();
//  } catch (SQLException e) {
//     e.printStackTrace();
//  } finally {
//     disconnect();
//  }
//  return 0;
//}	
	
//	public void deleteTotalViewer(int resNum) {
//		connect();
//		try {
//		String sql = "update movie " + 
//				"set totalViewer = totalViewer - " + 
//				"(select quantity from reservation where resNum = ?) " + 
//				"where movieNum = (select s.movieNum from schedule s " + 
//				"inner join reservation r " + 
//				"on s.scheduleNum = r.scheduleNum " + 
//				"where r.resNum = ?)";
//		pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, resNum);
//			pstmt.setInt(2, resNum);
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//	}	
	
}
