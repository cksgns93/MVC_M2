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

@WebFilter("/admin/*")
public class AdminCheckFilter extends LoginCheckFilter {

    /**
     * Default constructor. 
     */
    public AdminCheckFilter() {
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
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession ses= req.getSession();
		UserVO loginUser = (UserVO)ses.getAttribute("loginUser");
		if(loginUser==null) {
			super.doFilter(request, response, chain);
			return;
		}
		if(loginUser.getMstate()!=2) {
			String msg = "관리자만 이용할 수 있음";
			String viewPage = CommonUtil.addMsgBack(req,msg);
			RequestDispatcher disp=req.getRequestDispatcher(viewPage);
			disp.forward(req, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
