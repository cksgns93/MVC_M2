package common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		System.out.println("IndexAction�� execute()...");
		req.setAttribute("msg", "MVCWeb�� Ȩ�������Դϴ�.");
		
		this.setViewPage("/index.jsp");//�������� ����
		this.setRedirect(false);//�̵������ forward�̵����� ����
	}
}
