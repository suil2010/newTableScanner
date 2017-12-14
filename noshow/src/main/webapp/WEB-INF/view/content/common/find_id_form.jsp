<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script>
	$(document).ready(function() {

		$("#checkBtn").on("click", function() {
			$.ajax({
				"url" : "${initParam.rootPath }/find_id.do",
				"type" : "get",
				"dataType" : "text",
				"data" : {
					"memberName" : $("#memberName").val(),
					"memberEmail" : $("#memberEmail").val()
				},
				"beforeSend" : function() {
					if (!$("#memberName").val()) {
						alert("Name를 입력하시오.");
						$("#memberName").focus();
						return false;
					}
					if (!$("#memberEmail").val()) { 
						alert("Email을 입력하시오.");
						$("#memberEmail").focus();
						return false;
					}
				},
				"success" : function(obj) {
					if (obj == "Email, Name을 다시 확인하세요.") {
						alert(obj);
					} else {
						text = "<div class='id col-sm-12'>ID는 " + obj + " 입니다.</div>";
						text += '<div class="link col-sm-12"><a href="${initParam.rootPath }/find_password_form.do">비밀번호찾기</a></div>';
						//$("#view").html(text); 
						$(".row2").append(text);

						$(".row1").css({"display":"none"});
						$(".row2").css({"display":"block"});
					}
				}
			});
		});
	});
</script>
<style>
.id{
	font-size: 32px;
	text-align: center;
}
.link{   
	text-align: center;
	font-size: 18px;
	margin-top: 100px; 
}
</style>

<div class="container" id="view" style="max-width: 800px; padding-top: 40px;">

	<div class="row row1">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberName" style="height: 34px; margin: 0; text-align: center; line-height: 34px;">Name</label>
			<div class="col-sm-10">
				<input type="text" name="memberName" id="memberName" placeholder="이름을 입력해주세요." class="form-control" autofocus required >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label" for="memberEmail" style="height: 34px; text-align: center; line-height: 34px;">Email</label>
			<div class="col-sm-10">
				<input type="text" name="memberEmail" id="memberEmail" class="form-control" placeholder="이메일을 입력해주세요." required >
			</div>
		</div>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10" style="margin-top: 20px;">
				<button id="checkBtn" name="checkBtn" class="btn btn-default">아이디 찾기</button>
			</div>
		</div> 
	</div>

	<div class="row row2" style="border: 1px solid #000; min-height: 300px; max-width: 500px; margin: 0 auto; display: none; padding: 30px;">
		
	
	</div>

</div>