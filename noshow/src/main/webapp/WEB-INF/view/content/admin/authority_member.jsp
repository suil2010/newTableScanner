<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>가입된 정보</h2>
<c:forEach items="${requestScope.member}" var="item">
<div>
사용자 ID : ${item.memberId } <br>
사용자 이름 : ${item.memberName }   <br>
생년월일 : <fmt:formatDate value="${item.memberBirthday }" pattern="yyyy-MM-dd"/> <br> 
성별 : ${item.memberGender }   <br>
전화번호 : ${item.memberTel }   <br>
이메일 : ${item.memberEmail } <br>

<form action="${initParam.rootPath}/memberAuthority_update.do">
	<button type="submit" value="${item.memberId }" id="memberId" name="memberId">관리자 권한 주기!</button>
</form>

</div>
<hr>
</c:forEach>