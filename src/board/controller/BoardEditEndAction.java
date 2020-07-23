package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.base.CommonUtil;
import common.controller.AbstractAction;

public class BoardEditEndAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//0. 파일 업로드 처리
		ServletContext app = req.getServletContext();
		String upDir=app.getRealPath("/Upload");
		System.out.println(upDir);
		MultipartRequest mr =null;
		try {
			mr=new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		}catch(IOException e){//multipart/form-data가 아니거나 업로드 용량 초과시 발생
			e.printStackTrace();
			throw new ServletException(e);
		}
		//1. 글번호, 작성자, 글내용, 비밀번호, 첨부파일명 파라미터 값 받기
		String idx = mr.getParameter("idx");
		String name = mr.getParameter("name");
		String content = mr.getParameter("content");
		String pwd =mr.getParameter("pwd");
		String subject = mr.getParameter("subject");
		//String filename = mr.getParameter("filename");
		String filename = mr.getFilesystemName("filename");//첨부파일
		String originFilename = mr.getOriginalFileName("filename");//원본파일명
		String old_filename=mr.getParameter("old_filename");
		if(filename!=null) {
			//새로 첨부파일이 있는 경우
			if(old_filename!=null||!old_filename.trim().isEmpty()) {
				//예전에 첨부한 파일이 있을 경우 ==> 예전 파일 삭제 처리
				File oldFile = new File(upDir+File.separator+old_filename);
				if(oldFile.exists()) {
					oldFile.delete(); //삭제 처리
				}
			}
		}
		long filesize = 0;
		if(filename!=null) {
			File file = mr.getFile("filename");
			filesize=file.length();
		}
		//2. 유효성체크(글번호,작성자,비번)
		if(idx==null||name==null||pwd==null||idx.trim().isEmpty()||name.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			System.out.println("??");
			return;
		}
		//3. BoardVO에 담아주기
		int idx_int=Integer.parseInt(idx.trim());
		BoardVO board = new BoardVO(idx_int,name,subject,content,pwd,null,0,filename,originFilename,filesize);
		//4. BoardDAO생성해서 updateBoard(board) 반환형 int
		BoardDAO dao = new BoardDAO();
		int n = dao.updateBoard(board);
		String msg = (n>0) ? "성공":"실패";
		String loc = (n>0) ? "list.do":"javascript:history.back()";
		//5. 실행결과 메시지 처리
		this.setViewPage(CommonUtil.addMsgLoc(req, msg, loc));
		this.setRedirect(false);
		
	}

}
