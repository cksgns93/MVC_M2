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
		//��ٱ��� ��ȣ,���� �ޱ�
		String cartNum = req.getParameter("cartNum");
		String oqty=req.getParameter("oqty");
		if(cartNum==null||oqty==null) {
			this.setRedirect(true);
			this.setViewPage("cart.do");
			return;
		}
		int oqty_num=Integer.parseInt(oqty);
		//������ 0�� ���� �ش� ��ǰ ���� ó��, ������ ����� ���� �ش� ��ǰ�� ������ ����ó��
		if(oqty_num==0) {
			dao.deleteCart(cartNum);
		}else if(oqty_num>0) {
			CartVO cart=new CartVO();
			cart.setOqty(oqty_num);
			cart.setCartNum(cartNum);
			
			dao.updateOqty(cart);
		}else {
			//������ ������ ���� "������ ������� �ؿ�" alert , history.back()
			this.setViewPage(CommonUtil.addMsgBack(req, "������ ���"));
			this.setRedirect(false);
			return;
		}
		this.setRedirect(true);
		this.setViewPage("cart.do");
	}

}
