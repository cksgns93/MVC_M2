package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.domain.ProductVO;
import shop.persistence.ProductDAOMyBatis;

public class DisplayAction extends AbstractAction {

	ProductDAOMyBatis pdao = new ProductDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<ProductVO> pList=pdao.selectByPspec("HIT");
		List<ProductVO> nList=pdao.selectByPspec("NEW");
		
		req.setAttribute("pspec1", "HIT");
		req.setAttribute("hitList", pList);
		
		req.setAttribute("pspec2", "NEW");
		req.setAttribute("newList", nList);
		
		this.setViewPage("shop/mall.jsp");
		this.setRedirect(false);
	}

}
