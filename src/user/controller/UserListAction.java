package user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
import user.domain.UserVO;
import user.persistence.UserDAO;

public class UserListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		UserDAO dao = new UserDAO();
		List<UserVO> userList = dao.listMember();
		req.setAttribute("userList", userList);
		this.setViewPage("../member/members.jsp");
		this.setRedirect(false);
	}

}
