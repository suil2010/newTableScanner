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
<script>

	function changeQuestion() {
		$(".updateQuestionFormDiv").hide();
		$(".questionViewDiv").show();
		if($(this).text()=='수정')
			$(this).parent().hide().next().next().next().show();
	}
	
	function changeAnswer() {
		$(".answerViewFormDiv").hide();
		$(".answerBtnClassiDiv").show();
		if ($(this).text()=='답변보기') {
			$(this).parent().hide().next().show();
		} 
		
	}

	$(document).ready(function() {
		$("#questionListDiv").on("click", ".changeQBtn", changeQuestion);/* 문의글 수정 버튼 */
		$("#questionListDiv").on("click", ".cancelQBtn", changeQuestion);/* 수정 취소 버튼 */
		$("#questionListDiv").on("click", ".answerViewBtn", changeAnswer);/* 답글보기 버튼 */
		$("#questionListDiv").on("click", ".cancelAnswerBtn", changeAnswer);/* 답글접기 버튼 */
	});
</script>

<div class="col-sm-10" id="questionListDiv">
	<sec:authentication property='principal.memberId' var="currentMemberId"/>
	<c:choose>
		<c:when test="${empty requestScope.questionList  }">
			<div class="col-md-6" style="margin-top: 10px;">현재 회원님께서 작성한 문의가 없습니다.</div>
		</c:when>
		<c:otherwise>								
		<div>
			<c:forEach items="${requestScope.questionList}" var="question" varStatus="cnt">
				<div class="col-md-12 box1" style="background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;">
					
					<div style="display:block" class="col-md-9 questionViewDiv">
						<span class="label col-md-9" style="color:#000;font-size: 12px;"> 문의글번호 : ${question.questionNum} | 작성자 : ${question.memberId}</span> 
						<span class="label col-md-9" style="color:#000;font-size: 14px;"> 문의글 : ${question.questionText}</span> 
						<span class="label col-md-9" style="color:#000;font-size: 12px;"> 등록 일시 : ${question.questionTime}</span>
						<button type="button" class="btn btn-basic btn-xs changeQBtn">수정</button>
						<form action="${initParam.rootPath }/deleteMyQuestion.do" method="post">
							<input type="submit" value="삭제" onclick="return confirm('정말 삭제하시겠습니까?')" class="btn btn-basic btn-xs">
							<input type="hidden" value="${question.questionNum }" name="questionNum">
							<sec:csrfInput />
						</form>
					</div>
					<div class="answerBtnClassiDiv" style="float:left;display:block"> 
					<c:if test="${question.answer.answerText != null }">
						<!-- <span class="glyphicons glyphicons-chevron-down"></span> -->
							<button type="button" class="btn btn-default btn-xs answerViewBtn">답변보기</button>							
						</c:if>					
					</div>
					<div class="col-md-12 answerViewFormDiv" style="display:none">
						<hr>
						<!-- <span class="glyphicons glyphicons-chevron-up"></span> -->
						<button type="button" class="btn btn-default btn-xs cancelAnswerBtn">답변접기</button>
						<div class="col-md-9">
							<span class="label col-sm-9" style="color: #000; font-size: 11px;"> 답글 번호 : ${question.answer.answerNum} | 작성자 : ${question.answer.businessId}</span> 
							<span class="label col-sm-9" style="color: #000; font-size: 13px;"> 답글 내용 : ${question.answer.answerText}</span> 
							<span class="label col-sm-9" style="color: #000; font-size: 11px;"> 등록 일시 : ${question.answer.answerDate}</span>
						</div>					
					</div>
					<!-- 문의글 수정 -->
					<div class="col-sm-10 updateQuestionFormDiv" style="display:none">
						<button type="button" class="btn btn-basic btn-xs cancelQBtn">취소</button>
						<form action="${initParam.rootPath}/updateMyQuestion.do" method="post" class="questionUpdateFrom">
							<textarea class="form-control" rows="3" style="resize: none;" name="questionText" id="questionText" required>${question.questionText}</textarea>
							<input type="hidden" name="businessId" value="${restaurant.businessId }" />
							<input type="hidden" name="memberId" value="${currentMemberId }">
							<input type="hidden" name="questionNum" value="${question.questionNum}">
							<div class="col-sm-2">
								<input type="submit"  value="문의수정 등록" class="btn btn-info updateRegistQBtn">
							</div>
							<sec:csrfInput />
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		</c:otherwise>
	</c:choose>	
	<sec:csrfInput />
				
</div>
