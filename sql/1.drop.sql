
drop table reservation;
drop table membership;
drop table schedule;
drop table screen;
drop table movie;

drop sequence memberSeq;
drop sequence scheduleseq;
drop sequence movieseq;

select ScheduleNum from schedule  natural join movie where movieTitle = '알라딘' and screenDate = to_date('2019/07/07 13','yyyy/mm/dd hh24');
