<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container" style="font-size: 20px;">
	<div class="row">
		<div class="col-sm-12">
			<h1>본인정보 조회결과</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-2">사용자 ID</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberId"/></div>
	</div>
	<div class="row">
		<div class="col-sm-2">이름</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberName"/></div>
	</div>
	<div class="row">
		<div class="col-sm-2">이메일주소</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberEmail"/></div>
	</div>
	<div class="row">
		<div class="col-sm-2">생년월일</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberBirthday"/></div>
	</div>
		<div class="row">
		<div class="col-sm-2">성별</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberGender"/></div>
	</div>
		<div class="row">
		<div class="col-sm-2">전화번호</div>
		<div class="col-sm-10"><sec:authentication property="principal.memberTel"/></div>
	</div>
</div>