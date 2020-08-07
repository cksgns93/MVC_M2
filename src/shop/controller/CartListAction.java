package shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.controller.AbstractAction;
import shop.domain.CartVO;
import shop.persistence.CartDAOMyBatis;
import user.domain.UserVO;

public class CartListAction extends AbstractAction {
	CartDAOMyBatis dao = new CartDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession ses = req.getSession();
		UserVO user=(UserVO)ses.getAttribute("loginUser");
		int idx=user.getIdx();

		//특정 회원의 장바구니 목록 가져오기
		List<CartVO> cartList = dao.selectCartByIdx(idx);
		//장바구니 총액과 총 포인트 가져오기
		CartVO cart = dao.getCartTotal(idx);
		
		if(cart!=null) {
			req.setAttribute("cartTotalPrice", cart.getCartTotalPrice());
			req.setAttribute("cartTotalPoint", cart.getCartTotalPoint());			
		}
		req.setAttribute("cartList", cartList);
		this.setViewPage("../shop/cartList.jsp");
		this.setRedirect(false);
	}
}
