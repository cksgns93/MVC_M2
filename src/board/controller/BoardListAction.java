package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardListAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String cpStr=req.getParameter("cpage");
		String psStr=req.getParameter("pageSize");
		if(cpStr==null||cpStr.trim().isEmpty()) {
			cpStr="1";
		}
		HttpSession session=req.getSession();
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
			pageSize=Integer.parseInt(psStr);//한 페이지당 보여줄 게시글 수
		}
		
		//BoardDAO객체 생성해서 메소드 호출 listBoard()
		BoardDAO dao = new BoardDAO();
		//1. 총 게시글 수 가져오기
		int totalCount=dao.getTotalCount();
		int pageCount = (totalCount-1)/pageSize+1;
		if(cpage<1) {
			cpage=1;
		}else if(cpage>pageCount) {
			cpage=pageCount;
		}
		//DB에서 끊어오기 위한 변수 (start, end)
		int end = pageSize*cpage;
		int start = end-pageSize+1;
		
		//2. 게시목록 가져오기
		List<BoardVO> arr = dao.listBoard(start,end);
		int pagingBlock=5;//페이지를 5개 단위로 블럭 처리
		int prevBlock=0, nextBlock=1;
		//이전 5개 [1][2][3][4][5] 아후 5개
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
		
		//반환해주는 List<BoardVO>를 req에 저장 (key값: boardArr)
		req.setAttribute("cpage", cpage);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("boardArr", arr);
		req.setAttribute("pagingBlock", pagingBlock);
		req.setAttribute("prevBlock", prevBlock);
		req.setAttribute("nextBlock", nextBlock);
		this.setViewPage("boardList2.jsp");
		this.setRedirect(false);
	}

}
