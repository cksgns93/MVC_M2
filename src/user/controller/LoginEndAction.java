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
		// 1. ���̵�� ��й�ȣ �� �޾ƿ���
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		// 2. ��ȿ�� üũ
		if(userid==null||pwd==null||userid.trim().isEmpty()) {
			this.setViewPage("signin.do");
			this.setRedirect(true);
			return;
		}
		// 3. UserDAO�� loginCheck(���̵�, ���) �޼ҵ� ȣ���Ͽ� UserVO�ޱ�
		UserDAO dao = new UserDAO();
		// 4. ���� ó���ϱ� try~catch�� �ϱ�
		try {
			HttpSession session =req.getSession();
			UserVO user =dao.loginCheck(userid, pwd);
			// 5.UserVO�� null�� �ƴ϶�� ==> ȸ�� �������� ���̹Ƿ� ���ǿ� �ش� VO��ü�� ���� "loginUser"��� Ű������
			if(user!=null) {
				session.setAttribute("loginUser", user);
				//�α��� ���� ������ �ٸ� ���������� ��� �����ؾ��ϹǷ� session�� ����Ѵ�.
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
