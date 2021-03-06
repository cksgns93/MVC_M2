<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/top.jsp"/>
<div class="text-left p-5">
	<h1 class="text-center">MVC Board List</h1>
	<p class="text-center">
		<a href="write.do">글쓰기</a>|
		<a href="list.do">글목록</a>
	</p>
	<p>
	<!-- pageSize관련 form 시작 -->
	<div class="row">
	<div class="col-md-3 offset-md-8 p-3 m-3">
	<form name="listF" id="listF" action="list.do#bbs">
		<input type="hidden" name="cpage" value="${cpage}">
		<select name="pageSize" class="form-control " onchange="submit()">
			<option value="5">::페이지 사이즈 선택::</option>
			<c:forEach var="ps" begin="5" end="20" step="5">
			<option value="${ps}">페이지 사이즈 ${ps}</option>
			</c:forEach>
		</select>
	</form>
	</div>
	</div>
	<!-- ------------------- -->
	</p>
	<table id="bbs" class="table table-striped table-hover">
		<tr class="table-secondary">
			<th width="10%">글번호</th>
			<th width="40%">제목</th>
			<th width="20%">글쓴이</th>
			<th width="20%">날   짜</th>
			<th width="10%">조회수</th>
		</tr>
		<!-- 반복문 -->
		<c:if test="${boardArr!=null||empty boardArr}">
			<c:forEach var="board" items="${boardArr}">
				<tr>
					<td>${board.idx}</td>
					<td><a href="view.do?idx=${board.idx}">${board.subject}</a>
					<c:if test="${board.filesize>0}">
						<img src="../images/attatch.PNG" width="16px">
					</c:if>
					</td>
					<td>${board.name}</td>
					<td>
					<!-- 날짜 포맷 -->
					<fmt:formatDate value="${board.wdate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>${board.readnum}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${boardArr==null&&not empty boardArr }">
			<tr><td colspan="5">데이터가 없습니다.</td></tr>
		</c:if>
		<!-- when otherwise로 if else 문을 사용할 수 있다. -->
		<c:choose>
			<c:when test="true"></c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
		<!-- 반복문 -->
		<tr>
			<td colspan="3" class="text-center">
			<!-- begin:시작 end:끝 step:증가치 -->
			<ul class="pagination justify-content-center">
			<c:forEach var="i" begin="1" end="${pageCount}" step="1">
			<li class="page-item <c:if test="${cpage==i}">active</c:if>"><a class="page-link" href="list.do?cpage=${i}#bbs">${i}</a></li>
			</c:forEach>
			</ul>
			</td>
			<td colspan="2" class="text-center">총게시글수: 
			<span class="text-primary">${totalCount}</span>
			<br>
			<span>현재페이지</span>/<span>총페이지수</span>
			</td>
		</tr>
	</table>
</div>
<jsp:include page="/foot.jsp"/>