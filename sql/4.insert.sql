-- Screen
insert into screen values(1,5);
insert into screen values(2,5);
insert into screen values(3,5);

-- movie 1001
insert into movie values(movieSeq.nextval,'알라딘',4,'가이리치','자스민','2019/07/01',null,null);
insert into movie values(movieSeq.nextval,'기생충',3,'봉준호','송강호','2019/07/01',null,null);
insert into movie values(movieSeq.nextval,'토이스토리',3,'조시쿨리','우디','2019/07/01',null,null);

-- membership 10001
insert into MEMBERSHIP values(memberSeq.nextval, 'hana', 'a1234', '유하나', '010-2322-6754',3);
insert into MEMBERSHIP values(memberSeq.nextval, 'jongsoo', 'b1234', '박종수', '010-2322-1234',2);
insert into MEMBERSHIP values(memberSeq.nextval, 'yongjin', 'c1234', '김용진', '010-2322-5678',0);
insert into MEMBERSHIP values(memberSeq.nextval, 'jinsil', 'd1234', '최진실', '010-2322-1345',0);
insert into MEMBERSHIP values(memberSeq.nextval, 'moonha', 'e1234', '박문하', '010-2322-6512',0);
insert into MEMBERSHIP values(1,'관리자','rhksfl1!','관리자','010-0000-0000',0);

-- schedule 1
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/05 10','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/05 13','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/05 16','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/05 11','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/05 14','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/05 17','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/05 12','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/05 15','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/05 18','yyyy/mm/dd hh24')); -- 9

insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/06 10','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/06 13','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/06 16','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/06 11','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/06 14','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/06 17','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/06 12','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/06 15','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/06 18','yyyy/mm/dd hh24')); -- 18

insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/07 10','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/07 13','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('2019/07/07 16','yyyy/mm/dd hh24'));
--insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/07 11','yyyy/mm/dd hh24'))
--insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/07 14','yyyy/mm/dd hh24'))
--insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('2019/07/07 17','yyyy/mm/dd hh24'))
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/07 12','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/07 15','yyyy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('2019/07/07 18','yyyy/mm/dd hh24')); -- 27

-- reservation
insert into RESERVATION values (100011,1,10001,2,'A2,A3');    -- 1001
insert into RESERVATION values (100012,13,10001,3,'A2,A3,A5');  -- 1002
insert into RESERVATION values (100013,22,10001,1,'A1'); -- 1003
insert into RESERVATION values (100021,2,10002,2,'A2,A3'); -- 1001
insert into RESERVATION values (100022,17,10002,2,'A2,A3'); -- 1003

-- truncate table reservation