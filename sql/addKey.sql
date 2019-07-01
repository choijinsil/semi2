/* 상영관 */
ALTER TABLE Screen
	ADD
		CONSTRAINT PK_Screen
		PRIMARY KEY (
			screenNum
		);

/* 영화 */
ALTER TABLE Movie
	ADD
		CONSTRAINT PK_Movie
		PRIMARY KEY (
			movieNum
		);

/* 회원 */
ALTER TABLE Membership
	ADD
		CONSTRAINT PK_Membership
		PRIMARY KEY (
			memberNum
		);


/* 상영일정 */
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

/* 예매 */
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
		CONSTRAINT FK_Membership_TO_Reservation
		FOREIGN KEY (
			memberNum
		)
		REFERENCES Membership (
			memberNum
		);