-- blob table 생성
drop table img_test;
create table img_test(
			no number,
			img_blob blob);



-- clob table 생성
drop table txt_test;
create table txt_test(
			no number,
			txt_clob clob);