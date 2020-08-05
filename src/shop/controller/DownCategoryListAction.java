package shop.controller;

import java.util.List;
import net.sf.json.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import shop.domain.CategoryVO;
import shop.persistence.ProductDAOMyBatis;

public class DownCategoryListAction extends AbstractAction {

	ProductDAOMyBatis dao = new ProductDAOMyBatis();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String upCode = req.getParameter("upCode");

		//상위 카테고리에 해당하는 하위 카테고리 목록 가져오기
		List<CategoryVO> dList = dao.getDownCategoryList(upCode);
		JSONArray jsonArr = JSONArray.fromObject(dList);
		req.setAttribute("jsonArr",jsonArr.toString());
		
		
		this.setRedirect(false);
		this.setViewPage("../shop/downCategory.jsp");
	}

}
