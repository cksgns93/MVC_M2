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
			//아이디 입력 폼을 보여준다.
			this.setViewPage("member/idCheck.jsp");
			this.setRedirect(false);			
		}else if(method.equalsIgnoreCase("POST")) {
			//1. 사용자가 입력한 아이디값 받기
			String userid = req.getParameter("userid");
			//2. 유효성 체크
			if(userid==null||userid.trim().isEmpty()) {
				this.setViewPage(CommonUtil.addMsgBack(req, "아이디를 입력하세요"));
				this.setRedirect(false);
				return;
			}
			//3. UserDAO생성해서 idCheck(userid) ==> 그 결과 req에 저장
			UserDAO dao = new UserDAO();
			boolean isUse = dao.idCheck(userid);
			req.setAttribute("isUse", isUse); //Boolean객체로 저장됨 (autoboxing)
			req.setAttribute("userid", userid);
			this.setViewPage("member/idCheckResult.jsp");
			this.setRedirect(false);
		}
	}

}
