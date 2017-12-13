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
			$(this).parent().hide().next().show();
	}
	
	function changeAnswer() {
		if ($(this).text()=='답변보기') {
			$(".answerBtnClassiDiv").hide();
			$(".answerViewFormDiv").show();
		} else {
			$(".answerViewFormDiv").hide();
			$(".answerBtnClassiDiv").show();
		}
		
	}
	$(document).ready(function() {
		$("#myQuestionDiv").on("click", ".changeQBtn", changeQuestion); /* 문의글 수정 버튼 */
		$("#myQuestionDiv").on("click", ".cancelQBtn", changeQuestion); /* 수정 취소 버튼 */
		$("#myQuestionDiv").on("click", ".answerViewBtn", changeAnswer); /* 답글보기 버튼 */
		$("#myQuestionDiv").on("click", ".cancelAnswerBtn", changeAnswer); /* 답글접기 버튼 */
	});
</script>
<div class="col-sm-10" id="myQuestionDiv"
	style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;">
	<c:choose>
		<c:when test="${empty requestScope.questionList  }">
			<div class="col-md-6" style="margin-top: 10px;">해당 음식점에 등록된 문의가 없습니다.</div>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.questionList}" var="question">
				<div class="col-md-12 box1" style="background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;">
					<div style="float: right;" class="col-md-9 questionViewDiv">
						<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 문의글번호 : ${question.questionNum} | 작성자 : ${question.memberId}</span> 
						<span class="label col-sm-9" style="color: #000; font-size: 18px;"> 문의글 : ${question.questionText}</span> 
						<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 등록 일시 : ${question.questionTime}</span>
						<button type="button" class="btn btn-basic btn-xs changeQBtn">수정</button>
						<form action="${initParam.rootPath }/deleteMyQuestion.do" method="post">
							<input type="submit" class="btn btn-basic btn-xs" value="삭제" onclick="return confirm('정말 문의글을 삭제하시겠습니까?')">
							<input type="hidden" value="${question.questionNum}" name="questionNum">									
							<sec:csrfInput />
						</form>
						
					</div>
					<!-- 문의글 수정 -->
					<div class="col-sm-10 updateQuestionFormDiv" style="display:none">
						<button type="button" class="btn btn-basic btn-xs cancelQBtn">취소</button>
						<form action="${initParam.rootPath}/updateMyQuestion.do" method="post" class="questionUpdateFrom">
							<textarea class="form-control" rows="3" style="resize: none;" name="questionText" id="questionText" required>${question.questionText}</textarea>
							<input type="hidden" name="businessId" value="${question.businessId }" />
							<input type="hidden" name="memberId" value="${currentMemberId }">
							<input type="hidden" name="questionNum" value="${question.questionNum}">
							<div class="col-sm-2">
								<input type="submit" value="문의수정 등록" class="btn btn-info updateRegistQBtn" onclick="return confirm('정말 수정하시겠습니까?')">
							</div>
							<sec:csrfInput />
						</form>
					</div>
					<div class="answerBtnClassiDiv" style="float:left;display:block"> 
						<c:if test="${question.answer.answerText != null }">
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
				</div>			
			</c:forEach>
		</c:otherwise>
	</c:choose>	
	<sec:csrfInput />
</div>
