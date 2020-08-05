package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.domain.CartVO;
import shop.persistence.CartDAOMyBatis;
import user.domain.UserVO;

public class CartListAction extends AbstractAction {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		this.setViewPage("../shop/cartAdd.jsp");
		this.setRedirect(false);
	}

}
