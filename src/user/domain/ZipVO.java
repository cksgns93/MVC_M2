package user.domain;

public class ZipVO {
	private int post_code;
	private String sido_kor;	
	private String doro_kor;
	private String sigungu_bld_name;

	public ZipVO(int post_code, String sido_kor, String doro_kor, String sigungu_bld_name) {
		super();
		this.post_code = post_code;
		this.sido_kor = sido_kor;
		this.doro_kor = doro_kor;
		this.sigungu_bld_name = sigungu_bld_name;
	}
	
	public int getPost_code() {
		return post_code;
	}
	public void setPost_code(int post_code) {
		this.post_code = post_code;
	}
	public String getSido_kor() {
		return sido_kor;
	}
	public void setSido_kor(String sido_kor) {
		this.sido_kor = sido_kor;
	}
	public String getDoro_kor() {
		return doro_kor;
	}
	public void setDoro_kor(String doro_kor) {
		this.doro_kor = doro_kor;
	}
	public String getSigungu_bld_name() {
		return sigungu_bld_name;
	}
	public void setSigungu_bld_name(String sigungu_bld_name) {
		this.sigungu_bld_name = sigungu_bld_name;
	}
	
}
