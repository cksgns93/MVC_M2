package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
//요청 uri => /MVCWeb/board/write.do
//뷰페이지 물리적 경로 => /MVCWeb/WebContent/board/boardWrite.jsp
public class BoardFormAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		this.setViewPage("boardWrite.jsp");
		this.setRedirect(false);
	}
}
