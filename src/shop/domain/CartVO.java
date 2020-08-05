package shop.domain;

import java.io.Serializable;

public class CartVO implements Serializable {
	
	private String cartNum;//��ٱ��Ϲ�ȣ
	private int oqty;//��ٱ��� ����
	private int idx; //ȸ����ȣ
	private int pnum;//��ǰ��ȣ
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
