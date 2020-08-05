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
		//1. 삭제할 상품번호 받기
		String pnum= req.getParameter("pnum");
		//2. 유효성 체크
		if(pnum==null||pnum.trim().isEmpty()) {
			this.setViewPage("../index.do");
			this.setRedirect(true);
			return;
		}
		
		int n=dao.productDelete(pnum.trim());
		String msg=(n>0)? "상품 삭제 성공":"삭제 실패";
		String loc=(n>0)? "prodList.do":"javascript:history.back()";
		String viewPage=CommonUtil.addMsgLoc(req, msg, loc);
		this.setViewPage(viewPage);
		this.setRedirect(false);
	}
}
