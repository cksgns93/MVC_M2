package shop.domain;

import java.io.Serializable;

public class CartVO implements Serializable {
	
	private String cartNum;//장바구니번호
	private int oqty;//장바구니 수량
	private int idx; //회원번호
	private int pnum;//상품번호
	private java.sql.Date indate;
	
	public CartVO() {
		
	}

	public CartVO(String cartNum, int oqty, int idx, int pnum) {
		super();
		this.cartNum = cartNum;
		this.oqty = oqty;
		this.idx = idx;
		this.pnum = pnum;
	}

	public String getCartNum() {
		return cartNum;
	}

	public void setCartNum(String cartNum) {
		this.cartNum = cartNum;
	}

	public int getOqty() {
		return oqty;
	}

	public void setOqty(int oqty) {
		this.oqty = oqty;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public java.sql.Date getIndate() {
		return indate;
	}

	public void setIndate(java.sql.Date indate) {
		this.indate = indate;
	}
	
}
