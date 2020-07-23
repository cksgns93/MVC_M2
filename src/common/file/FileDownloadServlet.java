package common.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDown")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		download(request,response);
	}
	
	private void download(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		//1.다운로드받을 파일명 받기
		String fname = req.getParameter("fname");
		String ofname = req.getParameter("origin_fname");//원본파일명
		System.out.println("fname: "+fname);
		//2.절대경로 만들기
		ServletContext application = req.getServletContext();
		String upDir = application.getRealPath("/Upload");
		String path=upDir+File.separator+fname;
		System.out.println("path: "+path);
		
		//res.setContentType("application/unknown");
		res.setContentType("application/octet-stream");
		//알수 없는 형식으로 지정 ==> 브라우저는 컨텐트 타입을 보고
		//표현할 수 있는 형식이면 해당 형식으로 표현하고 잘 모르는 형식이면 다운로드창을 실행시켜
		//다운로드 받도록 처리한다.
		String fname_en = new String(ofname.getBytes(),"ISO-8859-1");
		String arg1="attachment; filename="+fname_en;
		res.setHeader("Content-Disposition", arg1);
		//3.해당 경로와 스트림 연결
		//(FileInputStream)=> 파일을 읽어서
		//(ServletOutputStream)=> 클라이언트쪽으로 내보낸다.
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		ServletOutputStream sos = res.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		int n=0;
		byte []data=new byte[4000];
		while((n=bis.read(data))!=-1) {
			bos.write(data,0,n);
			bos.flush();
		}
		bis.close();
		bos.close();
		sos.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		download(request,response);
	}

}
