<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:csrfInput/>

		<li><a href="${initParam.rootPath }/mypage/member_info.do">사용자 정보 조회</a></li>
		<li><a href="${initParam.rootPath }/mypage/update_member_form.do">사용자 정보 수정</a></li>
		<li><a href="${initParam.rootPath }/myReservation.do">사용자 예약 내역</a></li>
		<li><a href="${initParam.rootPath }/member/regist_rt_form.do">음식점 등록</a></li>
		



