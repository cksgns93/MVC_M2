package common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	//사전 처리나 사후 처리할 일이 있으면 doFilter()메소드 안에서 구현한다.
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		//post방식일 때의 한글 처리를 여기서 사전에 처리하도록 하자
		//System.out.println("EncodingFilter doFilter()호출됨");
		req.setCharacterEncoding("UTF-8");
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
