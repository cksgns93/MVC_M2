package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import user.persistence.UserDAO;

public class IdCheckAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String method = req.getMethod();
		System.out.println("method= "+method);
		if(method.equalsIgnoreCase("GET")) {
			//���̵� �Է� ���� �����ش�.
			this.setViewPage("member/idCheck.jsp");
			this.setRedirect(false);			
		}else if(method.equalsIgnoreCase("POST")) {
			//1. ����ڰ� �Է��� ���̵� �ޱ�
			String userid = req.getParameter("userid");
			//2. ��ȿ�� üũ
			if(userid==null||userid.trim().isEmpty()) {
				this.setViewPage(CommonUtil.addMsgBack(req, "���̵� �Է��ϼ���"));
				this.setRedirect(false);
				return;
			}
			//3. UserDAO�����ؼ� idCheck(userid) ==> �� ��� req�� ����
			UserDAO dao = new UserDAO();
			boolean isUse = dao.idCheck(userid);
			req.setAttribute("isUse", isUse); //Boolean��ü�� ����� (autoboxing)
			req.setAttribute("userid", userid);
			this.setViewPage("member/idCheckResult.jsp");
			this.setRedirect(false);
		}
	}

}
