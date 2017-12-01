<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container" style="max-width: 800px;">
	<form class="form-horizontal" method="post" action="${initParam.rootPath}/join_member.do">
		<sec:csrfInput />
		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberId">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="ID" name="memberId" id="memberId">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberPassword">Password</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Password" name="memberPassword" id="memberPassword">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberName">Name</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Name" name="memberName" id="memberName">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberBirthday">Birthday</label>
			<div class="col-sm-10">
				<input type="date" class="form-control" placeholder="Birthday" name="memberBirthday" id="memberBirthday">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberTel">Tel</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Tel" name="memberTel" id="memberTel">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberEmail">Email</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Email" name="memberEmail" id="memberEmail">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">Gender</label> 
			<label class="radio-inline"> 
				<input type="radio" name="memberGender" value="male">남자
			</label> 
			<label class="radio-inline"> 
				<input type="radio" name="memberGender" value="female">여자
			</label>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">회원가입</button>
			</div>
		</div>
	</form>

</div>