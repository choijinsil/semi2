package com.movie.VO;

import javax.swing.ImageIcon;

public class MovieVO {
	private int quantity;
	private int resNum;
	private int movieNum; // 영화번호
	private String movieTitle;
	private String screenDate;
	private String seatName;
	private int scheduleNum;
	private ImageIcon movieImage;
	private String resSeat;
	private String synopsis;

	public MovieVO() {
	}

	public MovieVO(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public MovieVO(int quantity, int resNum, int movieNum, String movieTitle, String screenDate, String seatName) {
		this.quantity = quantity;
		this.resNum = resNum;
		this.movieNum = movieNum;
		this.movieTitle = movieTitle;
		this.screenDate = screenDate;
		this.seatName = seatName;
	}

	public MovieVO(int resNum, int scheduleNum, String resSeat) {
		this.resNum = resNum;
		this.scheduleNum = scheduleNum;
		this.resSeat = resSeat;
	}

	public MovieVO(int resNum, String movieTitle, String screenDate, String resSeat) {
		this.resNum = resNum;
		this.movieTitle = movieTitle;
		this.screenDate = screenDate;
		this.resSeat = resSeat;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public ImageIcon getMovieImage() {
		return movieImage;
	}

	public void setMovieImage(ImageIcon movieImage) {
		this.movieImage = movieImage;
	}

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

}
