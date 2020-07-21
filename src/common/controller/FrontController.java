package common.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { "*.do" }, 
		initParams = { 
				@WebInitParam(
						name = "config", 
						value = "C:\\myjava\\workspace\\MVCweb\\WebContent\\WEB-INF\\Command.properties")
		})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> cmdMap = new HashMap<>();
	//Command.properties에 설정되어 있는 값들을 해쉬맵에 옮길 예정
	
	public void init(ServletConfig conf) throws ServletException {
		System.out.println("init()호출됨..");
		String props = conf.getInitParameter("config");
		System.out.println(props);
		Properties pr = new Properties();
		//Command.properties파일 정보를 Properties로 옮겨보자
		try {
			FileInputStream fis = new FileInputStream(props);
			pr.load(fis);
			if (fis!=null) fis.close();
			
			//pr의 key값들을 추출해보자.
			Enumeration<Object> en = pr.keys();
			while(en.hasMoreElements()) {
				String cmd = en.nextElement().toString();//key값 "/index.do"
				System.out.println("cmd= "+cmd);
				String className=pr.getProperty(cmd);//value값 "common.controller.IndexAction"
				if(className!=null) {
					className=className.trim();
				}
				System.out.println("className= "+className);
				//className의 클래스를 인스턴스화
				Class<?> cls = Class.forName(className);
				Object cmdInstance = cls.newInstance();
				//해당 클래스의 객체를 생성해줌
				cmdMap.put(cmd, cmdInstance);
			}//while() --
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}//init() ---

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	
	protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		//1. 클라이언트의 요청 URI를 분석해보자.
		String cmdUri = req.getServletPath();
		System.out.println("cmdUri= "+cmdUri);
		
		Object instance = cmdMap.get(cmdUri);
		if(instance==null) {
			System.out.println("action이 null");
			throw new ServletException("Action이 null");
		}
		
		AbstractAction action=(AbstractAction)instance;
		//서브컨트롤러(액션)가 수행할 메소드를 호출한다.
		try {
			action.execute(req, res);
			//execute에서는 로직을 수행한 뒤에 뷰페이지와 이동방식을 지정
			String viewPage=action.getViewPage();
			boolean isRedirect=action.isRedirect();
			if(isRedirect) {
				//redirect 방식으로 이동
				res.sendRedirect(viewPage);
			}else {
				//forward 방식으로 이동
				RequestDispatcher disp = req.getRequestDispatcher(viewPage);
				disp.forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
