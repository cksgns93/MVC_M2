package board.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import common.base.DAOBase;
import jdbc.util.DBUtil;

public class BoardDAO extends DAOBase{
	public int insertBoard(BoardVO vo) throws SQLException{
		try {
			con=DBUtil.getCon();
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
			con=DBUtil.getCon();
			String sql = "select * from board";
			ps=con.prepareStatement(sql);
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
			con=DBUtil.getCon();
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
			con=DBUtil.getCon();
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
	public boolean updateReadnum(String idx) throws SQLException{
		try {
			con=DBUtil.getCon();
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
			con=DBUtil.getCon();
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
			con=DBUtil.getCon();
			String sql="delete from board where idx=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, idx);
			int n = ps.executeUpdate();
		}finally {
			close();
		}
	}
}
