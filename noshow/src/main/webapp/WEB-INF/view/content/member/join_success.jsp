<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div>
<h2>가입된 정보</h2>
사용자 ID : ${requestScope.member.memberId } <br>
사용자 이름 : ${requestScope.member.memberName }	<br>
생년월일 : ${requestScope.member.memberBirthday }	<br> 
성별 : ${requestScope.member.memberGender }	<br>
전화번호 : ${requestScope.member.memberTel }	<br>
이메일 : ${requestScope.member.memberEmail } <br>


</div>