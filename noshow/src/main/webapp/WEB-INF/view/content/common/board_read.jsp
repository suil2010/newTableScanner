<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.title_top {
	background: #f1f1f1;
}

.title_top>div {
	font-size: 14px;
	height: 32px;
	line-height: 32px;
}
</style>
<div class="container" style="width: 800px; padding-top: 40px;">     
	<div class="row">

		<div class="col-sm-12 title_top">
			<div class="col-sm-2" style="text-align: center;">${requestScope.board.boardNum}</div>
			<div class="col-sm-2" style="text-align: center; float: right;">${requestScope.board.memberId}</div>
			<div class="col-sm-3" style="text-align: center; float: right;">
				<fmt:formatDate value="${requestScope.board.boardTime}" pattern="yyyy-MM-dd hh:mm" />
			</div>
		</div> 

		<div class="col-sm-12" style="font-size: 18px; text-align: center; height: 60px; color: brown; line-height: 60px; border-bottom: 1px solid #E0E0E0;">${requestScope.board.boardSubject}</div>
	</div>

	<div class="row" style="border-bottom: 1px solid #E0E0E0; margin-bottom: 10px;">
		<div class="col-sm-12" style="padding: 20px; min-height: 500px;">${requestScope.board.boardText}</div>
	</div>
	
	
   





	<sec:authorize access="isAuthenticated()">
		<c:set var="Id">
			<sec:authentication property="principal.memberId" />
		</c:set>

		<c:if test='${requestScope.board.memberId == Id}'>
			<form action="${initParam.rootPath }/updateBoard_form.do" method="post">
				<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
				<button type="submit" id="btnUpdete">수정</button>
				<sec:csrfInput />
			</form>
			<form action="${initParam.rootPath }/deleteBoardByNum.do" method="post">
				<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
				<button type="submit" id="btnDelete">삭제</button>
				<sec:csrfInput />
			</form>
		</c:if>

		<c:if test="${Id == 'admin'}">
			<form action="${initParam.rootPath }/deleteBoardByNum.do" method="post">
				<input type="hidden" value="${requestScope.board.boardNum}" name="boardNum">
				<button type="submit" id="btnDelete">삭제</button>
				<sec:csrfInput />
			</form>
		</c:if>
	</sec:authorize>
	<sec:csrfInput />

</div>