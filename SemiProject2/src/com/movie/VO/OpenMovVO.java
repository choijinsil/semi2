package com.movie.VO;

public class OpenMovVO {
	private int movieNum;
	private String movieTitle;
	private int totalViewer;
	private String director;
	private String mainActor;
	private String openingDate;
	private String movieSyn;

	private byte[] movieImg;

	public OpenMovVO() {

	}

	public OpenMovVO(int movieNum, String movieTitle, int totalViewer, String director, String mainActor,
			String openingDate, byte[] movieImg) {
		this.movieNum = movieNum;
		this.movieTitle = movieTitle;
		this.totalViewer = totalViewer;
		this.director = director;
		this.mainActor = mainActor;
		this.openingDate = openingDate;
		this.movieImg = movieImg;
	}
	public String getMovieSyn() {
		return movieSyn;
	}
	
	public void setMovieSyn(String movieSyn) {
		this.movieSyn = movieSyn;
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

	public int getTotalViewer() {
		return totalViewer;
	}

	public void setTotalViewer(int totalViewer) {
		this.totalViewer = totalViewer;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getMainActor() {
		return mainActor;
	}

	public void setMainActor(String mainActor) {
		this.mainActor = mainActor;
	}

	public String getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(String openingDate) {
		this.openingDate = openingDate;
	}
	
	public byte[] getMovieImg() {
		return movieImg;
	}
	
	public void setMovieImg(byte[] movieImg) {
		this.movieImg = movieImg;
	}

}
