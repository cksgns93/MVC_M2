drop table board;
-- �ܼ��� �Խ���(����÷�α��)
create table board{
	idx number(8) constraint board_pk primary key, --�۹�ȣ
	name varchar2(30) not null, --�ۼ���
	pwd varchar2(30) not null, --���
	subject varchar2(200), --����
	content varchar2(2000), --����
	wdate timestamp default systimestamp, --�ۼ���
	readnum number(8) default 0, --��ȸ��
	filename varchar2(300), --÷�����ϸ�
	originFilenmae varchar2(300), --�������ϸ�
	filesize number(8), --÷������ ũ��
	refer number(8), --�� �׷� ��ȣ[�亯�� �Խ����� �� ���]
	lev number(8), --�亯 ����[�亯�� �Խ����� �� ���]
	sunbun number(8) --���� �� �׷� ���� ��������[�亯�� �Խ����� �� ���]
};

drop sequence board_seq;

create sequence board_seq
start with 1 increment by 1
nocache;