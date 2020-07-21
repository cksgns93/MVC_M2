package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("IndexAction의 execute()...");
		req.setAttribute("msg", "MVCWeb의 홈페이지입니다.");
		
		this.setViewPage("/index.jsp");//뷰페이지 지정
		this.setRedirect(false);//이동방식은 forward이동으로 설정
	}
}
