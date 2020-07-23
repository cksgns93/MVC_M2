package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardViewAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. �۹�ȣ �ޱ�
		String idx = req.getParameter("idx");
		//2. ��ȿ�� üũ => list.do�� redirect�̵�
		if(idx==null||idx.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			return;
		}
		BoardDAO dao = new BoardDAO();
		//3_1. ��ȸ�� ����
		boolean b=dao.updateReadnum(idx);
		//3_2. BoardDAO������ viewBoard(idx) ȣ��
		//4. ==> ��ȯ�� BoardVO�� req�� ����		
		BoardVO vo = dao.viewBoard(idx);
		req.setAttribute("board", vo);
		//5. viewPage����
		this.setViewPage("boardView.jsp");
		this.setRedirect(false);
	}

}
