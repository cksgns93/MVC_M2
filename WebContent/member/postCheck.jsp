<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,user.domain.ZipVO"%>
<% request.setCharacterEncoding("UTF-8");%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<style>
	.scrolltable {
    	display: block;
    	overflow-y:scroll ;
    	height : 300px;
	}
	</style>
<div class="container">
	<%
		//요청방식을 알아내보자
	String method = request.getMethod();
	out.println("method()" + method + "<br>");
	if (method.equalsIgnoreCase("GET")) {
		//get방식의 요청일 경우 => 아이디 입력 폼을 보여준다.
	%>
	<div class="row p-3">
		<div class="col-md-10 offset-md-1">
			<form name="idf" action="postCheck.jsp" method="POST">
				<label>주소:</label> <input type="text" name="post"
					class="form-control m-1" placeholder="post"
					autofocus="autofocus">
				<button class="btn btn-outline-primary m-1">확인</button>
			</form>
		</div>
	</div>
	<%
		} else {
		//post방식의 요청일 경우 => 아이디 사용 가능 여부를 알려준다.
		String doro = request.getParameter("post");
		//out.println(userid);
	%>
	<jsp:useBean id="zipDao" class="user.persistence.ZipDAO"
		scope="session" />
	<%
	List<ZipVO> rs = zipDao.selectAll(doro);
	out.println("<div class='scrolltable container'>");
		out.println("<table class='table table-bordered table-hover'style='border:1px solid'>");
		out.println("<tr><th>우편번호</th><th>상세주소</th></tr>");
		if (rs.size()!=0) {
			for(int i=0;i<rs.size();i++){
				out.println("<tr>");
				out.println("<td>");
				out.println(rs.get(i).getPost_code());
				out.println("</td>");
				out.println("<td>");
				out.println(rs.get(i).getSido_kor()+" "+rs.get(i).getDoro_kor());
				if(rs.get(i).getSigungu_bld_name()!=null){
					out.println(" "+rs.get(i).getSigungu_bld_name());
				}
				out.println("</td>");
				out.println("</tr>");	
			}
		}
		out.println("</table>");
	}
	out.println("</div>");
	%>	
<script>
	$('tr').click(function(){
		//alert($(this).children("td:first").text())
		opener.document.f.post.value = $(this).children("td:first").text();
		opener.document.f.addr1.value = $(this).children("td:eq(1)").text();
		self.close();
	})
</script>
