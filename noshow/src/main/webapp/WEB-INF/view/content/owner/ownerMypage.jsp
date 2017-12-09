<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:csrfInput/>

		<li><a href="${initParam.rootPath}/mypage/member_info.do">사용자 정보 조회</a></li>
		<li><a href="${initParam.rootPath}/mypage/update_member_form.do">사용자 정보 수정</a></li>
		<li><a href="${initParam.rootPath}/find_rt_byid.do">음식점 정보 조회</a></li>
		<li><a hrfe="${initParam.rootPath}/owner_update_form.do">음식점 정보 수정</a></li>

		


