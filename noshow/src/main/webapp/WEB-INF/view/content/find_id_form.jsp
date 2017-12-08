<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<script>
$(document).ready(function(){
	
	$("#checkBtn").on("click",function(){
		$.ajax({
			"url":"${initParam.rootPath }/find_id.do",
			"type":"get",
			"dataType":"text",
			"data":{
				"memberName" : $("#memberName").val(),
				"memberEmail" : $("#memberEmail").val()
			},
			"success":function(obj){
				if(obj == "Email, Name을 다시 확인하세요."){
					alert(obj);	
				}else{
					text = "ID는 " + obj + " 입니다.";
					$("#view").html(text);
				}
			},
			"error":function(a){
				alert("오류발생");
			}
		});		
	});
});

</script>



<div class="container" id="view">
	<span class="text-center">가입할 때 등록한 이름을 입력해주세요.</span>
	<input type="text" name="memberName" id="memberName">
	<span class="text-center">가입할 때 등록한 Email을 입력해주세요.</span>
	<input type="text" name="memberEmail" id="memberEmail">
	<button id="checkBtn" name="checkBtn">확인</button>
</div>