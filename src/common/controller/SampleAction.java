package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.SampleDAOMyBatis;

public class SampleAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		SampleDAOMyBatis dao = new SampleDAOMyBatis();
		int count = dao.selectTableCount();
		
		req.setAttribute("msg", "myshop계정의 테이블 수");
		req.setAttribute("count", count);
		
		this.setViewPage("/sample.jsp");
		this.setRedirect(false);
	}

}
