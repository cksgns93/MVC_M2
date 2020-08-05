package shop.persistence;

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
}
