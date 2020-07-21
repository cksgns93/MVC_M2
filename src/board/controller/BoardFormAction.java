package board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.controller.AbstractAction;
//��û uri => /MVCWeb/board/write.do
//�������� ������ ��� => /MVCWeb/WebContent/board/boardWrite.jsp
public class BoardFormAction extends AbstractAction{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		this.setViewPage("boardWrite.jsp");
		this.setRedirect(false);
	}
}
