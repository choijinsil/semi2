-- Screen
insert into screen values(1,5);
insert into screen values(2,5);
insert into screen values(3,5);

-- movie
insert into movie values(movieSeq.nextval,'알라딘',10,'가이리치','자스민','19/07/01',null,null);
insert into movie values(movieSeq.nextval,'기생충',10,'봉준호','송강호','19/07/01',null,null);
insert into movie values(movieSeq.nextval,'토이스토리',10,'조시쿨리','우디','19/07/01',null,null);

-- membership
insert into MEMBERSHIP values(memberSeq.nextval, 'hana', 'a1234', '유하나', '010-2322-6754',2);
insert into MEMBERSHIP values(memberSeq.nextval, 'jongsoo', 'b1234', '박종수', '010-2322-1234',2);
insert into MEMBERSHIP values(memberSeq.nextval, 'yongjin', 'c1234', '김용진', '010-2322-5678',2);
insert into MEMBERSHIP values(memberSeq.nextval, 'jinsil', 'd1234', '최진실', '010-2322-1345',2);
insert into MEMBERSHIP values(memberSeq.nextval, 'munha', 'e1234', '박문하', '010-2322-6512',2);

-- schedule
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/05 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/05 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/05 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/05 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/05 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/05 15','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/05 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/05 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/05 15','yy/mm/dd hh24'));

insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/06 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/06 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/06 15','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/06 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/06 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/06 15','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/06 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/06 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/06 15','yy/mm/dd hh24'));

insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/07 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/07 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1001, 1, to_date('19/07/07 15','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/07 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/07 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1002, 2, to_date('19/07/07 15','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/07 10','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/07 13','yy/mm/dd hh24'));
insert into schedule values (scheduleSeq.nextval, 1003, 3, to_date('19/07/07 15','yy/mm/dd hh24'));