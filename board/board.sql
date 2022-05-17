create table board(
	bno number(8), --번호
	title nvarchar2(50) not null, --제목
	content nvarchar2(1000) not null, --내용
	password varchar2(20) not null, --비밀번호
	attach nvarchar2(100), --파일첨부
	readcount number(8) default 0, --조회수
	name nvarchar2(10) not null, --작성자
	regdate date default sysdate, --작성날짜
	re_ref number(8) not null, --댓글 작성시 원본글 글번호
	re_lev number(8) not null, --댓글 레벨(댓글, 대댓글)
	re_seq number(8) not null --댓글의 순서
);

--pk 규칙 설정
alter table board add constraint pk_board primary key(bno);

--시퀀스 정의(bno필드의 사용할 예정)
create sequence board_seq;

insert into board(bno,title,content,password,attach,name,re_ref,re_lev,re_seq)
values(board_seq.nextval,'게시판 작성', '게시판을 작성해 봅시다','12345',null,'홍길동',board_seq.currval,0,0);

select * from board;

select count(*) from board;

--더미데이터 삽입
insert into BOARD(bno,name,password,title,content,re_ref,re_lev,re_seq)
(select board_seq.nextval,name,password,title,content,board_seq.currval,re_lev,re_seq
from board);

--댓글

select bno,title,re_ref,re_seq,re_lev from board where bno=768;

--re_ref : 댓글 작성시 원본글 글번호
--re_lev : 댓글의 레벨
--re_seq : 댓글의 순서

--768번 댓글 작성
--re_ref는 원본글의 re_ref값을 넣어줌
--re_seq는 원본글의 re_seq +1 값을 넣어줌
--re_lev는 원본글의 re_lev+1 값을 넣어줌

insert into board(bno,title,content,password,attach,name,re_ref,re_lev,re_seq)
values(board_seq.nextval,'re: 댓글작성', '게시판을 작성해 봅시다','12345',null,'립제이',768,1,1);


select bno,title,re_ref,re_seq,re_lev from board where re_ref=768 order by re_seq;

--768번의 두번째 댓글 : 가장 최신 댓글이 위로 올라와야 함
--댓글을 달기 위해서는 원본글의 re_ref, re_seq, re_lev가 필요함
update BOARD set re_seq = re_seq+1 where re_ref = 768 and re_seq >0;

insert into board(bno,title,content,password,attach,name,re_ref,re_lev,re_seq)
values(board_seq.nextval,'re: 댓글작성2', '게시판을 작성해 봅시다','12345',null,'립제이',784,1,1);


select * from board where title like '%게시판%';


--페이지 나누기
--rownum : 가상칼럼(임시값), 조회된 결과에 번호를 매기는 것
select rownum,bno,title from board order by bno desc;

--select rownum,bno,title from BOARD where rownum > 10; (불가)
select rownum,bno,title from BOARD where rownum <= 10;

select rownum,bno,title from board where rownum <= 10 order by bno desc;

--rownum과 orber by 사용 시 정렬을 한 뒤 번호를 매기지 않음
select rownum,bno,title from board where rownum <= 10 
order by re_ref desc, re_seq asc;

--- 인라인 쿼리 작성
select rownum,bno,title from 
(select bno,title from BOARD where bno>0 order by re_ref desc, re_seq asc)
where rownum <=10;

--1 click => 최신글 10개 가지고 오기
--최신글로 우선 목록을 뽑은 후, 그 중 일정 데이터를 뽑도록 한 뒤, 거기서 일정 데이터를 갖고 오도록 함
select rnum,bno,title
from
	(select rownum rnum,A.* 
	from 
		(select bno,title from BOARD where bno>0 order by re_ref desc, re_seq asc) A
	where rownum <=10)
where rnum>0;


--2 clikc => 그 다음 최신글 10개 가져오기
select rnum,bno,title
from
	(select rownum rnum,A.* 
	from 
		(select bno,title from BOARD where bno>0 order by re_ref desc, re_seq asc) A
	where rownum <=20)
where rnum>10;

--3 clikc => 그 다음 최신글 10개 가져오기
select rnum,bno,title
from
	(select rownum rnum,A.* 
	from 
		(select bno,title from BOARD where bno>0 and title like '%테스트%' order by re_ref desc, re_seq asc) A
	where rownum <=30)
where rnum>20;


--1 (10,0) : 1*10, (1-1)*10
--2 (20,10) : 2*10, (2-1)*10

--전체 게시물 수
select count(*) from BOARD;




