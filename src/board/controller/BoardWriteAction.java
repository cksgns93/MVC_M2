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
		//0.���� ���ε� ó���ϱ�
		//1) ���ε��� ���丮 ������ ��� ==> application.getRealPath("Upload");
		ServletContext application = req.getServletContext();
		String upDir = application.getRealPath("/Upload");
		System.out.println(upDir);
		MultipartRequest mr = null;
		try {
			DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
			mr=new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",df);
		}catch(IOException e){
			req.setAttribute("message", "���� ���ε� ����: �뷮�� 10M���� ����");
			req.setAttribute("loc", "javascript:history.back()");
			this.setViewPage("../msg.jsp");
			this.setRedirect(false);
			e.printStackTrace();
			return;
		}
		//1. ����,�̸�,����,��� �޾ƿ���
		String subject = mr.getParameter("subject");
		String name = mr.getParameter("name");
		String content= mr.getParameter("content");
		String pwd=mr.getParameter("pwd");
		//String filename=mr.getParameter("filename");(X)
		//÷�������� ���� ���� mr.getFilesystemName("filename")
		String filename=mr.getFilesystemName("filename");
		String originFilename =mr.getOriginalFileName("filename");
		long filesize=0;
		File file=mr.getFile("filename");
		filesize = (file!=null) ? file.length():0;
		//2. ��ȿ�� üũ(�̸�,����)
		if(subject==null||name==null||content==null||pwd==null||subject.trim().isEmpty()
				||name.trim().isEmpty()||content.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("write.do");
			this.setRedirect(true);//redirect������� �۾��� �������� �̵���Ű��.
			return;
		}
		//3. BoardVO�� ����ֱ�
		BoardVO board = new BoardVO(0,name,subject,content,pwd,null,0,filename,originFilename,filesize);
		//4. BoardDAO������ insertBoard()ȣ��
		BoardDAO dao = new BoardDAO();
		int n = dao.insertBoard(board);
		//5. �� ����� ���� message,loc req�� ����
		String message=(n>0)? "����":"����";
		String loc=(n>0)? "list.do":"javascript:history.back()";
		req.setAttribute("message", message);
		req.setAttribute("loc", loc);
		//6. ���������� �̵���� ����
		this.setViewPage("../msg.jsp");
		this.setRedirect(false);
	}
}
