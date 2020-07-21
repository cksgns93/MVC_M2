drop table board;
-- 단순형 게시판(파일첨부기능)
create table board{
	idx number(8) constraint board_pk primary key, --글번호
	name varchar2(30) not null, --작성자
	pwd varchar2(30) not null, --비번
	subject varchar2(200), --제목
	content varchar2(2000), --내용
	wdate timestamp default systimestamp, --작성일
	readnum number(8) default 0, --조회수
	filename varchar2(300), --첨부파일명
	originFilenmae varchar2(300), --원본파일명
	filesize number(8), --첨부파일 크기
	refer number(8), --글 그룹 번호[답변형 게시판일 때 사용]
	lev number(8), --답변 레벨[답변형 게시판일 때 사용]
	sunbun number(8) --같은 글 그룹 내의 순서정렬[답변형 게시판일 때 사용]
};

drop sequence board_seq;

create sequence board_seq
start with 1 increment by 1
nocache;