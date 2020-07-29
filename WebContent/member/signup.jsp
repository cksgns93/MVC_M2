<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<script type="text/javascript">
	/*아이디 중복체크 팝업창 열기*/
	var win=null;
	function openWin(){
		f.flag.value="false";
		win=window.open("idCheck.do","idCheck","width=400, height=400, left=100, top=100");
	}
	function openWin2(){
		win2=window.open("postCheck.jsp","postCheck","width=600, height=400, left=100, top=100");
	}


	function check(){
		if(!f.name.value){
			alert('이름을 입력하세요');
			f.name.focus();
			return;
		}
		//아이디,비밀번호, hp1,hp2,hp3
		if(!f.userid.value){
			alert('아이디 입력하세요');
			f.userid.focus();
			return;
		}
		if(!f.pwd.value){
			alert('비밀번호 입력하세요');
			f.pwd.focus();
			return;
		}
		if(!f.pwd2.value){
			alert('비밀번호2 입력하세요');
			f.pwd2.focus();
			return;
		}
		if(!f.hp1.value){
			alert('전화번호 입력하세요');
			f.hp2.focus();
			return;
		}
		if(!f.hp2.value){
			alert('전화번호 입력하세요');
			f.hp1.focus();
			return;
		}
		if(!f.hp3.value){
			alert('전화번호 입력하세요');
			f.hp3.focus();
			return;
		}
		if(f.pwd.value!=f.pwd2.value){
			alert('비밀번호 확인 다름');
			return;
		}
		//if(f.flag.value=="false"){
		//	alert('아이디 중복 체크하세요');
		//	return;
		//}
		
		f.submit();
	}
</script>
<h1 class="text-primary m-5">SignUp</h1>
	<form name="f" action="signupEnd.do" method="post">
		<div class="row m-3">
			<div class="col-md-3">이름</div>
			<div class="col-md-6">
				<input type="text" name="name" id="name" placeholder="Name" class="form-control">
			</div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">아이디</div>
			<div class="col-md-6">
				<input type="text"  name="userid" placeholder="userid" class="form-control">
				<!-- hidden date -->
				<input type="hidden" name="flag" value="false">
				<!--  -->
			</div>
			<div class="col-md-3"><button type="button" onclick="openWin()"class="btn btn-success">아이디 중복체크</button></div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">비밀번호</div>
			<div class="col-md-6">
				<input type="password" name="pwd" id="pwd" placeholder="Password" class="form-control">
			</div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">비밀번호 확인</div>
			<div class="col-md-6">
				<input type="password" name="pwd2" id="pwd2" placeholder="Re Password" class="form-control">
			</div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">연락처</div>
			<div class="col-md-2">
				<input type="text" name="hp1" id="hp1"placeholder="HP1" class="form-control" maxlength="3">
			</div>
			<div class="col-md-2">
				<input type="text" name="hp2"id="hp2" placeholder="HP2" class="form-control" maxlength="4">
			</div>
			<div class="col-md-2">
				<input type="text" name="hp3"id="hp3" placeholder="HP3" class="form-control" maxlength="4">
			</div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">우편번호</div>
			<div class="col-md-6">
				<input type="text" name="post" id="post"placeholder="Post" class="form-control" maxlength="5">
			</div>
			<div class="col-md-3"><button type="button"class="btn btn-success" onclick="openWin2()">우편번호 찾기</button></div>
		</div>
		<div class="row m-3">
			<div class="col-md-3">주소</div>
			<div class="col-md-6">
				<input type="text" name="addr1" id="addr1" placeholder="Address1"class="form-control">
				<input type="text" name="addr2" id="addr2" placeholder="Address2"class="form-control">
			</div>
		</div>
		<div class="row m-3">
			<div class="col-md-12 text-center">
				<button type="button" class="btn- btn-primary" onclick="check()">회원가입</button>
				<button type="reset" class="btn- btn-primary">다시쓰기</button>
			</div>
		</div>
	</form>
<jsp:include page="/foot.jsp"/>