<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btnWrite").click(function(){
			location.href="${initParam.rootPath}/board_write.do";
		});
	});
</script>
</head>
<body>
	<h1>게시판</h1>
	<h2>게시글 목록</h2>
	<button type="button" id="btnWrite">글쓰기</button>
	<table border="1" width="600px">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach var="row" items="${list }">
				<tr>
					<td>${row.boardNum }</td>
					<td><a href="${initParam.rootPath }/common/board_view.do?board=${row.boardTitle }">${row.boardTitle }</a></td>
					<td>${row.boardWriter }</td>
					<td><fmt:formatDate value="${row.boardTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${row.boardViews }</td>
				</tr>
			</c:forEach>
	</table>
	
</body>
</html>