<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.mainTemplate {
	background: #FAFAFA;
}

label, p {
	text-align: center;
	height: 50px;
	line-height: 50px;
}
</style>
<sec:csrfInput />

<!-- 일반회원 -->
<sec:authorize access="hasRole('ROLE_MEMBER')">

	<div class="col-md-10" style="background: #fff; padding-bottom: 50px;">


		<div class="row">
			<div class="col-sm-12">
				<h1>본인정보 조회결과</h1>
			</div>
		</div>
		<div class="row">
			<label class="col-sm-3">사용자 ID</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberId" />
			</p>
		</div>
		<div class="row">
			<label class="col-sm-3">이름</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberName" />
			</p>
		</div>
		<div class="row">
			<label class="col-sm-3">이메일주소</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberEmail" />
			</p>
		</div>
		<div class="row">
			<label class="col-sm-3">생년월일</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberBirthday" />
			</p>
		</div>
		<div class="row">
			<label class="col-sm-3">성별</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberGender" />
			</p>
		</div>
		<div class="row">
			<label class="col-sm-3">전화번호</label>
			<p class="col-sm-9">
				<sec:authentication property="principal.memberTel" />
			</p>
		</div>

		<form action="${initParam.rootPath }/remove_Member.do" method="post">
			<button type="submit" class="btn btn-default" style="float: right; margin-top: 30px;">회원탈퇴</button>
			<sec:csrfInput />
		</form>
	</div>
	</div>
</sec:authorize>

<!-- 사업자회원 -->
<sec:authorize access="hasRole('ROLE_OWNER')">
	<div class="container" style="max-width: 800px; margin-top: 20px;">
		<div class="col-md-12" style="background: #fff; padding-bottom: 50px; border: 1px solid #fff; border-radius: 10px;">
			<div class="row">
				<div class="col-sm-12"> 
					<h1>본인정보 조회결과</h1>
				</div>
			</div>
			<div class="row">
				<label class="col-sm-3">사용자 ID</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberId" />
				</p>
			</div>
			<div class="row">
				<label class="col-sm-3">이름</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberName" />
				</p>
			</div>
			<div class="row">
				<label class="col-sm-3">이메일주소</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberEmail" />
				</p>
			</div>
			<div class="row">
				<label class="col-sm-3">생년월일</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberBirthday" />
				</p>
			</div>
			<div class="row">
				<label class="col-sm-3">성별</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberGender" />
				</p>
			</div>
			<div class="row">
				<label class="col-sm-3">전화번호</label>
				<p class="col-sm-9">
					<sec:authentication property="principal.memberTel" />
				</p>
			</div>

			<form action="${initParam.rootPath }/remove_Member.do" method="post">
				<button type="submit" class="btn btn-default" style="float: right; margin-top: 30px;">회원탈퇴</button>
				<sec:csrfInput />
			</form>
		</div>
	</div>
</sec:authorize>
