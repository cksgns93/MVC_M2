package common.base;

import javax.servlet.http.HttpServletRequest;

public class CommonUtil {
	public static String addMsgLoc(HttpServletRequest req, String msg, String loc) {
		req.setAttribute("message", msg);
		req.setAttribute("loc", loc);
		return "/msg.jsp";//뷰페이지 반환
	}
	public static String addMsgBack(HttpServletRequest req, String msg) {
		req.setAttribute("message", msg);
		req.setAttribute("loc", "javascript:history.back()");
		return "/msg.jsp";//뷰페이지 반환
	}
}
