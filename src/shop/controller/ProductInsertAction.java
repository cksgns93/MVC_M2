package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;

public class ProductInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String viewPage=CommonUtil.addMsgLoc(req, "msg","loc");
		this.setViewPage(viewPage);
		this.setRedirect(false);
	}

}
