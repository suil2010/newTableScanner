<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1> 회원가입 </h1>
   <form method="post" action="${initParam.rootPath}/join_member.do">
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