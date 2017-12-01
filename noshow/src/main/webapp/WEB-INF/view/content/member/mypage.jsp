<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<style>
	.row {
		padding-top: 5px;
	}
</style>
<div class="container" style="max-width: 800px; font-size: 20px;">          
	<div class="row">
		<div class="col-sm-12">
			<h1>본인정보 조회결과</h1>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">사용자 ID</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberId" />
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">이름</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberName" />
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">이메일주소</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberEmail" />
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">생년월일</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberBirthday" />
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">성별</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberGender" />
		</div>
	</div>
	<div class="row">
		<div class="col-sm-3">전화번호</div>
		<div class="col-sm-9">
			<sec:authentication property="principal.memberTel" />
		</div>
	</div>
    

</div>