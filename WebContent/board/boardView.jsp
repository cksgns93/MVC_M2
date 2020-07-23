<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<jsp:include page="/top.jsp"/>
<div align="center" id="bbs" class="col-md-10 col-md-offset-1">    
	<h1>MVC Board</h1>
	<p>
	<a href="${pageContext.request.contextPath}/board/write.do">글쓰기</a>|
	<a href="${pageContext.request.contextPath}/board/list.do">글목록</a>
	<p>
<c:if test="${board==null||empty board}">  
<h2 class="text-danger">${param.idx} 번 글은 존재하지 않습니다.</h2>
</c:if>
<c:if test="${board!=null&&not empty board}">
<table class="table table-bordered">
	<tr>
		<td width="20%"><b>글번호</b></td>
		<td width="30%">${board.idx }</td>
		<td width="20%"><b>작성일</b></td>
		<td width="30%">
		<fmt:formatDate value="${board.wdate}" pattern="yyyy-MM-dd hh:mm:ss"/>
		</td>
	</tr>
	<tr>
		<td width="20%"><b>글쓴이</b></td>
		<td width="30%">${board.name}</td>
		<td width="20%"><b>조회수</b></td>
		<td width="30%">${board.readnum}</td>
	</tr>
	<tr>
		<td width="20%"><b>첨부파일</b></td>
		<td colspan="3">
		<a href="#" onclick="down()">${board.filename}</a> [<fmt:formatNumber value="${board.filesize}" pattern="###,###"/>]bytes
		</td>
	</tr>
	<tr>
		<td width="20%"><b>제목</b></td>
		<td colspan="3">${board.subject}</td>
	</tr>
	<tr>
		<td width="20%"><b>글내용</b></td>
		<td colspan="3">
		<pre>${board.content}</pre>
		</td>
	</tr>
	<tr>
		<td colspan="4" class="text-center">
		<a href="list.do"><i class="fa fa-2x fa-align-justify fa-fw"></i></a>|
		<a href="#" onclick="goEdit('${board.idx}')"><i class="fa fa-edit fa-fw fa-2x"></i></a>|
		<a href="#" onclick="goDel('${board.idx}')"><i class="fa fa-2x fa-cut fa-fw"></i></a>|
		<a href="javascript:goRe()"><i class="fa fa-2x fa-fw fa-reply hub"></i></a>
		</td>
	</tr>
</table>
</c:if>
<!-- 답변달기 form시작 --------------------- -->
<!-- hidden으로 부모글의 글번호(idx)와 제목(subject)를 넘긴다 -->

<!-- 삭제 또는 수정 form 시작------------------------- -->
<form id="boardF" method="POST">
	<input type="hidden" name="idx" id="idx" value="${board.idx}"/>
	<div class="row" style="display:none" id="divPwd">
		<div class="col-md-3 offset-md-1">
			<label for="pwd" class="text-danger">글 비밀번호</label>
		</div>
		<div class="col-md-4">
		<input type="password" name="pwd" id="pwd" placeholder="password" class="form-control">
		</div>
		<div class="col-md-2">
		<button id="btn" class="btn btn-danger">확인</button>
		</div>
	</div>
</form>
<!-- 파일다운로드를 위한 폼 -->
<form id="ff" action="../FileDown">
	<input type="hidden" name="fname" value="${board.filename}">
	<input type="hidden" name="origin_fname" value="${board.originFilename}">
</form>
<script type="text/javascript">
	var goDel = function(idx){
		var yn=confirm(idx+"번 글을 정말 삭제할까요?");
		if(yn){
			$('#divPwd').show(500);
			$('#pwd').focus();
			$('#btn').text('글삭제');
			$('#boardF').prop('method','POST');
			$('#boardF').prop('action','delete.do');
		}
	}
	var down = function(){
		$('#ff').submit();
	}
	var goEdit = function(idx){
		$('#divPwd').show(500);
		$('#pwd').focus();
		$('#btn').text('글수정');
		$('#boardF').prop('method','POST');
		$('#boardF').prop('action','edit.do');
	};
</script>

</div>
<jsp:include page="/foot.jsp"/>










