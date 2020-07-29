package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.base.CommonUtil;
import common.controller.AbstractAction;

public class BoardFindAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session=req.getSession();
		//�˻������� �˻��� �ޱ�
		String findType = req.getParameter("findType");
		String findKeyword=req.getParameter("findKeyword");
		if(findType==null||findType.equals("0")||findKeyword==null||findKeyword.trim().isEmpty()) {
			findType=(String)session.getAttribute("findType");
			findKeyword=(String)session.getAttribute("findKeyword");
			if(findType==null||findType.equals("0")||findKeyword==null||findKeyword.trim().isEmpty()) {
				this.setViewPage(CommonUtil.addMsgBack(req, "�˻������� �˻�� �Է��ϼ���"));
				this.setRedirect(false);
				return;
			}
		}
		session.setAttribute("findType", findType);
		session.setAttribute("findKeyword", findKeyword);
		String cpStr=req.getParameter("cpage");
		String psStr=req.getParameter("pageSize");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		
		if(psStr==null||psStr.trim().isEmpty()) {
			psStr=(String)session.getAttribute("pageSize");
			if(psStr==null) {				
				psStr="5";
			}
		}
		session.setAttribute("pageSize", psStr);
		int cpage=Integer.parseInt(cpStr);
		int pageSize=5;
		if(psStr!=null) {
			pageSize=Integer.parseInt(psStr);//�� �������� ������ �Խñ� ��
		}
		
		//BoardDAO��ü �����ؼ� �޼ҵ� ȣ�� listBoard()
		BoardDAO dao = new BoardDAO();
		//1. �� �Խñ� �� ��������
		int totalCount=dao.getTotalCount(findType,findKeyword);
		int pageCount = (totalCount-1)/pageSize+1;
		if(cpage<1) {
			cpage=1;
		}else if(cpage>pageCount) {
			cpage=pageCount;
		}
		//DB���� ������� ���� ���� (start, end)
		int end = pageSize*cpage;
		int start = end-pageSize+1;
		
		//2. �Խø�� ��������
		List<BoardVO> arr = dao.findBoard(start,end,findType,findKeyword);
		int pagingBlock=5;//�������� 5�� ������ �� ó��
		int prevBlock=0, nextBlock=1;
		//���� 5�� [1][2][3][4][5] ���� 5��
		/*
		 * [1][2][3][4][5] | [6][7][8][9][10] | [11][12][13][14][15] | ...
		 * cpage			pagingBlock		prevBlock	nextBlock
		 * 1,2,3,4,5		5				0			6
		 * 6,7,8,9,10		5				5			11
		 * 11,12,13,14,15	5				10			16
		 * 
		 * prevBlock = (cpage-1)/pagingBlock * pagingBlock 
		 * nextBlock = prevBlock + pagingBlock + 1 
		 * 
		 * */	
		prevBlock = (cpage-1)/pagingBlock * pagingBlock;
		nextBlock = prevBlock + pagingBlock + 1;
		
		//��ȯ���ִ� List<BoardVO>�� req�� ���� (key��: boardArr)
		req.setAttribute("cpage", cpage);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("boardArr", arr);
		req.setAttribute("pagingBlock", pagingBlock);
		req.setAttribute("prevBlock", prevBlock);
		req.setAttribute("nextBlock", nextBlock);
		req.setAttribute("findType", findType);
		req.setAttribute("findKeyword", findKeyword);
		this.setViewPage("boardFind.jsp");
		this.setRedirect(false);
	}

}
