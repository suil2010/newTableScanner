<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>회원 정보 수정</h2>
<c:if test ="{not empty requestScope.errorMessage}">
	<div>
		${requestScope.errorMessage }
	</div>
</c:if>
  <form method="post" action="${initParam.rootPath}/update_Member.do">
  	<div>
  		<label for="id"> 사용자 ID </label>
      <input type="text" name="memberId" value='<sec:authentication property="principal.memberId"/>' readonly="readonly"> 
    </div>
    <div>
  		<label for="oldPassword"> 기존 비밀번호 </label>
  		<input type="password" name="oldMemberPassword" required="required">
    </div>
    <div>
  		<label for="newPassword"> 새로운 비밀번호 </label>
  		<input type="password" name="memberPassword" required="required">
    </div> 
    <div>
  		<label for="name"> 이름 </label>
  		<input type="text" name="memberName" value='<sec:authentication property="principal.memberName"/>' readonly="readonly">
    </div> 
    
      <input type="date" name="memberBirthday" placeholder="생년월일를 입력해주세요.">
      <input type="text" name="memberTel" placeholder="전화번호를 입력해주세요."> 
      <input type="text" name="memberEmail" placeholder="Email을 입력해주세요."> 
      <div class="memberGender">
         <label>남성</label> <input type="radio" name="memberGender" class="radio" value="male"> 
         <label>여성</label> <input type="radio" name="memberGender" class="radio" value="female">
      </div>
      <input type="submit" class="join_form_submit" value="정보수정">
      <sec:csrfInput/>
   </form>