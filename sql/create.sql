/* 상영관 */
CREATE TABLE Screen (
	screenNum NUMBER NOT NULL, /* 상영관번호 */
	totalSeat NUMBER NOT NULL /* 총좌석수 */
);

/* 영화 */
CREATE TABLE Movie (
	movieNum NUMBER NOT NULL, /* 영화번호 */
	movieTitle VARCHAR2(30) NOT NULL, /* 영화제목 */
	totalViewer NUMBER NOT NULL, /* 관객수 */
	director VARCHAR2(20) NOT NULL, /* 감독 */
	mainActor VARCHAR2(20) NOT NULL, /* 주연 */
	openingDate DATE NOT NULL /* 개봉일 */
	synopsis clob /* 줄거리 */
	movieImage blob /* 이미지 */
);

/* 회원 */
CREATE TABLE Membership (
	memberNum NUMBER NOT NULL, /* 회원번호 */
	id VARCHAR2(20) NOT NULL, /* 아이디 */
	password VARCHAR2(20) NOT NULL, /* 비밀번호 */
	memberName VARCHAR2(20) NOT NULL, /* 이름 */
	memberPhone VARCHAR2(20), /* 전화번호 */
	totalCnt NUMBER /* 총관람횟수 */
);

/* 상영일정 */
CREATE TABLE Schedule (
	scheduleNum NUMBER NOT NULL, /* 상영일정번호 */
	movieNum NUMBER, /* 영화번호 */
	screenNum NUMBER, /* 상영관번호 */
	screenDate DATE /* 상영일자 및 시간 */
);


/* 예매 */
CREATE TABLE Reservation (
	resNum NUMBER NOT NULL, /* 예매번호 */
	seatNum NUMBER NOT NULL, /* 좌석번호 */
	scheduleNum NUMBER NOT NULL, /* 상영일정번호 */
	memberNum NUMBER NOT NULL, /* 회원번호 */
	quantity NUMBER NOT NULL /* 수량 */
);

create sequence memberSeq
	start with 10001
	increment by 1
	minvalue 9999
	nocycle
	;
	
create sequence movieSeq
	start with 1001
	increment by 1
	minvalue 999
	nocycle
	;
	
create sequence scheduleSeq
	start with 1
	increment by 1
	minvalue -1
	nocycle
	;