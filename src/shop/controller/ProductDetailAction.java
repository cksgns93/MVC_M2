package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.domain.ProductVO;
import shop.persistence.ProductDAOMyBatis;

public class ProductDetailAction extends AbstractAction {
	
	ProductDAOMyBatis dao = new ProductDAOMyBatis();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1.상품 번호 받아보기
		String pnum=req.getParameter("pnum");
		//2.유효성 체크
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		//3.dao의 productInfo(상품번호) 호출하기
		ProductVO vo = dao.productInfo(pnum);
		//4.req에 저장하기
		req.setAttribute("prod", vo);
		this.setViewPage("shop/productView.jsp");
		this.setRedirect(false);
	}

}
