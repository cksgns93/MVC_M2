CREATE USER MYSHOP
IDENTIFIED BY tiger;

grant connect,resource to myshop;

alter user myshop quota unlimited on system;



drop table member;

create table member(
  idx number(8) constraint member_idx_pk primary key,
  name varchar2(30) not null,
  userid varchar2(20) constraint member_userid_uk unique,
  pwd varchar2(20) not null,
  hp1 char(3) not null,
  hp2 char(4) not null,
  hp3 char(4) not null,
  post char(5), --우편번호 테이블을 참조하는 외래키
  addr1 varchar2(200),
  addr2 varchar2(200),
  indate date default sysdate,
  mileage number(10) default 1000,
  mstate number(2) default 0 --활동상태 활동 0 정지 1 탈퇴 -1
  constraint member_mstate_ck check (mstate in (0,1,-1))
);

desc member;

create sequence member_seq
start with 1 increment by 1 nocache;