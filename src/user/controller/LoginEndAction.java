package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import commom.exception.NotUserException;
import common.base.CommonUtil;
import common.controller.AbstractAction;
import user.domain.UserVO;
import user.persistence.UserDAO;

public class LoginEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// 1. 아이디와 비밀번호 값 받아오기
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		// 2. 유효성 체크
		if(userid==null||pwd==null||userid.trim().isEmpty()) {
			this.setViewPage("signin.do");
			this.setRedirect(true);
			return;
		}
		// 3. UserDAO의 loginCheck(아이디, 비번) 메소드 호출하여 UserVO받기
		UserDAO dao = new UserDAO();
		// 4. 예외 처리하기 try~catch로 하기
		try {
			HttpSession session =req.getSession();
			UserVO user =dao.loginCheck(userid, pwd);
			// 5.UserVO가 null이 아니라면 ==> 회원 인증받은 것이므로 세션에 해당 VO객체를 저장 "loginUser"라는 키값으로
			if(user!=null) {
				session.setAttribute("loginUser", user);
				//로그인 중인 유저가 다른 페이지에서 사용 가능해야하므로 session을 사용한다.
			}
		}catch(NotUserException e){
			String msg=e.getMessage();
			this.setRedirect(false);
			this.setViewPage(CommonUtil.addMsgBack(req, msg));
			return;
		}
		
		this.setRedirect(true);
		this.setViewPage("index.do");
		
	}

}
