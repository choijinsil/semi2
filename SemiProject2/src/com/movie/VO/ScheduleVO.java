package com.movie.VO;

public class ScheduleVO {
	private int scheduleNum;
	private String movieTitle;
	private int screenNum;
	private String screenDate;
	
	public ScheduleVO() {
		// TODO Auto-generated constructor stub
	}//생성자
	
	public ScheduleVO(int scheduleNum, String movieTitle, int screenNum, String screenDate) {
		super();
		this.scheduleNum = scheduleNum;
		this.movieTitle = movieTitle;
		this.screenNum = screenNum;
		this.screenDate = screenDate;
	}

	public int getScheduleNum() {
		return scheduleNum;
	}
	public void setScheduleNum(int scheduleNum) {
		this.scheduleNum = scheduleNum;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public int getScreenNum() {
		return screenNum;
	}
	public void setScreenNum(int screenNum) {
		this.screenNum = screenNum;
	}
	public String getScreenDate() {
		return screenDate;
	}
	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}
	
	
}
