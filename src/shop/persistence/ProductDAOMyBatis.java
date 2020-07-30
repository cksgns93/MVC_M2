package shop.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.base.DAOMyBatisBase;
import shop.domain.CategoryVO;
import shop.domain.ProductVO;

public class ProductDAOMyBatis extends DAOMyBatisBase{

	private final String NS="common.mapper.ProductMapper";
	private SqlSession ses;
	
	public List<ProductVO> selectByPspec(String pspec){
		try {
			ses=this.getSqlSessionFactory().openSession();
			List<ProductVO> arr=ses.selectList(NS+".selectByPspec",pspec);
			return arr;
		}finally {
			close(ses);
		}
	}

	public List<CategoryVO> getUpCategoryList() {
		try {
			ses=this.getSqlSessionFactory().openSession();
			List<CategoryVO> cList = ses.selectList(NS+".getUpCategoryList");
			return cList;
		}finally {
			close(ses);
		}
	}


	
}
