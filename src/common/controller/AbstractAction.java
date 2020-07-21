package common.controller;
//Action 인터페이스를 상속받는 
//추상클래스 : execute()추상메소드를 상속받아 가지고 있음
abstract public class AbstractAction implements Action{
	
	private String viewPage;//보여줄 뷰페이지 
	private boolean isRedirect=false;//페이지 이동방식이 redirect방식이면 true forward방식이면 false
	
	//setter, getter
	public String getViewPage() {
		return viewPage;
	}
	public void setViewPage(String viewPage) {
		this.viewPage = viewPage;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
}
