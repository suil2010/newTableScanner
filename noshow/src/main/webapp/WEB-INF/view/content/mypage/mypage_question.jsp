<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<style>
.mainTemplate {
	background: #FAFAFA;
}
</style>
<div class="col-sm-10"
	style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;">
	<c:choose>
		<c:when test="${empty requestScope.questionList  }">
			<div class="col-md-6" style="margin-top: 10px;">해당 음식점에 등록된 문의가 없습니다.</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.questionList}" var="question">
				<div class="col-md-12 box1" style="background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;">
					<div style="float: right;" class="col-md-9">
						<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 문의글번호 : ${question.questionNum} | 작성자 : ${question.memberId}</span> 
						<span class="label col-sm-9" style="color: #000; font-size: 18px;"> 문의글 : ${question.questionText}</span> 
						<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 등록 일시 : ${question.questionTime}</span>
					</div>
					<sec:authentication property='principal.memberId' var="currentMemberId"/>
					<c:if test="${question.memberId == currentMemberId }">
						<button type="button" class="btn btn-basic btn-xs">수정</button>
						<button type="button" class="btn btn-basic btn-xs">삭제</button>
					</c:if>
					<c:if test="${currentMemberId == question.businessId }">
						<button type="button" class="btn btn-basic btn-xs">답변 등록</button>										
					</c:if>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>	
	<sec:csrfInput />
</div>
