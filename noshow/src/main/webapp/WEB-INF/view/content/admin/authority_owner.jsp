<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h2>가입된 정보</h2>
<c:forEach items="${requestScope.member}" var="item">
<div>
사용자 ID : ${item.memberId } <br>
사용자 이름 : ${item.memberName }	<br>
생년월일 : ${item.memberBirthday }	<br> 
성별 : ${item.memberGender }	<br>
전화번호 : ${item.memberTel }	<br>
이메일 : ${item.memberEmail } <br>
</div>
<hr>
</c:forEach>