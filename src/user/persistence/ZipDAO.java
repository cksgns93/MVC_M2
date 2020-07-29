package user.persistence;

import java.util.*;
import java.sql.*;
import common.base.DAOBase;
import jdbc.util.DBUtil;
import user.domain.UserVO;
import user.domain.ZipVO;

public class ZipDAO extends DAOBase{
	public ZipDAO() {
		
	}
	public List<ZipVO> selectAll(String doro) throws SQLException{
		try {
			List<ZipVO> arr=new ArrayList<ZipVO>();
			con=DBUtil.getCon();
			String sql="select * from zipcode where doro_kor like ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, "%"+doro+"%");
			rs = ps.executeQuery();
			arr = makeList(rs); 
			return arr;
		}finally {
			close();
		}
	}
	public List<ZipVO> makeList(ResultSet rs) throws SQLException{
		List<ZipVO> arr= new ArrayList<>();
		while(rs.next()) {
			int post_code = rs.getInt("new_post_code");
			String sido_kor = rs.getString("sido_kor");
			String doro_kor = rs.getString("doro_kor");
			String singungu_bld_name = rs.getString("sigungu_bld_name");
			ZipVO zip = new ZipVO(post_code,sido_kor,doro_kor,singungu_bld_name);
			arr.add(zip);
		}
		return arr;
	}
}
