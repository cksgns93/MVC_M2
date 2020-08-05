package shop.controller;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.domain.ProductVO;
import shop.persistence.ProductDAOMyBatis;

public class ProductInsertAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//1.업로드할 디렉토리 절대경로 얻어오기
		ServletContext app = req.getServletContext();
		String upDir = app.getRealPath("/images");
		System.out.println(upDir);
		//2.이미지파일들 업로드 처리 - cos.jar
		MultipartRequest mr
		=new MultipartRequest(req,upDir,10*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		
		//3.사용자가 입력한 값 받기, 유효성 체크

		String upCg_code = mr.getParameter("upCg_code");
		String downCg_code = mr.getParameter("downCg_code");
		String pname = mr.getParameter("pname");
		int price = Integer.parseInt(mr.getParameter("price"));
		int saleprice = Integer.parseInt(mr.getParameter("saleprice"));
		int pqty = Integer.parseInt(mr.getParameter("pqty"));
		int point = Integer.parseInt(mr.getParameter("point"));
		String pspec = mr.getParameter("pspec");
		String pcontents = mr.getParameter("pcontents");
		String pcompany = mr.getParameter("pcompany");
		String pimage1=mr.getFilesystemName("pimage1");
		String pimage2=mr.getFilesystemName("pimage2");
		String pimage3=mr.getFilesystemName("pimage3");
		System.out.println(upCg_code);
		//업로드한 파일명과 크기 알아내기
		/*Enumeration<String> en=mr.getFileNames();
		if(en!=null) {
			while(en.hasMoreElements()) {
				String param=en.nextElement();
				String fname = mr.getFilesystemName(param);
				System.out.println(param+"/"+fname);
			}			
		}*/
		
		//4.ProductVO에 담아주기
		ProductVO item = new ProductVO(null,upCg_code,downCg_code,pname,pimage1,pimage2,pimage3,price,saleprice,pqty,point,pspec,pcontents,pcompany,null);
		//5.DAO의 productInsert(vo) 호출
		ProductDAOMyBatis dao = new ProductDAOMyBatis();
		int n = dao.productInsert(item);
		String msg= (n>0)? "등록 성공":"등록 실패";
		String loc= (n>0)? "prodList.do":"javascript:history.back()";
		
		//6.그 결과 메시지 처리
		String viewPage=CommonUtil.addMsgLoc(req,msg,loc);
		this.setViewPage(viewPage);
		this.setRedirect(false);
	}

}
