<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript">
	$(function() {
		$(".passwordsubmit").on("click", function() {
			alert("이메일로 패스워드가 전송됩니다.");
			$(".form").submit();
		});
	});
</script>
<div class="container" style="max-width: 800px; padding-top: 40px;">

	<form method="post" action="${initParam.rootPath}/find_password.do" class="form">
		<sec:csrfInput />
		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberId" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">ID</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="ID" name="memberId" id="memberId">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberEmail" style="height: 34px; text-align: center; line-height: 34px;">Email</label>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="Email" name="memberEmail" id="memberEmail">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="margin-top: 20px;">
				<button type="button" class="btn btn-default passwordsubmit">비밀번호찾기</button>
			</div>
		</div> 
	</form>
</div>
