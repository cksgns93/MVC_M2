<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% 
	String myctx =request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyHome</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<div class="jumbotron text-center" style="margin-bottom:0">
		<h1>MyHome Page</h1>
		<p>Welcome to MyHome Page</p>
	</div>
	<!-- navbar -->
	<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
	  <ul class="navbar-nav">
	    <li class="nav-item active">
	      <a class="nav-link" href="<%=myctx %>/index.do">Home</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=myctx %>/member/signup.jsp">SignUp</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=myctx %>/login/signin.jsp">SignIn</a>
	    </li>
	    <li class="nav-item bg-primary">
	      <a class="nav-link" href="#">님 로그인 중</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=myctx %>/login/logout.jsp">Logout</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=myctx %>/board/write.do">Board Write</a>
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="<%=myctx %>/board/list.do">Board List</a>
	    </li>
	  </ul>
	</nav>
	<!-- navbar  -->
	<div class="container" style="margin-top:30px;margin-bottom:30px">
		<div class="row" style="height:auto;">
			<div class="col-md-3">
				<ul class="list-group">
					<li class="list-group-item"><a href="<%=myctx %>/example/form.jsp">입력폼</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/example/form2.jsp">입력폼2</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/example/form3.jsp">입력폼3</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/beans/input.jsp">Beans(page,session,application)</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/beans/input.jsp">Beans(request)</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/login/sessionTest.jsp">Session테스트</a></li>
					<li class="list-group-item"><a href="<%=myctx %>/login/memberTest.jsp">회원 인증 페이지</a></li>
					<li class="list-group-item"><a href="/MyWeb/cookie/cookieTest.jsp">cookie테스트</a></li>
					<li class="list-group-item"><a href="/MyWeb/login/adminCheckModule.jsp">관리자테스트</a></li>
					<li class="list-group-item"><a href="/MyWeb/file/upload.jsp">파일업로드1</a></li>
					<li class="list-group-item"><a href="/MyWeb/file/upload2.jsp">파일업로드2</a></li>
					<li class="list-group-item"><a href="/MyWeb/file/fileList.jsp">파일업로드목록</a></li>
				</ul>
			</div>
			<div class="col-md-9">