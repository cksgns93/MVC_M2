package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SampleAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setAttribute("msg", "Sample ������ �Դϴ�");
		this.setViewPage("/sample.jsp");
		this.setRedirect(false);
	}

}
