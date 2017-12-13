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
			작성 시간 : <fmt:formatDate value="${requestScope.board.boardTime}" pattern="yyyy-MM-dd hh:mm"/> 
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
    	    <input type="hidden" value="${requestScope.board.boardNum}" name="boardNum" > 
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