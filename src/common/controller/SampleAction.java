package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("msg", "Sample 페이지 입니다");
		this.setViewPage("/sample.jsp");
		this.setRedirect(false);
	}

}
