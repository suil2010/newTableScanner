<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<script type="text/javascript" src="${initParam.rootPath }/resource/jquery/jquery-3.2.1.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	$("#duplicatedCheckBtn").on("click",function(){
		
		var memberId = $("#memberId").val();
		
		$.ajax({
			"url":"${initParam.rootPath }/idDuplicatedCheck.do",
			"type":"get",
			"data":{
				"memberId":$('#memberId').val(),
				"${_csrf.parameterName}":"${_csrf.token}"		
			},
			"dataType":"text",
			"success":function(message){
				if(message=='0'){
					var check = confirm("아이디를 사용하시겠습니까?");
						if(check == true){
							$("#memberId").addClass("disable");
							$("#idCheck").val("true");
							document.getElementById("memberId").readOnly=true;
						}else{
							document.getElementById("memberId").value='';
							$("#idCheck").val("false");
						}
				} else if(message=='1'){
					alert('이미 사용중인 id 입니다.');
					document.getElementById("memberId").value='';
					$("#idCheck").val("false");
				} else if(message=='2'){
					alert("아이디를 입력해주세요.");
					$("#idCheck").val("false");
				}
			},
			"error":function(a,b,c){
				alert(a);
				alert(b);
				alert(c);
			}
		});
	});
	
	$("#joinBtn").on("click",function(){
		if($("#idCheck").val()=='false'){
			alert("ID 중복체크를 해주세요");
		} else {
			$(this).parent().submit();
		}
	});
});

function idCheck(){
	var str = $("#memberId").val();
	
	var check1 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var check2 = /\s/;
	var check3 = /[~!@#$%^&*()_+|<>?:{}]/;
	
	if(check1.test(str)){
		alert("한글은 사용하실 수 없습니다. 다시 설정해주세요.");
		$("#memberId").val("");
	}
	if(check2.test(str)){
		alert("공백은 사용하실 수 없습니다. 다시 설정해주세요.");
		$("#memberId").val('');
	}
	if(check3.test(str)){
		alert("특수문자는 사용하실 수 없습니다. 다시 설정해주세요.");
		$("#memberId").val('');
	}
}
function PasswordCheck(){
	var str = $("#memberPassword").val();
	
	var check1 = /\s/;
	var check2 = /[()_+|<>?:{}]/;
	
	if(check1.test(str)){
		alert("비밀번호는 공백을 사용하실 수 없습니다.");
		$("#memberPassword").val("");
	}
	if(check2.test(str)){
		alert("비밀번호는 ~,!,@,#,$,%,^,&,* 만을 사용하실 수 있습니다.");
		$("#memberPassword").val('');
	}

}
function nameCheck(){
	var str = $("#memberName").val();
	
	var check1 = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
	var check2 = /[a-z]/;
	var check3 = /[\s]/;
	
	if(!check1.test(str) && !check2.test(str)){
		alert("이름은 한글과 영문으로만 입력해주세요.");
		$("#memberName").val("");
	} else if(check3.test(str)){
		alert("이름에 공백이 있습니다.");
		$("#memberName").val("");
	}
	
}
function telCheck(){
	var str = $("#memberTel").val();
	var check = /^[0-9]*$/;
	
	if(!check.test(str)){
		alert("번호는 숫자로만 입력해주세요.");
		$("#memberTel").val("");
	}
}
function checkemail(){
	var str = $("#memberEmail").val();
	var regExp = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if(str.lenght == 0){
		alert("이메일을 입력하세요.");
		$("#memberEmail").val("");
	}
	if (!str.match(regExp))
	{
		alert("Email 형식이 아닙니다.");
		$("#memberEmail").val("");
	}
}

</script>
<input type="hidden" value="false" id="idCheck">
<div class="container" style="max-width: 800px;">
	<h1>회원가입</h1>
	<form class="form-horizontal" method="post" action="${initParam.rootPath}/join_member.do">
		<sec:csrfInput />
		<div class="form-group">
			<label for="memberId">ID</label>
			<input type="text" class="form-control" placeholder="ID" name="memberId" onblur=idCheck(); id="memberId" required="required">
			<br>
			<button type="button" id="duplicatedCheckBtn" class="btn-default">id 중복체크</button>
		</div>

		<div class="form-group">
			<label for="memberPassword">Password</label>
			<input type="text" class="form-control" placeholder="Password" name="memberPassword" onblur=PasswordCheck(); id="memberPassword">
		</div>

		<div class="form-group">
			<label for="memberName">Name</label>
			<input type="text" class="form-control" placeholder="Name" name="memberName" onblur=nameCheck(); id="memberName">
		</div>

		<div class="form-group">
			<label for="memberBirthday">Birthday</label>
			<input type="date" class="form-control" placeholder="Birthday" name="memberBirthday" id="memberBirthday">
		</div>

		<div class="form-group">
			<label for="memberTel">Tel</label>
			<input type="text" class="form-control" placeholder="Tel" name="memberTel" onblur=telCheck(); id="memberTel">
		</div>

		<div class="form-group">
			<label for="memberEmail">Email</label>
			<input type="text" class="form-control" placeholder="Email" name="memberEmail" onblur=checkemail(); id="memberEmail">
		</div>

		<div class="form-group">
			<label>Gender</label> 
			<label> 
				<input type="radio" name="memberGender" value="male">남자
			</label> 
			<label>
				<input type="radio" name="memberGender" value="female">여자
			</label>
		</div>

		<div class="form-group">
			<button id="joinBtn" type="button" class="btn btn-default">회원가입</button>
		</div>
	</form>

</div>