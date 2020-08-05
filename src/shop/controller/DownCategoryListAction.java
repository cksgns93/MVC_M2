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

		//���� ī�װ��� �ش��ϴ� ���� ī�װ� ��� ��������
		List<CategoryVO> dList = dao.getDownCategoryList(upCode);
		JSONArray jsonArr = JSONArray.fromObject(dList);
		req.setAttribute("jsonArr",jsonArr.toString());
		
		
		this.setRedirect(false);
		this.setViewPage("../shop/downCategory.jsp");
	}

}
