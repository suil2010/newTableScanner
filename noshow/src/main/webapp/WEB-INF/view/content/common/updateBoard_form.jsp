<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script>
	$(document).ready(function() {

		$("#submit").click(function() {
			if (confirm("수정하시겠습니까?")) {
				document.form.submit();
			}
			var title = $("#boardSubject").val();
			var content = $("#boardText").val();
			var writer = $("#writer").val();

			if (title == "") {
				alert("제목을 입력하세요");
				document.form.title.focus();
				return;
			}
			if (content == "") {
				alert("내용을 입력하세요");
				document.form.content.focus();
				return;
			}

			// 입력한 데이터를 서버로 전송
			document.form.submit();
		});
	});
</script>
<div class="container" style="width: 800px;">
	<h2>게시글 수정하기</h2>
	
	<form name="form" method="post" action="${initParam.rootPath }/updateBoard.do">

		<div>
			게시글번호 : <input type="text" name="boardNum" id="boardNum" value="${requestScope.board.boardNum}" class="form-control" readonly="readonly">
		</div> 
		<div>
			작성자 : <input type="text" name="memberId" id="memberId" value="<sec:authentication property='principal.memberId' />" class="form-control" readonly="readonly">
		</div> 
		<div>
			제목 : <input type="text" name="boardSubject" id="boardSubject" size="80" class="form-control" value="${requestScope.board.boardSubject}">
		</div>
		<div>
			내용 : 
			<textarea name="boardText" id="boardText" rows="16" cols="80" class="form-control" style="resize: none;">${requestScope.board.boardText}</textarea>
		</div>
		
		<button type="submit" id="submit" class="btn btn-default" style="float: right;">수정</button>
		<sec:csrfInput />
	</form>
	
</div>


