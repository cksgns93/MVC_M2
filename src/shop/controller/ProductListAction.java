package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.domain.ProductVO;
import shop.persistence.ProductDAOMyBatis;

public class ProductListAction extends AbstractAction {
	ProductDAOMyBatis dao = new ProductDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//총 상품 갯수 가져오기
		int totalCount = dao.getProductCount();
		
		//총 상품 목록 가져오기
		List <ProductVO> arr =dao.getProductList();
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("prodList", arr);
		this.setViewPage("../shop/productList.jsp");
		this.setRedirect(false);
	}

}
