package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.controller.AbstractAction;

public class BoardWriteAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//0.파일 업로드 처리하기
		//1) 업로드할 디렉토리 절대경로 얻기 ==> application.getRealPath("Upload");
		ServletContext application = req.getServletContext();
		String upDir = application.getRealPath("/Upload");
		System.out.println(upDir);
		MultipartRequest mr = null;
		try {
			DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
			mr=new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",df);
		}catch(IOException e){
			req.setAttribute("message", "파일 업로드 실패: 용량은 10M까지 가능");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("../msg.jsp");
			this.setRedirect(false);
			e.printStackTrace();
			return;
		}
		//1. 제목,이름,내용,비번 받아오기
		String subject = mr.getParameter("subject");
		String name = mr.getParameter("name");
		String content= mr.getParameter("content");
		String pwd=mr.getParameter("pwd");
		//String filename=mr.getParameter("filename");(X)
		//첨부파일을 얻어올 때는 mr.getFilesystemName("filename")
		String filename=mr.getFilesystemName("filename");
		String originFilename =mr.getOriginalFileName("filename");
		long filesize=0;
		File file=mr.getFile("filename");
		filesize = (file!=null) ? file.length():0;
		//2. 유효성 체크(이름,내용)
		if(subject==null||name==null||content==null||pwd==null||subject.trim().isEmpty()
				||name.trim().isEmpty()||content.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("write.do");
			this.setRedirect(true);//redirect방식으로 글쓰기 페이지로 이동시키자.
			return;
		}
		//3. BoardVO에 담아주기
		BoardVO board = new BoardVO(0,name,subject,content,pwd,null,0,filename,originFilename,filesize);
		//4. BoardDAO생성후 insertBoard()호출
		BoardDAO dao = new BoardDAO();
		int n = dao.insertBoard(board);
		//5. 그 결과에 따라 message,loc req에 저장
		String message=(n>0)? "성공":"실패";
		String loc=(n>0)? "list.do":"javascript:history.back()";
		req.setAttribute("message", message);
		req.setAttribute("loc", loc);
		//6. 뷰페이지와 이동방식 지정
		this.setViewPage("../msg.jsp");
		this.setRedirect(false);
	}
}
