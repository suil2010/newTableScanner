<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
		.join_form{
			width: 400px;  
		}
		.join_form > input{
			width: 100%;
			height: 40px;
			margin-top: 7px;
		}
		.join_form_submit{
			width: 100%;
			height: 45px;
			background: #39BAE8;
			border: 0;
			outline: 0;
			color: #fff;
			font-weight: bold;
			font-size: 18px;
			margin-top: 15px;
			cursor: pointer;

		}
		.memberGender{
		width: 100%;
		height: 40px;
		float: left;   
		} 
		.memberGender >label , .memberGender >input{  
			float: left; 
		}


		@media screen and (max-width: 768px){ /*화면이 웹 스크린이고 width가 750px 이하가 되면 아래의 css 설정이 적용됨.*/
			.content{
				width: 100%;
			} 
		}
	</style>     
<h1 style="margin-top: 20px;"> 회원가입 </h1>   
   <form method="post" action="${initParam.rootPath}/join_member.do" class="join_form">
      <input type="text" name="memberId" placeholder="ID를 입력해주세요."> 
      <input type="password" name="memberPassword" placeholder="Password를 입력해주세요.">
      <input type="text" name="memberName" placeholder="name를 입력해주세요.">
      <input type="date" name="memberBirthday" placeholder="생년월일를 입력해주세요.">
      <input type="text" name="memberTel" placeholder="전화번호를 입력해주세요."> 
      <input type="text" name="memberEmail" placeholder="Email을 입력해주세요."> 
      <div class="memberGender">
         <label>남성</label> <input type="radio" name="memberGender" class="radio" value="male"> 
         <label>여성</label> <input type="radio" name="memberGender" class="radio" value="female">
      </div>
      <input type="submit" class="join_form_submit" value="회원가입">
      <sec:csrfInput/>
   </form>