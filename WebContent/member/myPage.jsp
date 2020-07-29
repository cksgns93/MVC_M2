<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<div class="text-center p-5">
	<h1 class="m-3 text-success">MyPage</h1>
	  <div class="card" style="width:400px">
	    <img class="card-img-top" src="../images/1.JPG" alt="MyImage" style="width:100%">
	    <div class="card-body">
	      <h4 class="card-title">${loginUser.name}[${loginUser.userid}]</h4>
	      <p class="card-text">
	      	마일리지 : ${loginUser.mileage}<br>
	      	가입일 : ${loginUser.indate}<br>
	      	주소 : ${loginUser.addr1}<br>
	      	회원상태 : ${loginUser.mstate}<br>
	      </p>
	      <a href="#" class="btn btn-primary stretched-link">See Profile</a>
	    </div>
	  </div>
</div>
<jsp:include page="/foot.jsp"/>