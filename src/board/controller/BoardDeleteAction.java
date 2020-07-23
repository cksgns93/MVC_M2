package board.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardDeleteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. 삭제할 글번호와 비번 받기
		String idx = req.getParameter("idx");
		String pwd = req.getParameter("pwd");
		System.out.println(idx+"/"+pwd);
		//2. 유효성 체크 => list.do로 redirect
		if(idx==null||pwd==null||idx.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			return;
		}
		//3. BoardDAO생성해서 viewBoard(idx) ==> BoardVO받기
		//	사용자가 입력한 pwd와 BoardVO의 pwd 값이 같으면 삭제 처리 메소드 호출
		//  deleteBoard(idx)
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.viewBoard(idx.trim());
		if(pwd.equals(vo.getPwd())) {
			//업로드한 파일이 있다면 서버의 Upload디렉토리에서 해당 파일을 삭제 처리한다.
			if(vo.getFilesize()>0) {
				ServletContext app =req.getServletContext();
				String upDir = app.getRealPath("Upload");
				String path = upDir+File.separator+vo.getFilename();
				File file= new File(path);
				if(file.exists()) {
					boolean a = file.delete();
					System.out.println("참조파일 삭제 여부"+a);
				}
			}
			dao.deleteBoard(idx);
			req.setAttribute("message", "삭제");
			req.setAttribute("loc", "list.do");
		}else{
			req.setAttribute("message", "비밀번호가 일치하지 않음");
			req.setAttribute("loc", "javascript:history.back()");
		}
		//그 결과 메시지와 이동경로를("list.do")를 req에 저장한다.
		this.setViewPage("../msg.jsp");
		this.setRedirect(false);
	}
}
