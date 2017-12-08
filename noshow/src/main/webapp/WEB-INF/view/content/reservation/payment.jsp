<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	$(document).ready(function() {
 
		var IMP = window.IMP; // 생략가능
		IMP.init('imp00556650'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

		IMP.request_pay({
			pg : 'inicis', // version 1.1.0부터 지원.
			pay_method : 'vbank',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '주문명:결제테스트',
			amount : '${requestScope.reservation.resPrice}',   
			buyer_email : '${requestScope.member.memberEmail}',
			buyer_name : '${requestScope.member.memberName}',
			buyer_tel : '${requestScope.member.memberTel}',
			m_redirect_url : 'http://127.0.0.1:8088/noshow/addReservation.do'
		}, function(rsp) {
			if (rsp.success) {
				var msg = '결제가 완료되었습니다.';
				msg += '고유ID : ' + rsp.imp_uid;
				msg += '상점 거래ID : ' + rsp.merchant_uid;
				msg += '결제 금액 : ' + rsp.paid_amount;
				msg += '카드 승인번호 : ' + rsp.apply_num;
				alert("${requestScope.reservation.tableList}");
				$(".submit").submit();
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
	});
</script>

<form action="${initParam.rootPath }/addReservation.do" method="post" class="submit">
	<input type="hidden" name="resDate" value="${requestScope.reservation.resDate}" >
	<input type="hidden" name="resPeople" value="${requestScope.reservation.resPeople}" >
	<input type="hidden" name="resStartTime" value="${requestScope.reservation.resStartTime}" >
	<input type="hidden" name="resPayStatement" value="${requestScope.reservation.resPayStatement}" >
	<input type="hidden" name="businessId" value="${requestScope.reservation.businessId}" >
	<c:forEach items="${requestScope.reservation.tableList}" var="table">
		<input type="hidden" name="tableList" value="${table}">	 
	</c:forEach>
	<sec:csrfInput />
</form>
