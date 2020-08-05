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
		//1.��ǰ ��ȣ �޾ƺ���
		String pnum=req.getParameter("pnum");
		//2.��ȿ�� üũ
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("index.do");
			this.setRedirect(true);
			return;
		}
		//3.dao�� productInfo(��ǰ��ȣ) ȣ���ϱ�
		ProductVO vo = dao.productInfo(pnum);
		//4.req�� �����ϱ�
		req.setAttribute("prod", vo);
		this.setViewPage("shop/productView.jsp");
		this.setRedirect(false);
	}

}
