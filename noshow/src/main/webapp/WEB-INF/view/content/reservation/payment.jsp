<%@ page contentType="text/html;charset=utf-8"%>
<script type="text/javascript">

	$(document).ready(function() {
 
		var IMP = window.IMP; // 생략가능
		IMP.init('imp00556650'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

		IMP.request_pay({
			pg : 'inicis', // version 1.1.0부터 지원.
			pay_method : 'vbank',
			merchant_uid : 'merchant_' + new Date().getTime(),
			name : '주문명:결제테스트',
			amount : ${requestScope.reservation.resPrice},   
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
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
			}
			alert(msg);
		});
	});
</script>
