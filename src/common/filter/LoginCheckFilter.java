package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import common.base.CommonUtil;
import user.domain.UserVO;

/**
 *    /user/*�� �����ϴ� ��û�� �� ��� �α���üũ ���Ͱ� �ش� ��û�� ���͸��Ѵ�.
 */
@WebFilter(urlPatterns = {"/user/*","/admin/*"})
public class LoginCheckFilter implements Filter {

    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("LoginCheckFilter...");
		//���ǿ� ����Ǿ� �ִ� loginUser�� ������.
		//���� ����Ǿ� �ִ� loginUser�� ���ٸ� "�α��� �ؾ� �̿��� �� �־��"�� �����ְ� return
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession ses= req.getSession();
		UserVO loginUser = (UserVO)ses.getAttribute("loginUser");
		if(loginUser==null) {
			String msg = "�α��� �ؾ� �̿��� �� ����";
			String loc=req.getContextPath()+"/signin.do";
			String viewPage = CommonUtil.addMsgLoc(req,msg,loc);
			RequestDispatcher disp=req.getRequestDispatcher(viewPage);
			disp.forward(req, response);
			return;
		}
		//�׷��� ������ ���� ���ͷ� �ѱ��.
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
