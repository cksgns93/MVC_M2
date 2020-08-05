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

	public List<CategoryVO> getDownCategoryList(String upCode) {
		try {
			ses=this.getSqlSessionFactory().openSession();
			List<CategoryVO> dList = ses.selectList(NS+".getDownCategoryList",upCode);
			return dList;
		}finally {
			close(ses);
		}
	}

	public int productInsert(ProductVO item) {
		try {
			ses=this.getSqlSessionFactory().openSession();
			int n = ses.insert(NS+".productInsert",item);
			if(n>0) { //트랜잭션 처리
				ses.commit();
			}else {
				ses.rollback();
			}
			return n;
		}finally {
			close(ses);
		}		
	}

	public List<ProductVO> getProductList() {
		try {
			ses=this.getSqlSessionFactory().openSession();
			List<ProductVO> arr = ses.selectList(NS+".getProductList");
			return arr;
		}finally {
			close(ses);
		}
	}

	public int getProductCount() {
		try {
			ses=this.getSqlSessionFactory().openSession();
			int n = ses.selectOne(NS+".getProductCount");
			return n;
		}finally {
			close(ses);
		}
	}
	/*상품 번호로 상품정보 가져오는 메소드*/
	public ProductVO productInfo(String pnum) {
		try {
			ses=this.getSqlSessionFactory().openSession();
			ProductVO vo = ses.selectOne(NS+".productInfo",Integer.parseInt(pnum));
			return vo;
		}finally {
			close(ses);
		}
	}
	
	public int productDelete(String pnum) {
		try {
			ses=this.getSqlSessionFactory().openSession(true);
			//true 넣으면 auto commit;
			int n = ses.delete(NS+".productDelete",pnum);
			return n;
		}finally {
			close(ses);
		}
	}
}
