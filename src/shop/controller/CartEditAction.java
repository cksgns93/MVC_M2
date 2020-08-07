package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.domain.CartVO;
import shop.persistence.CartDAOMyBatis;

public class CartEditAction extends AbstractAction {

	CartDAOMyBatis dao = new CartDAOMyBatis();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//장바구니 번호,수량 받기
		String cartNum = req.getParameter("cartNum");
		String oqty=req.getParameter("oqty");
		if(cartNum==null||oqty==null) {
			this.setRedirect(true);
			this.setViewPage("cart.do");
			return;
		}
		int oqty_num=Integer.parseInt(oqty);
		//수량이 0일 경우는 해당 상품 삭제 처리, 수량이 양수일 경우는 해당 상품의 수량을 수정처리
		if(oqty_num==0) {
			dao.deleteCart(cartNum);
		}else if(oqty_num>0) {
			CartVO cart=new CartVO();
			cart.setOqty(oqty_num);
			cart.setCartNum(cartNum);
			
			dao.updateOqty(cart);
		}else {
			//수량이 음수일 경우는 "수량은 양수여야 해요" alert , history.back()
			this.setViewPage(CommonUtil.addMsgBack(req, "수량은 양수"));
			this.setRedirect(false);
			return;
		}
		this.setRedirect(true);
		this.setViewPage("cart.do");
	}

}
