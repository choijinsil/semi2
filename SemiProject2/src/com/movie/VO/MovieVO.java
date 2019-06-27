package com.movie.VO;

public class MovieVO {
	int quantity;
	int resNum;
	String movieTitle;
	String screenDate;
	String screenTime;
	String seatName;
	
	public MovieVO() {
		this.movieTitle= movieTitle;
	}
	
	public MovieVO(String movieTitle) {
		this.movieTitle= movieTitle;
	}
	
	public MovieVO(int quantity, int resNum, String movieTitle, String screenDate, String screenTime, String seatName) {
		super();
		this.quantity = quantity;
		this.resNum = resNum;
		this.movieTitle = movieTitle;
		this.screenDate = screenDate;
		this.screenTime = screenTime;
		this.seatName = seatName;
	}
	
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
