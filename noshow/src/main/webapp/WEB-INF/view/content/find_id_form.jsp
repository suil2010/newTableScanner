<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<form method="post" action="${initParam.rootPath}/find_id.do">
	<sec:csrfInput />
	<div class="form-group">
		<label class="col-sm-2 control-label" for="memberName">ID</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" placeholder="Name"
				name="memberName" id="memberName">
		</div>
	</div>

	<div class="form-group">
		<label class="col-sm-2 control-label" for="memberEmail">Email</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" placeholder="Email"
				name="memberEmail" id="memberEmail">
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-default">ID찾기</button>
		</div>
	</div>
</form>