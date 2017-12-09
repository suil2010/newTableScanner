<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<script>
$(document).ready(function() {

	$("#submit").click(function() {
		if(confirm("등록하시겠습니까?")){
			document.form.submit();
		}
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		if (title == "") {
			alert("제목을 입력하세요");
			document.form.title.focus();
			return;
		}
		if (title == "") {
			alert("제목을 입력하세요");
			document.form.content.focus();
			return;
		}
		if (content == "") {
			alert("내용을 입력하세요");
			document.form.content.focus();
			return;
		}
		if (writer == "") {
			alert("이름을 입력하세요");
			document.form.writer.focus();
			return;
		}
		// 입력한 데이터를 서버로 전송
		document.form.submit();
	});
});
</script>
</head>
<body>
	<h2>게시글 작성하기</h2>
	<form name="form" method="post" action="${initParam.rootPath }/insertBoard.do">
	<sec:csrfInput />
		<div>
			작성일자 :
			<fmt:formatDate value="${board.boardTime}" pattern="yyyy-MM-dd a HH:mm:ss" />
		</div>
		<div>조회수 : ${board.boardViews}</div>
		<div>
			작성자 : <input name="writer" id="writer" value="${board.boardWriter}" placeholder="이름을 입력해주세요">
		</div>
		<div>
			제목 <input name="title" id="title" size="80" value="${board.boardSubject}" placeholder="제목을 입력해주세요">
		</div>
		<div>
			내용
			<textarea name="content" id="content" rows="4" cols="80" placeholder="내용을 입력해주세요">${board.boardText}</textarea>
		</div>
		<div style="width: 650px; text-align: center;">
			<!-- 게시물번호를 hidden으로 처리 -->
			<input type="hidden" name="boardNum" value="${board.boardNum}">
			<button type="submit" id="submit">등록</button>
		</div>
	</form>
</body>
</html>

