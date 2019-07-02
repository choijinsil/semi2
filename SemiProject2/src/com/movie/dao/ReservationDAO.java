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
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import com.movie.VO.MemberVO;
import com.movie.VO.MovieVO;
import com.movie.VO.SeatVO;

public class ReservationDAO {

	Properties pro;

	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;

	
	public ArrayList<MovieVO> findMovieTitle() { 
		
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		
		try {
			
			String sql = "select MOVIETITLE, MOVIENUM from movie";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(); 

			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMovieTitle(rs.getString("movietitle"));
				vo.setMovieNum(rs.getInt("movienum"));
				list.add(vo);
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	
//	//영화 시간 - 용진
//	public ArrayList<MovieVO> findScreenDate(String selMovie) { 
//		
//		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
//		connect();
//		try {
//			// to_char(screenDate,'yyyy/mm/dd hh24')
//			
//			String sql = "select screenDate " + "from schedule " + "natural join movie " + 
//					"where movieTitle = '" + selMovie + "'";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery(); 
//			while (rs.next()) {
//				MovieVO vo = new MovieVO();
//				vo.setScreenDate(rs.getString("screenDate"));
//				System.out.println(vo.getScreenDate());
//				list.add(vo);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			disconnect();
//		}
//		return list;
//	}
	//영화 시간
	public ArrayList<MovieVO> findScreenDate(String selMovie) { 
		
		ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		connect();
		try {
			// to_char(screenDate,'yyyy/mm/dd hh24')
			
			String sql = "select to_char(screenDate, 'yyyy/MM/dd hh24') as screenDate from schedule natural join movie where movieTitle = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, selMovie);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setScreenDate(rs.getString("screenDate"));
//				System.out.println("screenDate출력값>>>"+vo.getScreenDate());
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}
	
	//스캐쥴 넘버 조회
		public ArrayList<MovieVO> findScheduleNum(String selMovie, String selDate) { 
			
			ArrayList<MovieVO> list = new ArrayList<MovieVO>();
			connect();
			
			try {
				
				String sql = "select ScheduleNum num from schedule  natural join movie " + 
						"where movieTitle = ? and screenDate = to_date (?,'yyyy/mm/dd hh24')";
			
				
//				java.sql.Date date=	new java.sql.Date(selDate.getTime()); 
				
//				System.out.println("sql의 Date형식"+date);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, selMovie); 
//				pstmt.setDate(2, date);
				pstmt.setString(2, selDate);
				rs = pstmt.executeQuery(); 
				
				while (rs.next()) {
					MovieVO vo = new MovieVO();
//					vo.setScreenDate(rs.getString("scheduleNum"));
					vo.setScheduleNum(rs.getInt("num"));
					list.add(vo);
					System.out.println("scheduleNum>>"+vo.getScheduleNum());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}

//		//스캐쥴 넘버 조회 - 용진
//				public ArrayList<MovieVO> findScheduleNum(String selMovie, Date selDate) { 
//					
//					ArrayList<MovieVO> list = new ArrayList<MovieVO>();
//					connect();
//					try {
//						
//						String sql = "select ScheduleNum " + "from schedule " + 
//								"where movieTitle = '" + selMovie + "' and screenDate = '" + selDate +"'"  ;
//						pstmt = conn.prepareStatement(sql);
//						rs = pstmt.executeQuery(); 
//						while (rs.next()) {
//							MovieVO vo = new MovieVO();
//							vo.setScreenDate(rs.getString("scheduleNum"));
//							list.add(vo);
//						}
//
//					} catch (SQLException e) {
//						e.printStackTrace();
//					} finally {
//						disconnect();
//					}
//					return list;
//				}
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
	public String[][] movieSearch() {
		String[][] str = {{"al","알라딘"},{"toy","토이스토리"},{"bug","기생충"}};

		return str; 
	}
	public MemberVO loginDAO(String id, String pwd) {
		connect();
		MemberVO vo = new MemberVO();
		try {
			String sql ="select memberNum as num, memberName as name from membership where id=? and password=?";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setMemberNum(rs.getInt("num"));
				vo.setName(rs.getString("name"));
				vo.setId(id);
				return vo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return null;
	}
	
	public boolean signUp(MemberVO vo) {
		connect();
		try {
			String sql = "insert into membership values (9999,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPwd());
				pstmt.setString(3, vo.getName());
				pstmt.setString(4, vo.getPhone());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			disconnect();			
		}
		return false;
	}
	
	public int checkID(String id) {
		connect();
		
		try {
			String sql = "select count(*) as cnt from membership where id = ?";
			pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("cnt")<1) return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return -1;
	}
	////////////////////////////////////////////////////////////////////////////////////////박문하 시작
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
		         System.out.println("1");
		         String scheduleNum = movieTmp.get("scheduleNum");
		         System.out.println("2");
		         String memberNum = movieTmp.get("memberNum");
		         System.out.println("3");
		         String quantity = movieTmp.get("quantity");
		         System.out.println("4");
		         String totalCnt = movieTmp.get("totalCnt");
		         
		         String sql = "insert into reservation values (?,?,?,?,?)";
		         pstmt = conn.prepareStatement(sql);
		            pstmt.setInt(1, Integer.parseInt(memberNum+totalCnt));
		            pstmt.setInt(2, Integer.parseInt(scheduleNum));
		            pstmt.setInt(3, Integer.parseInt(memberNum));
		            pstmt.setInt(4, Integer.parseInt(quantity));
		            pstmt.setString(5, resSeat);
		            System.out.println("5");
		         pstmt.executeUpdate();
		         System.out.println("6");
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
			         if(t==1) return true;
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
			         String sql = "select r.resNum resNum, m.movieTitle movieTitle, s.screenDate screenDate, s.screenTime screenTime, r.resSeat resSeat from movie m inner join schedule s on m.movienum = s.movienum inner join reservation r on s.schedulenum = r.schedulenum inner join membership ms on r.memberNum = ms.memberNum WHERE id = ?";
			         pstmt = conn.prepareStatement(sql);
			            pstmt.setString(1, loginId);
			         rs = pstmt.executeQuery();
			         while(rs.next()) {
			            MovieVO mv = new MovieVO(rs.getInt("resNum"), rs.getString("movieTitle"),rs.getString("screenDate"),rs.getString("screenTime"),rs.getString("resSeat"));
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
			         //String sql = "update (movie m   inner join schedule s on m.movieNum = s.movieNum) set m.totalViewer = m.totalViewer+? where s.scheduleNum = ?";
			         String sql = "update movie set totalviewer = totalviewer + ? where movieNum = (select movieNum from schedule where scheduleNum = ?);";
			         pstmt = conn.prepareStatement(sql);
			            pstmt.setInt(1, Integer.parseInt(movieTmp.get("quantity")));
			            pstmt.setString(2, movieTmp.get("scheduleNum"));
			         int t = pstmt.executeUpdate();
			         if(t==1) return true;
			      } catch (NumberFormatException e) {
			         e.printStackTrace();
			      } catch (SQLException e) {
			         e.printStackTrace();
			      } finally {
			         disconnect();
			      }
			      return false;
			   }
			   
			   public boolean deleteReservation(int resNum) {
			      connect();
			      try {
			      String sql = "delete from reservation where resnum = ?";
			      pstmt = conn.prepareStatement(sql);
			         pstmt.setInt(1, resNum);
			      int t = pstmt.executeUpdate();
			      if(t==1) return true;
			      } catch (NumberFormatException e) {
			         e.printStackTrace();
			      } catch (SQLException e) {
			         e.printStackTrace();
			      } finally {
			         disconnect();
			      }
			      return false;
			   }
	//////////////////////////////////////////////////////////////////////박문하 끝
   /////////////////////////////////////////////////////////////////////////// 유하나 시작
	public Vector<SeatVO> seatState(int scheduleNum) {
		Vector<SeatVO> vo = new Vector<>();
		connect();
		try {
			String sql = "select s.screennum screennum, r.resseat resseat from schedule s, reservation r where r.scheduleNum=? and r.scheduleNum=s.scheduleNum"; //scheduleNum
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleNum);
			rs = pstmt.executeQuery();
			System.out.println(scheduleNum);
			while (rs.next()) {
				SeatVO svo = new SeatVO();
				svo.setScreenNum(""+rs.getInt("screennum"));
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
	/////////////////////////////////////////////////////////////////////////// 유하나 끝
	private void connect() {
		try {
			conn = DriverManager.getConnection(pro.getProperty("url"), pro);
//			System.out.println("conn 성공!");
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
