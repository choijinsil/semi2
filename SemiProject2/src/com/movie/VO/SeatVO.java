package com.movie.VO;

public class SeatVO {
	private String screenNum;
	private String seatName;
	
	public SeatVO() {
	}
	
	public SeatVO(String screenNum, String seatName) {
		this.screenNum = screenNum;
		this.seatName = seatName;
	}
	
	public String getScreenNum() {
		return screenNum;
	}
	
	public void setScreenNum(String screenNum) {
		this.screenNum = screenNum;
	}
	
	public String getSeatName() {
		return seatName;
	}
	
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
}
