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

	function changeAnswerDiv() {
		if ($(this).text()=='답변보기') {
			$(".anserBtnClassiDiv").hide(); /* 문의글 하단 버튼 div */
			$(".answerViewFormDiv").show(); /* 답글보기 div */
		} else if($(this).text()=='접기'){
			$(".answerViewFormDiv").hide(); /* 답글보기 div */
			$(".anserBtnClassiDiv").show(); /* 문의글 하단 버튼 div */
		} else if($(this).text()=='답변달기'){
			$(".anserBtnClassiDiv").hide();   /* 문의글 하단 버튼 div */
			$(".registAnswerFormDiv").show(); /* 답글 등록 div */
		} else {
			$(".registAnswerFormDiv").hide(); /* 답글 등록 div */
			$(".anserBtnClassiDiv").show();   /* 문의글 하단 버튼 div */
		}
		
	}
	
	function changeUpdateDiv() {
		if($(this).text()=='수정'){
			$(".answerViewFormDiv").hide();	/* 답글 보기 div */
			$(".updateAnswerFormDiv").show(); /* 답글 수정Form div */
		} else {
			$(".updateAnswerFormDiv").hide(); /* 답글 수정Form div */
			$(".answerViewFormDiv").show();   /* 답글 보기 div */
		}
	}
	
	$(document).ready(function() {
		$("#ownerQuestionDiv").on("click", ".answerBeforeBtn", changeAnswerDiv); /* 답변달기 버튼  */
		$("#ownerQuestionDiv").on("click", ".cancelAnswerBtn", changeAnswerDiv); /* 답변달기 취소 버튼 */
		$("#ownerQuestionDiv").on("click", ".answerViewBtn", changeAnswerDiv);	 /* 답변보기 버튼 */
		$("#ownerQuestionDiv").on("click", ".closeAnswerBtn", changeAnswerDiv);  /* 답변접기 버튼 */
		$("#ownerQuestionDiv").on("click", ".updateAnswerBtn", changeUpdateDiv); /* 답변수정 버튼 */
		$("#ownerQuestionDiv").on("click", ".updateCancelBtn", changeUpdateDiv); /* 답변수정 취소 버튼 */
	});


</script>

<sec:authentication property='principal.memberId' var="currentBusinessId"/>
<div class="container" id="ownerQuestionDiv">
	<div class="col-sm-10" id="questionListDiv"
		style="background: #fff; min-height: 600px; margin-bottom: 10px; padding-top: 20px;">
		<c:choose>
			<c:when test="${empty requestScope.questionList  }">
				<div class="col-md-6" style="margin-top: 10px;">해당 음식점에 등록된 문의가 없습니다.</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${requestScope.questionList}" var="question">
					<div class="col-md-12 box1" style="background: #fff; min-height: 100px; padding: 10px; margin-top: 10px;">
						<div class="col-md-9">
							<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 문의글번호 : ${question.questionNum} | 작성자 : ${question.memberId}</span> 
							<span class="label col-sm-9" style="color: #000; font-size: 18px;"> 문의글 : ${question.questionText}</span> 
							<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 등록 일시 : ${question.questionTime}</span>
						</div>
						<div class="anserBtnClassiDiv" style="float:left;display:block"> 
							<c:choose>
								<c:when test="${question.answer.answerText == null}">
									<button type="button" class="btn btn-primary btn-xs answerBeforeBtn">답변달기	</button>
								</c:when>	
								<c:otherwise>
									<button type="button" class="btn btn-basic btn-xs answerViewBtn">답변보기</button>							
								</c:otherwise>				
							</c:choose>					
						</div>
						<!-- 답글 등록 div -->
						<div class="col-md-12 registAnswerFormDiv" style="display:none">
							<button type="button" class="btn btn-basic btn-xs cancelAnswerBtn">취소</button>
							<form action="${initParam.rootPath}/registAnswer.do" method="post" class="registAnswerFrom">
								<textarea class="form-control" rows="3" style="resize: none;" name="answerText" id="answerText" required>
							</textarea>
								<input type="hidden" name="businessId" value="${currentBusinessId }" />
								<input type="hidden" name="questionNum" value="${question.questionNum}">
								<div class="col-sm-2">
									<input type="button"  value="답변 등록" class="btn btn-info answerRegistQBtn">
								</div>
								<sec:csrfInput />
							</form>						
						</div>
						<!-- 답글 보기 div -->
						<div class="col-md-12 answerViewFormDiv" style="display:none">
							<button type="button" class="btn btn-basic btn-xs closeAnswerBtn">답글접기</button>
							<div class="col-md-9">
								<button type="button" class="btn btn-basic btn-xs updateAnswerBtn">수정</button>
								<form action="${initParam.rootPath }/deleteAnswer.do" method="post">
									<input type="hidden" value="${question.answer.answerNum}" name="answerNum">
									<input type="submit" class="btn btn-basic btn-xs" value="삭제" onclick="return confirm('정말 답글을 삭제하시겠습니까?')">
								</form>
								<a href="javascript:deleteAnswer('${question.answer.answerNum}')" class="btn btn-basic btn-xs" role="button">삭제</a>
								<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 답글 번호 : ${question.answer.answerNum} | 작성자 : ${question.memberId}</span> 
								<span class="label col-sm-9" style="color: #000; font-size: 18px;"> 답글 내용 : ${question.answer.answerText}</span> 
								<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 등록 일시 : ${question.answer.answerDate}</span>
							</div>					
						</div>
						<!-- 답글 수정 div -->
						<div class="col-sm-10 updateAnswerFormDiv" style="display:none">
							<button type="button" class="btn btn-basic btn-xs updateCancelBtn">취소</button>
							<form action="${initParam.rootPath}/registQuestion.do" method="post" class="questionUpdateFrom">
								<textarea class="form-control" rows="3" style="resize: none;" name="questionText" id="questionText" required>${question.answer.answerText}</textarea>
								<input type="hidden" name="businessId" value="${restaurant.businessId }" />
								<input type="hidden" name="memberId" value="${currentMemberId }">
								<input type="hidden" name="questionNum" value="${question.questionNum}">
								<div class="col-sm-2">
									<input type="button"  value="문의수정 등록" class="btn btn-info updateRegistQBtn">
								</div>
								<sec:csrfInput />
							</form>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>	
		<sec:csrfInput />
	</div>
</div>

