package shop.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.base.DAOMyBatisBase;
import shop.domain.CartVO;

public class CartDAOMyBatis extends DAOMyBatisBase {

	private final String NS = "common.mapper.CartMapper";
	private SqlSession ses;
	public int addCart(CartVO vo) {
		try {
			ses=this.getSqlSessionFactory().openSession(true); //true¡÷∏È auto commit;
			int n = ses.insert(NS+".addCart",vo);
			return n;
		}finally {
			close(ses);
		}
	}
	public int selectCartByPnum(CartVO cvo) {
		try {
			ses=this.getSqlSessionFactory().openSession();
			int cnt=ses.selectOne(NS+".selectCartByPnum",cvo);
			return cnt;
		}finally {
			close(ses);
		}
	}
	public int updateCartOqty(CartVO cvo) {
		try {
			ses=this.getSqlSessionFactory().openSession(true);
			int n = ses.update(NS+".updateCartOqty",cvo);
			return n;
		}finally {
			close(ses);
		}
	}
	public List<CartVO> selectCartByIdx(int idx){
		try {
			ses=this.getSqlSessionFactory().openSession();
			List<CartVO> cList = ses.selectList(NS+".selectCartByIdx",idx);
			return cList;
		}finally {
			close(ses);
		}
	}
	
	public CartVO getCartTotal(int idx) {
		try {
			ses=this.getSqlSessionFactory().openSession();
			CartVO vo = ses.selectOne(NS+".getCartTotal",idx);
			return vo;
		}finally {
			close(ses);
		}
	}
	public int deleteCart(String cartNum) {
		try {
			ses=this.getSqlSessionFactory().openSession(true);
			int n = ses.delete(NS+".deleteCart",cartNum);
			return n;
		}finally {
			close(ses);
		}
	}
	
	public int updateOqty(CartVO cart) {
		try {
			ses=this.getSqlSessionFactory().openSession(true);
			int n = ses.update(NS+".updateOqty",cart);
			return n;
		}finally {
			close(ses);
		}
	}
	
}
