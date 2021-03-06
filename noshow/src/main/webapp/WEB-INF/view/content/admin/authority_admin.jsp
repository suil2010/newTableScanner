<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<h2>관리자 회원</h2>

	<div class="col-sm-12" style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;">

		<table class="table table-hover">
			<thead>
				<tr>
					<th>사용자 ID</th>
					<th>사용자 이름</th>
					<th>생년월일</th>
					<th>성별</th>
					<th>전화번호</th>
					<th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.member}" var="item">
					<tr>
						<td>${item.memberId }</td>
						<td>${item.memberName }</td>
						<td><fmt:formatDate value="${item.memberBirthday }" pattern="yyyy-MM-dd" /></td>
						<td>${item.memberGender }</td>
						<td>${item.memberTel }</td>
						<td>${item.memberEmail }</td>

					</tr>
				</c:forEach>
			</tbody>
			<sec:csrfInput />
		</table>
	</div>
</div>