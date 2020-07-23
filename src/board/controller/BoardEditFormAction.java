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
		//1. 수정할 글 번호, 비번 받기
		String idx = req.getParameter("idx");
		String pwd = req.getParameter("pwd");
		//2. 유효성 체크
		if(idx==null||pwd==null||idx.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			return;
		}
		//3. 수정할 글의 비번과 사용자가 입력한 비번이 일치하면 해당 글 내용을
		//req에 저장
		BoardDAO dao = new BoardDAO();
		BoardVO board=dao.viewBoard(idx.trim());
		if(board==null) {
			this.setViewPage(CommonUtil.addMsgBack(req, "해당 글은  없어요"));
			this.setRedirect(false);
			return;
		}
		if(!board.getPwd().equals(pwd)) {
			//4. 일치하지 않으면 메시지 처리
			this.setViewPage(CommonUtil.addMsgBack(req, "비밀번호가 일치하지 않아요"));
			this.setRedirect(false);
		}else {
			//비번 일치한다면
			req.setAttribute("board", board);
			this.setViewPage("boardEdit.jsp");
			this.setRedirect(false);
		}
	}

}
