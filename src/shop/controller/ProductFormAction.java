package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.domain.CategoryVO;
import shop.persistence.ProductDAOMyBatis;

public class ProductFormAction extends AbstractAction {

	ProductDAOMyBatis dao=new ProductDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//상위 카테고리 목록 가져오기
		List<CategoryVO> upCgList=dao.getUpCategoryList();
		req.setAttribute("upCgList", upCgList);
		this.setViewPage("../shop/prodInput.jsp");
		this.setRedirect(false);
	}

}
