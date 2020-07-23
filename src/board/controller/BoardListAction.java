package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//BoardDAO��ü �����ؼ� �޼ҵ� ȣ�� listBoard()
		BoardDAO dao = new BoardDAO();
		//1. �� �Խñ� �� ��������
		int totalCount=dao.getTotalCount();
		//2. �Խø�� ��������
		List<BoardVO> arr = dao.listBoard();
		//��ȯ���ִ� List<BoardVO>�� req�� ���� (key��: boardArr)
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("boardArr", arr);
		this.setViewPage("boardList.jsp");
		this.setRedirect(false);
	}

}
