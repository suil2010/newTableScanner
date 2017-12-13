<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container" style="width: 800px;">
	<h2>게시글 상세보기</h2>
	
		<div>
			게시글 번호 :  ${requestScope.board.boardNum}
		</div> 
		<div>
			작성자 : ${requestScope.board.memberId}
		</div> 
		<div>
			제목 : ${requestScope.board.boardSubject}
		</div>
		<div>
			내용 : ${requestScope.board.boardText}
		</div>
		<div>
			작성 시간 : <fmt:formatDate value="${requestScope.board.boardTime}" pattern="yyyy-MM-dd"/> 
		</div>

	<c:set var="Id">
  		<sec:authentication property="principal.memberId" />
	</c:set>
    <c:if test='${requestScope.board.memberId == Id}'>
        <button type="button" id="btnUpdete">수정</button>
        <button type="button" id="btnDelete">삭제</button>
    </c:if>		
		
		<sec:csrfInput />
	
</div>