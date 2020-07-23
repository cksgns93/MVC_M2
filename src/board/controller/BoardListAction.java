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
		//BoardDAO객체 생성해서 메소드 호출 listBoard()
		BoardDAO dao = new BoardDAO();
		//1. 총 게시글 수 가져오기
		int totalCount=dao.getTotalCount();
		//2. 게시목록 가져오기
		List<BoardVO> arr = dao.listBoard();
		//반환해주는 List<BoardVO>를 req에 저장 (key값: boardArr)
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("boardArr", arr);
		this.setViewPage("boardList.jsp");
		this.setRedirect(false);
	}

}
