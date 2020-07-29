package user.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import commom.exception.NotUserException;
import common.base.DAOBase;
//import jdbc.util.DBUtil;
import user.domain.UserVO;
public class UserDAO extends DAOBase{
	private DataSource ds;
	public UserDAO() {
		try {
			Context ctx = new InitialContext();
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/myshop");
			System.out.println("DataSource Lookup Seccess2!!");
		} catch (NamingException e) {
			System.out.println("데이터 소스 룩업 실패");
			e.printStackTrace();
		}
		System.out.println("UserDAO 생성");
	}
	public int createUser(UserVO user) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			//String => 불변성(immutable) 문자열을 조작하면 원본을 그대로두고 새로운 객체를 만든다.
			//문자열 조작이나 편집을 하려면 StringBuffer나 StringBuilder를 이용
			//이 클래스들은 무자열을 버퍼에 보관하여 버퍼내에서 삽입, 수정, 삭제 등의 편집을 수행한다. 
			String sql="insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,sysdate,1000,0)";
//			StringBuffer buf
//			= new StringBuffer("insert into member values(member_seq.nextval")
//			.append(",?,?,?,?,?,?,?,?,?,sysdate,").append("DEFAULT,DEFAULT)");
//			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getHp1());
			ps.setString(5, user.getHp2());
			ps.setString(6, user.getHp3());
			ps.setString(7, user.getPost());
			ps.setString(8, user.getAddr1());
			ps.setString(9, user.getAddr2());
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	/*모든 회원 목록 가져오기 (페이징 처리하기 전)*/
	public List<UserVO> listMember() throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=con=ds.getConnection();
			String sql="select * from member order by idx desc";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			List<UserVO> arr= makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	//---
	/*총 회원 수 가져오기*/
	public int getTotalUserCount() throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select count(idx) cnt from member"; 
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			int count=0;
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			return count;
		}finally {
			close();
		}
	}
	/*검색한 회원수 가져오기*/
	public int getFindTotalUserCount(String type, String keyword) throws SQLException{
		try {
			String colName="";
			switch(type) {
			case "1" : colName="name"; break;
			case "2" : colName="userid"; break;
			case "3" : colName="hp1||hp2||hp3"; break;
			}
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select count(idx) cnt from member where "+colName+" like ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			rs=ps.executeQuery();
			int count =0;
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			return count;
		}finally {
			close();
		}
	}
	/*모든 회원 목록 가져오기 (페이징 처리 후)*/
	public List<UserVO> listMember(int start, int end) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="";
			StringBuilder buf = new StringBuilder("select * from(")
					.append(" select rownum rn, a.* from")
					.append(" (select * from member order by idx desc) a)")
					.append(" where rn between ? and ?");
			sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs=ps.executeQuery();
			List<UserVO> arr= makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	
	/*모든 검색 회원 목록 가져오기 (페이징 처리 후)*/
	public List<UserVO> findMember(String type, String keyword,int start, int end) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String colName="";
			switch(type) {
			case "1" : colName="name"; break;
			case "2" : colName="userid"; break;
			case "3" : colName="hp1||hp2||hp3"; break;
			}
			String sql=""; //wgho순서
			StringBuilder buf = new StringBuilder("select * from(")
					.append(" select rownum rn, a.* from")
					.append(" (select * from member where ")
					.append(colName+" like ?")
					.append(" order by idx desc) a)")
					.append(" where rn between ? and ?");
			sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+keyword+"%");
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs=ps.executeQuery();
			List<UserVO> arr= makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	public List<UserVO> makeList(ResultSet rs) throws SQLException{
		List<UserVO> arr= new ArrayList<>();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String userid = rs.getString("userid");
			String pwd = rs.getString("pwd");
			String hp1 = rs.getString("hp1");
			String hp2 = rs.getString("hp2");
			String hp3 = rs.getString("hp3");
			String post = rs.getString("post");
			String addr1 = rs.getString("addr1");
			String addr2 = rs.getString("addr2");
			java.sql.Date indate=rs.getDate("indate");
			int mileage=rs.getInt("mileage");
			int mstate=rs.getInt("mstate");
			UserVO user = new UserVO(idx,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,indate,mileage,mstate);
			arr.add(user);
		}
		return arr;
	}
		
	/*회원탈퇴 처리를 하는 메소드*/
	public int deleteMember(String idx) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			StringBuilder buf = new StringBuilder("update member set mstate=-1")
					.append(" where idx=?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	public boolean idCheck(String userid) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select * from member where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			//아이디는 unique제약조건을 주었기 때문에 있으면 1개의 결과를 반환, 없으면 반환x
			boolean bool = rs.next(); //==true를 반환하면 해당 아이디가 존재
			return !bool;
		}finally {
			close();
		}
	}	
	/*회원번호(PK)로 회원정보 가져오는 메소드*/
	public UserVO selectUserByIdx(String idx) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select * from member where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			rs=ps.executeQuery();
			List<UserVO> arr = makeList(rs);
			if(arr!=null||arr.size()>=1) {
				UserVO user = arr.get(0);
				return user;
			}
			return null;
		}finally {
			close();
		}
	}
	public UserVO selectUserByUserid(String userid) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select * from memberView where userid=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, userid);
			rs=ps.executeQuery();
			List<UserVO> arr = makeList(rs);
			if(arr!=null||arr.size()>=1) {
				UserVO user = arr.get(0);
				return user;
			}
			return null;
		}finally {
			close();
		}
	}
	
	public int updateUser(UserVO user)throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="update member set name=?,userid=?,pwd=?,hp1=?,hp2=?,hp3=?,post=?,addr1=?,addr2=?,mstate=?,mileage=? where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUserid());
			ps.setString(3, user.getPwd());
			ps.setString(4, user.getHp1());
			ps.setString(5, user.getHp2());
			ps.setString(6, user.getHp3());
			ps.setString(7, user.getPost());
			ps.setString(8, user.getAddr1());
			ps.setString(9, user.getAddr2());
			ps.setInt(10, user.getMstate());
			ps.setInt(11, user.getMileage());
			ps.setInt(12, user.getIdx());
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	
	public UserVO loginCheck(String userid, String pwd) throws SQLException, NotUserException{
		UserVO user = this.selectUserByUserid(userid);
		if(user==null) {
			//아이디가 존재하지 않을 경우
			throw new NotUserException(userid+"란 아이디는 존재하지 않는다.");
		}
		//비번 체크
		if(!pwd.equals(user.getPwd())){
			throw new NotUserException("비밀번호가 일치하지 않음");
		}
		return user;
	}
	
	
}
