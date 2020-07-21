<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/top.jsp"/>
<div class="text-left p-5">
	<h1 class="text-center">MVC Board</h1>
	<p class="text-center">
		<a href="write.do">글쓰기</a>|
		<a href="list.do">글목록</a>
	</p>
	<!-- 파일업로드, 메소드는 post, enctype은 multipart/form-data를 주어야 데이터가 서버에 함께 전송-->
	<form name="bf" id="bf" action="writeEnd.do" method="post" enctype="multipart/form-data">
		<table class="table table-bordered">
			<tr>
				<th style="width:20%">제목</th>
				<td style="width:80%">
					<input type="text" name="subject" id="subject" placeholder="Subject" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글쓴이</th>
				<td style="width:80%">
					<input type="text" name="name" id="name" placeholder="name" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">글내용</th>
				<td style="width:80%">
					<textarea rows="10" cols="50" name="content" id="content" placeholder="Content" class="form-control"></textarea>
				</td>
			</tr>
			<tr>
				<th style="width:20%">비밀번호</th>
				<td style="width:80%">
					<input type="password" name="pwd" id="pwd" placeholder="Password" class="form-control">
				</td>
			</tr>
			<tr>
				<th style="width:20%">첨부파일</th>
				<td style="width:80%">
					<input type="file" name="filename" id="filename" placeholder="Attatch File" class="form-control">
				</td>
			</tr>
			<tr>
				<td colspan="2" class="text-center">
					<button class="btn btn-success" id="btnWrite">글쓰기</button>
					<button type="reset" class="btn btn-warning" id="btnReset">다시쓰기</button>
				</td>
			</tr>	
		</table>
	</form>
</div>
<jsp:include page="/foot.jsp"/>

<script>
	$(function(){
		$("#bf").on('submit',function(e){
			//e.preventDefault();
			if(!$('#subject').val()){
				alert('글제목을 입력하세요');
				$('#subject').focus();
				return false;
			}
			if(!$('#name').val()){
				alert('작성자 이름을 입력하세요');
				$('#name').focus();
				return false;
			}
			if(!$('#pwd').val()){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				return false;
			}
		})
	})
</script>


