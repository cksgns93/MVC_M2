package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.persistence.CartDAOMyBatis;

public class CartDeleteAction extends AbstractAction {

	CartDAOMyBatis dao = new CartDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//��ٱ��� ��ȣ �ޱ�
		String cartNum = req.getParameter("cartNum");
		if(cartNum==null||cartNum.trim().isEmpty()) {
			this.setViewPage(CommonUtil.addMsgBack(req, "�߸� ���� ����Դϴ�"));
			this.setRedirect(false);
			return;
		}
		
		int n = dao.deleteCart(cartNum);
		
		this.setViewPage("cart.do");
		this.setRedirect(true);
		
	}

}
