package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.base.CommonUtil;
import common.controller.AbstractAction;

public class BoardEditFormAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. ������ �� ��ȣ, ��� �ޱ�
		String idx = req.getParameter("idx");
		String pwd = req.getParameter("pwd");
		//2. ��ȿ�� üũ
		if(idx==null||pwd==null||idx.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			return;
		}
		//3. ������ ���� ����� ����ڰ� �Է��� ����� ��ġ�ϸ� �ش� �� ������
		//req�� ����
		BoardDAO dao = new BoardDAO();
		BoardVO board=dao.viewBoard(idx.trim());
		if(board==null) {
			this.setViewPage(CommonUtil.addMsgBack(req, "�ش� ����  �����"));
			this.setRedirect(false);
			return;
		}
		if(!board.getPwd().equals(pwd)) {
			//4. ��ġ���� ������ �޽��� ó��
			this.setViewPage(CommonUtil.addMsgBack(req, "��й�ȣ�� ��ġ���� �ʾƿ�"));
			this.setRedirect(false);
		}else {
			//��� ��ġ�Ѵٸ�
			req.setAttribute("board", board);
			this.setViewPage("boardEdit.jsp");
			this.setRedirect(false);
		}
	}

}
