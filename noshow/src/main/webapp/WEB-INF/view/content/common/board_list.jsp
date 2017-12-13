<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
thead>tr>th {
	text-align: center;
}
</style>
<div class="container" style="max-width: 1000px; padding-top: 40px;">

	<table class="table" style="width: 100%;">
		<thead>
			<tr>
				<th style="width: 10%;">글번호</th>
				<th style="width: 45%;">제목</th>
				<th style="width: 20%;">작성자</th>
				<th style="width: 15%;">작성일</th>
				<th style="width: 10%;">조회수</th>
			</tr>
			<c:forEach var="row" items="${requestScope.list}">
				<tr>
					<td>${row.boardNum }</td>
					<td><a href="${initParam.rootPath }/readBoardByNum.do?boardNum=${row.boardNum}"> ${row.boardSubject }</a></td>
					<td>${row.memberId }</td>
					<td><fmt:formatDate value="${row.boardTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${row.boardViews }</td>
				</tr>
			</c:forEach>
	</table>
	<sec:authorize access="isAuthenticated()">
		<a href="${initParam.rootPath}/board_write.do">
			<button type="button" id="btnWrite" class="btn btn-default">글쓰기</button>
		</a>
	</sec:authorize>	

	<sec:csrfInput />
</div>
