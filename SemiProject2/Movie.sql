ALTER TABLE Payment
	DROP
		CONSTRAINT FK_Reservation_TO_Payment
		CASCADE;

ALTER TABLE Payment
	DROP
		CONSTRAINT FK_Membership_TO_Payment
		CASCADE;

ALTER TABLE Payment
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Payment;

/* Payment2 */
CREATE TABLE Payment (
	payNum NUMBER NOT NULL, /* payNum */
	memberNum NUMBER NOT NULL, /* 회원번호 */
	resNum NUMBER NOT NULL, /* 예매번호 */
	payDate DATE NOT NULL /* 결제일자 */
);

COMMENT ON TABLE Payment IS 'Payment2';

COMMENT ON COLUMN Payment.payNum IS 'payNum';

COMMENT ON COLUMN Payment.memberNum IS '회원번호';

COMMENT ON COLUMN Payment.resNum IS '예매번호';

COMMENT ON COLUMN Payment.payDate IS '결제일자';

CREATE UNIQUE INDEX PK_Payment
	ON Payment (
		payNum ASC
	);

ALTER TABLE Payment
	ADD
		CONSTRAINT PK_Payment
		PRIMARY KEY (
			payNum
		);

ALTER TABLE Payment
	ADD
		CONSTRAINT FK_Reservation_TO_Payment
		FOREIGN KEY (
			resNum
		)
		REFERENCES Reservation (
			resNum
		);

ALTER TABLE Payment
	ADD
		CONSTRAINT FK_Membership_TO_Payment
		FOREIGN KEY (
			memberNum
		)
		REFERENCES Membership (
			memberNum
		);
		

ALTER TABLE Reservation
	DROP
		CONSTRAINT FK_Schedule_TO_Reservation
		CASCADE;

ALTER TABLE Reservation
	DROP
		CONSTRAINT FK_Seat_TO_Reservation
		CASCADE;

ALTER TABLE Reservation
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Reservation;

/* 예매 */
CREATE TABLE Reservation (
	resNum NUMBER NOT NULL, /* 예매번호 */
	seatNum NUMBER NOT NULL, /* 좌석번호 */
	scheduleNum NUMBER NOT NULL, /* 상영일정번호 */
	quantity NUMBER /* 수량 */
);

COMMENT ON TABLE Reservation IS '예매';

COMMENT ON COLUMN Reservation.resNum IS '예매번호';

COMMENT ON COLUMN Reservation.seatNum IS '좌석번호';

COMMENT ON COLUMN Reservation.scheduleNum IS '상영일정번호';

COMMENT ON COLUMN Reservation.quantity IS '수량';

CREATE UNIQUE INDEX PK_Reservation
	ON Reservation (
		resNum ASC
	);

ALTER TABLE Reservation
	ADD
		CONSTRAINT PK_Reservation
		PRIMARY KEY (
			resNum
		);

ALTER TABLE Reservation
	ADD
		CONSTRAINT FK_Schedule_TO_Reservation
		FOREIGN KEY (
			scheduleNum
		)
		REFERENCES Schedule (
			scheduleNum
		);

ALTER TABLE Reservation
	ADD
		CONSTRAINT FK_Seat_TO_Reservation
		FOREIGN KEY (
			seatNum
		)
		REFERENCES Seat (
			seatNum
		);
		
ALTER TABLE Seat
	DROP
		CONSTRAINT FK_Screen_TO_Seat
		CASCADE;

ALTER TABLE Seat
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Seat;

/* 좌석 */
CREATE TABLE Seat (
	seatNum NUMBER NOT NULL, /* 좌석번호 */
	screenNum NUMBER NOT NULL, /* 상영관번호 */
	seatState NUMBER NOT NULL /* 좌석배정유무 */
);

COMMENT ON TABLE Seat IS '좌석';

COMMENT ON COLUMN Seat.seatNum IS '좌석번호';

COMMENT ON COLUMN Seat.screenNum IS '상영관번호';

COMMENT ON COLUMN Seat.seatState IS '좌석배정유무';

CREATE UNIQUE INDEX PK_Seat
	ON Seat (
		seatNum ASC
	);

ALTER TABLE Seat
	ADD
		CONSTRAINT PK_Seat
		PRIMARY KEY (
			seatNum
		);

ALTER TABLE Seat
	ADD
		CONSTRAINT FK_Screen_TO_Seat
		FOREIGN KEY (
			screenNum
		)
		REFERENCES Screen (
			screenNum
		);
		
ALTER TABLE Membership
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Membership;

