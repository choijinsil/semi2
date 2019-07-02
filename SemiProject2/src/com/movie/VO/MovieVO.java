package com.movie.VO;

public class MovieVO {
	int quantity;
	int resNum;
	int movieNum; // 영화번호
	String movieTitle;
	
	String screenDate;
	String screenTime;
	String seatName;
	private int scheduleNum;
	public int getScheduleNum() {
		return scheduleNum;
	}

	public void setScheduleNum(int scheduleNum) {
		this.scheduleNum = scheduleNum;
	}

	public String getResSeat() {
		return resSeat;
	}

	public void setResSeat(String resSeat) {
		this.resSeat = resSeat;
	}
	private String resSeat;
	
	public MovieVO() {
	}
	
	public MovieVO(String movieTitle) {
		this.movieTitle= movieTitle;
	}
	
	public MovieVO(int quantity, int resNum, int movieNum, String movieTitle, String screenDate, String screenTime, String seatName) {
		super();
		this.quantity = quantity;
		this.resNum = resNum;
		this.movieNum = movieNum;
		this.movieTitle = movieTitle;
		this.screenDate = screenDate;
		this.screenTime = screenTime;
		this.seatName = seatName;
	}
	
//////////////////////////////////moon 예약정보뷰에 뿌


	public MovieVO(int resNum, int scheduleNum, String resSeat) {
	this.resNum = resNum;
	this.scheduleNum = scheduleNum;
	this.resSeat = resSeat;
	}
	
	public MovieVO(int resNum, String movieTitle, String screenDate, String resSeat) {
	super();
	this.resNum = resNum;
	this.movieTitle = movieTitle;
	this.screenDate = screenDate;
	this.resSeat = resSeat;
	}

//////////////////////////////////////////////////////////////
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getResNum() {
		return resNum;
	}
	public void setResNum(int resNum) {
		this.resNum = resNum;
	}
	
	public int getMovieNum() {
		return movieNum;
	}
	public void setMovieNum(int movieNum) {
		this.movieNum = movieNum;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	public String getScreenDate() {
		return screenDate;
	}
	public void setScreenDate(String screenDate) {
		this.screenDate = screenDate;
	}
	public String getScreenTime() {
		return screenTime;
	}
	public void setScreenTime(String screenTime) {
		this.screenTime = screenTime;
	}
	public String getSeatName() {
		return seatName;
	}
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	
	
	
	
}
