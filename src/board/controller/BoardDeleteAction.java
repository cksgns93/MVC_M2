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
		//1. ������ �۹�ȣ�� ��� �ޱ�
		String idx = req.getParameter("idx");
		String pwd = req.getParameter("pwd");
		System.out.println(idx+"/"+pwd);
		//2. ��ȿ�� üũ => list.do�� redirect
		if(idx==null||pwd==null||idx.trim().isEmpty()||pwd.trim().isEmpty()) {
			this.setViewPage("list.do");
			this.setRedirect(true);
			return;
		}
		//3. BoardDAO�����ؼ� viewBoard(idx) ==> BoardVO�ޱ�
		//	����ڰ� �Է��� pwd�� BoardVO�� pwd ���� ������ ���� ó�� �޼ҵ� ȣ��
		//  deleteBoard(idx)
		BoardDAO dao = new BoardDAO();
		BoardVO vo = dao.viewBoard(idx.trim());
		if(pwd.equals(vo.getPwd())) {
			//���ε��� ������ �ִٸ� ������ Upload���丮���� �ش� ������ ���� ó���Ѵ�.
			if(vo.getFilesize()>0) {
				ServletContext app =req.getServletContext();
				String upDir = app.getRealPath("Upload");
				String path = upDir+File.separator+vo.getFilename();
				File file= new File(path);
				if(file.exists()) {
					boolean a = file.delete();
					System.out.println("�������� ���� ����"+a);
				}
			}
			dao.deleteBoard(idx);
			req.setAttribute("message", "����");
			req.setAttribute("loc", "list.do");
		}else{
			req.setAttribute("message", "��й�ȣ�� ��ġ���� ����");
			req.setAttribute("loc", "javascript:history.back()");
		}
		//�� ��� �޽����� �̵���θ�("list.do")�� req�� �����Ѵ�.
		this.setViewPage("../msg.jsp");
		this.setRedirect(false);
	}
}
