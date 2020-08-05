package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.persistence.ProductDAOMyBatis;

public class ProductRemoveAction extends AbstractAction {

	ProductDAOMyBatis dao = new ProductDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. ������ ��ǰ��ȣ �ޱ�
		String pnum= req.getParameter("pnum");
		//2. ��ȿ�� üũ
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("../index.do");
			this.setRedirect(true);
			return;
		}
		
		int n=dao.productDelete(pnum.trim());
		String msg=(n>0)? "��ǰ ���� ����":"���� ����";
		String loc=(n>0)? "prodList.do":"javascript:history.back()";
		String viewPage=CommonUtil.addMsgLoc(req, msg, loc);
		this.setViewPage(viewPage);
		this.setRedirect(false);
	}
}
