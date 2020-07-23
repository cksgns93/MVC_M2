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
		//0. ���� ���ε� ó��
		ServletContext app = req.getServletContext();
		String upDir=app.getRealPath("/Upload");
		System.out.println(upDir);
		MultipartRequest mr =null;
		try {
			mr=new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		}catch(IOException e){//multipart/form-data�� �ƴϰų� ���ε� �뷮 �ʰ��� �߻�
			e.printStackTrace();
			throw new ServletException(e);
		}
		//1. �۹�ȣ, �ۼ���, �۳���, ��й�ȣ, ÷�����ϸ� �Ķ���� �� �ޱ�
		String idx = mr.getParameter("idx");
		String name = mr.getParameter("name");
		String content = mr.getParameter("content");
		String pwd =mr.getParameter("pwd");
		String subject = mr.getParameter("subject");
		//String filename = mr.getParameter("filename");
		String filename = mr.getFilesystemName("filename");//÷������
		String originFilename = mr.getOriginalFileName("filename");//�������ϸ�
		String old_filename=mr.getParameter("old_filename");
		if(filename!=null) {
			//���� ÷�������� �ִ� ���
			if(old_filename!=null||!old_filename.trim().isEmpty()) {
				//������ ÷���� ������ ���� ��� ==> ���� ���� ���� ó��
				File oldFile = new File(upDir+File.separator+old_filename);
				if(oldFile.exists()) {
					oldFile.delete(); //���� ó��
				}
			}
		}
		long filesize = 0;
		if(filename!=null) {
			File file = mr.getFile("filename");
			filesize=file.length();
		}
		//2. ��ȿ��üũ(�۹�ȣ,�ۼ���,���)
		if(idx==null||name==null||pwd==null||idx.trim().isEmpty()||name.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			System.out.println("??");
			return;
		}
		//3. BoardVO�� ����ֱ�
		int idx_int=Integer.parseInt(idx.trim());
		BoardVO board = new BoardVO(idx_int,name,subject,content,pwd,null,0,filename,originFilename,filesize);
		//4. BoardDAO�����ؼ� updateBoard(board) ��ȯ�� int
		BoardDAO dao = new BoardDAO();
		int n = dao.updateBoard(board);
		String msg = (n>0) ? "����":"����";
		String loc = (n>0) ? "list.do":"javascript:history.back()";
		//5. ������ �޽��� ó��
		this.setViewPage(CommonUtil.addMsgLoc(req, msg, loc));
		this.setRedirect(false);
		
	}

}
