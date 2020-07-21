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
			String subject = rs.getString("subject");
			String content = rs.getString("content");
			Timestamp wdate = rs.getTimestamp("wdate");
			int readnum = rs.getInt("readnum");
			BoardVO board = new BoardVO(idx,name,subject,content,null,wdate,readnum,null,null,0);
			arr.add(board);
		}
		return arr;
	}
}
