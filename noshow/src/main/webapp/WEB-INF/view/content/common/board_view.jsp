<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>작성완료</title>
<script type="text/javascript">
	function move(url) {
		location.href=url;
	}
	function boardViewCheck() {
		var form = document.BoardViewForm;
		return true;
	}
</script>

<style>

</style>
</head>
<h2>게시글 작성완료</h2>
<body>

<form>
	<tr>
		<td align=center colspan=2> 
		<hr size=1>
		<div align="center">
		[ <input type="button" value="목록" onclick="move('board_list.jsp');"> | 
		<input type="button" value="수정" onclick="move('board_update.jsp');"> |
		<input type="button" value="답변" onclick="move('board_reply.jsp');"> |
		<input type="button" value="삭제" onclick="move('board_delete.jsp');">]<br>
		</div>
		</td>
	</tr>
</form>

</body>
</html>