/* 회원 */
CREATE TABLE Membership (
	memberNum NUMBER NOT NULL, /* 회원번호 */
	id VARCHAR2(20) NOT NULL, /* 아이디 */
	password VARCHAR2(20) NOT NULL, /* 비밀번호 */
	memberName VARCHAR2(20) NOT NULL, /* 이름 */
	memberPhone VARCHAR2(20) /* 전화번호 */
);

COMMENT ON TABLE Membership IS '회원';

COMMENT ON COLUMN Membership.memberNum IS '회원번호';

COMMENT ON COLUMN Membership.id IS '아이디';

COMMENT ON COLUMN Membership.password IS '비밀번호';

COMMENT ON COLUMN Membership.memberName IS '이름';

COMMENT ON COLUMN Membership.memberPhone IS '전화번호';

CREATE UNIQUE INDEX PK_Membership
	ON Membership (
		memberNum ASC
	);

ALTER TABLE Membership
	ADD
		CONSTRAINT PK_Membership
		PRIMARY KEY (
			memberNum
		);
		
		
ALTER TABLE Movie
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Movie;

/* 영화 */
CREATE TABLE Movie (
	movieNum NUMBER NOT NULL, /* 영화번호 */
	movieTitle VARCHAR2(30) NOT NULL, /* 영화제목 */
	totalViewer NUMBER NOT NULL, /* 관객수 */
	director VARCHAR2(20) NOT NULL, /* 감독 */
	mainActor VARCHAR2(20) NOT NULL, /* 주연 */
	openingDate DATE NOT NULL /* 개봉일 */
);

COMMENT ON TABLE Movie IS '영화';

COMMENT ON COLUMN Movie.movieNum IS '영화번호';

COMMENT ON COLUMN Movie.movieTitle IS '영화제목';

COMMENT ON COLUMN Movie.totalViewer IS '관객수';

COMMENT ON COLUMN Movie.director IS '감독';

COMMENT ON COLUMN Movie.mainActor IS '주연';

COMMENT ON COLUMN Movie.openingDate IS '개봉일';

CREATE UNIQUE INDEX PK_Movie
	ON Movie (
		movieNum ASC
	);

ALTER TABLE Movie
	ADD
		CONSTRAINT PK_Movie
		PRIMARY KEY (
			movieNum
		);
		
ALTER TABLE Schedule
	DROP
		CONSTRAINT FK_Screen_TO_Schedule
		CASCADE;

ALTER TABLE Schedule
	DROP
		CONSTRAINT FK_Movie_TO_Schedule
		CASCADE;

ALTER TABLE Schedule
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Schedule;

/* 상영일정 */
CREATE TABLE Schedule (
	scheduleNum NUMBER NOT NULL, /* 상영일정번호 */
	movieNum NUMBER, /* 영화번호 */
	screenNum NUMBER, /* 상영관번호 */
	screenDate DATE /* 상영일자 */
);

COMMENT ON TABLE Schedule IS '상영일정';

COMMENT ON COLUMN Schedule.scheduleNum IS '상영일정번호';

COMMENT ON COLUMN Schedule.movieNum IS '영화번호';

COMMENT ON COLUMN Schedule.screenNum IS '상영관번호';

COMMENT ON COLUMN Schedule.screenDate IS '상영일자';

CREATE UNIQUE INDEX PK_Schedule
	ON Schedule (
		scheduleNum ASC
	);

ALTER TABLE Schedule
	ADD
		CONSTRAINT PK_Schedule
		PRIMARY KEY (
			scheduleNum
		);

ALTER TABLE Schedule
	ADD
		CONSTRAINT FK_Screen_TO_Schedule
		FOREIGN KEY (
			screenNum
		)
		REFERENCES Screen (
			screenNum
		);

ALTER TABLE Schedule
	ADD
		CONSTRAINT FK_Movie_TO_Schedule
		FOREIGN KEY (
			movieNum
		)
		REFERENCES Movie (
			movieNum
		);
		
ALTER TABLE Screen
	DROP
		PRIMARY KEY
		CASCADE
		KEEP INDEX;

DROP INDEX PK_Screen;

/* 상영관 */
CREATE TABLE Screen (
	screenNum NUMBER NOT NULL, /* 상영관번호 */
	totalSeat NUMBER NOT NULL /* 총좌석수 */
);

COMMENT ON TABLE Screen IS '상영관';

COMMENT ON COLUMN Screen.screenNum IS '상영관번호';

COMMENT ON COLUMN Screen.totalSeat IS '총좌석수';

CREATE UNIQUE INDEX PK_Screen
	ON Screen (
		screenNum ASC
	);

ALTER TABLE Screen
	ADD
		CONSTRAINT PK_Screen
		PRIMARY KEY (
			screenNum
		);
		
		
select * from tab;

drop table MEMBERSHIP;

select * from user_constraints;