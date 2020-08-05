package shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.base.CommonUtil;
import common.controller.AbstractAction;
import shop.domain.CartVO;
import shop.persistence.CartDAOMyBatis;
import user.domain.UserVO;

public class CartAddAction extends AbstractAction {


	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. ��ǰ��ȣ, ���� �ޱ�
		String pnum= req.getParameter("pnum");
		String oqty=req.getParameter("oqty");
		
		//2. ��ȿ�� üũ
		if(pnum==null||oqty==null||pnum.trim().isEmpty()||oqty.trim().isEmpty()) {
			this.setViewPage("../index.do");
			this.setRedirect(true);
			return;
		}
		//3. ȸ����ȣ �˾Ƴ���
		HttpSession ses = req.getSession();
		UserVO loginUser=(UserVO)ses.getAttribute("loginUser");
		int idx=loginUser.getIdx();
		int oqty_int=Integer.parseInt(oqty);
		int pnum_int=Integer.parseInt(pnum);
		//īƮ�� ���
		CartVO cvo = new CartVO(null,oqty_int,idx,pnum_int);
		
		CartDAOMyBatis dao = new CartDAOMyBatis();
		int n = dao.addCart(cvo);
		
		String msg = (n>0) ? "��ٱ��� ��� ����":"��� ����";
		String loc = (n>0) ? "cart.do":"javascript:history.back()";
		
		String view = CommonUtil.addMsgLoc(req, msg, loc);
		
		this.setViewPage(view);
		this.setRedirect(false);
		
	}

}
