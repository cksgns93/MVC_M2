package board.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import common.base.DAOBase;
import jdbc.util.DBUtil;

public class BoardDAO extends DAOBase{
	private DataSource ds;
	
	public BoardDAO() {
		//server.xml에 등록외어 있는 커넥션 풀을 룩업하자
		try {
			Context ctx = new InitialContext();
			//1.WAS서버를 찾자 (톰캣)
			Context ectx =(Context)ctx.lookup("java:comp/env");
			//톰캣찾는 프로토콜
			//2.우리가 등록한 DataSource를 찾자. - resource name으로 룩업한다. => JNDI (Java Naming Directory Interface)
			ds=(DataSource)ectx.lookup("jdbc/myshop");
			System.out.println("DataSource Lookup Seccess!!");
		} catch (NamingException e) {
			System.out.println("데이터 소스 룩업 실패");
			e.printStackTrace();
		}
	}
	public int insertBoard(BoardVO vo) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "insert into board(idx,name,pwd,subject,content,filename,originfilename,filesize,wdate)"
					+ " values(board_seq.nextval,?,?,?,?,?,?,?,systimestamp)";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			ps.setString(5, vo.getFilename());
			ps.setString(6, vo.getOriginFilename());
			ps.setLong(7, vo.getFilesize());
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	
	public List<BoardVO> listBoard() throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "select * from board";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			List<BoardVO> arr = makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	public List<BoardVO> listBoard(int start, int end) throws SQLException{
		try {
			con=DBUtil.getCon();
			StringBuilder buf= new StringBuilder("select * from (") 
					.append("select row_number() over(order by idx desc) rn, ") 
					.append("board.* from board ") 
					.append(") where rn between ? and ?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs=ps.executeQuery();
			List<BoardVO> arr = makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	public List<BoardVO> makeList(ResultSet rs) throws SQLException{
		List<BoardVO> arr=new ArrayList<BoardVO>();
		while(rs.next()) {
			int idx = rs.getInt("idx");
			String name = rs.getString("name");
			String pwd = rs.getString("pwd");
			String subject = rs.getString("subject");
			String content = rs.getString("content");
			String filename = rs.getString("filename");
			String originFilename = rs.getString("originFilename");
			Timestamp wdate = rs.getTimestamp("wdate");
			int readnum = rs.getInt("readnum");
			long filesize = rs.getLong("filesize");
			int refer= rs.getInt("refer");
			int lev = rs.getInt("lev");
			int sunbun= rs.getInt("sunbun");
			BoardVO board = new BoardVO(idx,name,subject,content,pwd,wdate,readnum,filename,
					originFilename,filesize,refer,lev,sunbun);
			arr.add(board);
		}
		return arr;
	}
	public BoardVO viewBoard(String idx) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "select * from board where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			rs=ps.executeQuery();
			List<BoardVO> arr = makeList(rs);
			if(arr!=null&&arr.size()!=0) {
				return arr.get(0);				
			}
			return null;
		}finally {
			close();
		}
	}

	public int getTotalCount() throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select count(idx) cnt from board";
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			int n = rs.getInt("cnt");
			return n;
		}finally {
			close();
		}
	}
	public int getTotalCount(String findType, String findKeyword) throws SQLException{
		String colName = getColumnName(findType);
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="select count(idx) cnt from board where "+colName+" like ?";
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+findKeyword+"%");
			rs=ps.executeQuery();
			rs.next();
			int n = rs.getInt("cnt");
			return n;
		}finally {
			close();
		}
	}
	public List<BoardVO> findBoard(int start, int end, String findType, String findKeyword) throws SQLException{
		String colName=this.getColumnName(findType);
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			StringBuilder buf= new StringBuilder("select * from (") 
					.append("select row_number() over(order by idx desc) rn, ") 
					.append("board.* from board ") 
					.append("where "+colName+" like ? ") 
					.append(") where rn between ? and ?");
			String sql=buf.toString();
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+findKeyword+"%");
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs=ps.executeQuery();
			List<BoardVO> arr = makeList(rs);
			return arr;
		}finally {
			close();
		}
	}
	private String getColumnName(String findType) {
		String str="";
		switch(findType){
		case "1":str="subject";break;
		case "2":str="name";break;
		case "3":str="content";break;
		}
		return str;
	}

	public boolean updateReadnum(String idx) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="update board set readnum = readnum+1 where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			int n = ps.executeUpdate();
			return (n>0) ? true:false;
		}finally {
			close();
		}
	}
	
	public int updateBoard(BoardVO vo) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql = "update board set name=?,pwd=?,subject=?,content=?"
					+ ", wdate=systimestamp";
			if(vo.getFilename()!=null) {
				sql+= ",filename=? , originfilename=?,filesize=? ";	
			}
			sql+=" where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getSubject());
			ps.setString(4, vo.getContent());
			if(vo.getFilename()!=null) {//첨부파일이 있는 경우
				ps.setString(5, vo.getFilename());
				ps.setString(6, vo.getOriginFilename());
				ps.setLong(7, vo.getFilesize());
				ps.setInt(8, vo.getIdx());				
			}else {//첨부파일이 없는 경우
				ps.setInt(5, vo.getIdx());
			}
			int n = ps.executeUpdate();
			return n;
		}finally {
			close();
		}
	}
	public void deleteBoard(String idx) throws SQLException{
		try {
			//con=DBUtil.getCon();
			con=ds.getConnection();
			String sql="delete from board where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			int n = ps.executeUpdate();
		}finally {
			close();
		}
	}
}
