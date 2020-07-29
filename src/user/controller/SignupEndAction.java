package user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import user.domain.UserVO;
import user.persistence.UserDAO;

public class SignupEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1.����ڰ� �Է��� �� �ޱ� 
		String name = req.getParameter("name");
		String userid = req.getParameter("userid");
		String pwd = req.getParameter("pwd");
		String pwd2 = req.getParameter("pwd2");
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		String post = req.getParameter("post");
		String addr1= req.getParameter("addr1");
		String addr2= req.getParameter("addr2");
		int mileage =1000;
		int mstate=0;
		//2.��ȿ�� üũ => signup.do�� �����̷�Ʈ �̵�
		if(name==null||name.trim().isEmpty()||userid==null||userid.trim().isEmpty()||pwd==null||pwd.trim().isEmpty()) {
			this.setViewPage("signup.do");
			this.setRedirect(true);
			return;		
		}
		//3.UserVO��ü �����ؼ� 1������ ���� �� ����ֱ�
		UserVO user = new UserVO(0,name,userid,pwd,hp1,hp2,hp3,post,addr1,addr2,null,mileage,mstate);
		//4.UserDAO �����ؼ� createUser()ȣ���ϱ�
		UserDAO dao = new UserDAO();
		int n = dao.createUser(user);
		//5.�� ��� �޽��� ó�� 
		String msg = (n>0) ? "����":"����";
		String loc = (n>0) ? "index.do":"javascript:history.back()";
		String view = CommonUtil.addMsgLoc(req, msg, loc);
		this.setViewPage(view);
		this.setRedirect(false);
	}

}
