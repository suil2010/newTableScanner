<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div class="container" style="max-width: 800px; padding-top: 40px;">
	<form class="form-horizontal" action="${initParam.rootPath}/login.do" method="post">
		<sec:csrfInput />
		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberId">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="ID" name="memberId" id="memberId" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberPassword">Password</label>
			<div class="col-sm-10">
				<input type="password" class="form-control" placeholder="Password" name="memberPassword" id="memberPassword" required>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 col-xs-0 control-label"></label> 
			<label class="col-sm-2 col-xs-12 control-label"><a href="${initParam.rootPath }/find_id_form.do">아이디 찾기</a></label>
			<label class="col-sm-2 col-xs-12 control-label"><a href="${initParam.rootPath }/find_password_form.do">비밀번호찾기</a></label>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-default">로그인</button>
			</div>
		</div>
	</form>
</div>