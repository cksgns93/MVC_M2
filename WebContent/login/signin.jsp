<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <!-- SignIn.jsp-->
<jsp:include page="/top.jsp"/>
<script>
	//로그인 버튼을 클릭하면 유효성 체크 userid,pwd 입력값 체크
	$(function(){
		$("#btnLogin").click(function(){
			if(!loginF.userid.value||!loginF.pwd.value){
				alert("아이디 비밀번호 입력");
				return;
			}
			//입력값이 올바르면 submit()되도록 처리
			loginF.submit();
		});
	});
	
	
	
</script>
<%
	//저장된 아이디 쿠키를 거내서 아이디 입력폼에 해당 값을 출력하자
	Cookie []cks = request.getCookies();
	String val="";
	boolean flag=false;
	if(cks!=null){
		for(Cookie ck : cks){
			String key = ck.getName();
			if(key.equals("saveId")){
				val = ck.getValue();
				flag=true;
				break;
			}
		}
	}
%>
<h1 class="text-primary text-center">SignIn</h1>
	<form name="loginF" id="loginF" method="post" action="loginEnd.do">
		<div class="row">
			<div class="col-md-8 offset-md-2">
			<table class="table table-bordered">
				<tr>
					<td>아이디</td>
					<td>
						<input type="text" name="userid" id="userid" value="<%=val%>"  placeholder="User ID" class="form-control">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
						<input type="password" name="pwd" id="pwd" placeholder="Password" class="form-control">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<label for="saveId" class="form-check-label ml-4">
							<input type="checkbox" class="form-check-input" name="saveId" id="saveId" <%=(flag)? "checked":""%>>아이디 저장
						</label>
						<button type="button"class="btn btn-success ml-3" id="btnLogin">로그인</button>
					</td>
				</tr>
			</table>
			</div>
		</div>
	</form>
<jsp:include page="/foot.jsp"/>