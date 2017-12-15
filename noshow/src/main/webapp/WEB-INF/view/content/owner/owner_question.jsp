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
		$(".answerViewFormDiv").hide();
		$(".answerBtnClassiDiv").show();
		if($(this).text()=='답변보기')
			$(this).parent().hide().next().next().show();

	}
	
	function answerRegist() {
		$(".registAnswerFormDiv").hide();
		$(".answerBtnClassiDiv").show();
		if($(this).text()=='답변달기')
			$(this).parent().hide().next().show();
		
	} 
	
	function changeUpdateDiv() {
		//$(".updateAnswerFormDiv").hide(); /* 답글 수정Form div */
	//	$(".answerViewFormDiv").show();   /* 답글 보기 div */
		if($(this).text()=='수정') {
			$(this).parent().hide().next().show();
		} else {
			$(this).parent().hide().prev().show();
		}

		
	}
	
	$(document).ready(function() {
		$("#ownerQuestionDiv").on("click", ".answerBeforeBtn", answerRegist); /* 답변달기 버튼  */
		$("#ownerQuestionDiv").on("click", ".cancelAnswerBtn", answerRegist); /* 답변달기 취소 버튼 */
		$("#ownerQuestionDiv").on("click", ".answerViewBtn", changeAnswerDiv);	 /* 답변보기 버튼 */
		$("#ownerQuestionDiv").on("click", ".closeAnswerBtn", changeAnswerDiv);  /* 답변접기 버튼 */
		$("#ownerQuestionDiv").on("click", ".updateAnswerBtn", changeUpdateDiv); /* 답변수정 버튼 */
		$("#ownerQuestionDiv").on("click", ".updateCancelBtn", changeUpdateDiv); /* 답변수정 취소 버튼 */
	});


</script>

<sec:authentication property='principal.memberId' var="currentBusinessId"/>
<div class="container" id="ownerQuestionDiv" style="max-width: 900px;">
	<div class="col-sm-12" id="questionListDiv"
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
						<div class="answerBtnClassiDiv" style="float:left;">  	
							<c:choose>
								<c:when test="${question.answer.answerText == null}">
									<button type="button" class="btn btn-primary answerBeforeBtn">답변달기</button>
								</c:when>	 
								<c:otherwise>
									<button type="button" class="btn btn-default answerViewBtn">답변보기</button>							
								</c:otherwise>				
							</c:choose>					
						</div>
						<!-- 답글 등록 div -->
						<div class="col-md-12 registAnswerFormDiv" style="display:none">
							<button type="button" class="btn btn-basic cancelAnswerBtn">취소</button>
							<form action="${initParam.rootPath}/registAnswer.do" method="post" class="registAnswerFrom">
								<textarea class="form-control" rows="3" style="resize: none;" name="answerText" id="answerText" required></textarea>
								<input type="hidden" name="businessId" value="${currentBusinessId }" />
								<input type="hidden" name="questionNum" value="${question.questionNum}">
								<input type="submit"  value="답변 등록" class="btn btn-info answerRegistQBtn" onclick="return confirm('이 내용으로 답변을 등록하시겠습니까?')">
								<sec:csrfInput />
							</form>						
						</div>
						<!-- 답글 보기 div -->
						<div class="col-md-12 answerViewFormDiv" style="display:none; margin-top: 10px; border-top: 1px solid #E0e0e0; padding-top: 20px;">   	
							<div class="row">		
							<div class="col-sm-9"> 
								<span class="label col-sm-12" style="color: #000; font-size: 14px;"> 답글 번호 : ${question.answer.answerNum} | 작성자 : ${question.memberId}</span> 
								<span class="label col-sm-12" style="color: #000; font-size: 18px;"> 답글 내용 : ${question.answer.answerText}</span> 
								<span class="label col-sm-12" style="color: #000; font-size: 14px;"> 등록 일시 : ${question.answer.answerDate}</span>
							</div> 	
								<div class="col-sm-3">
									<button type="button" class="btn btn-default closeAnswerBtn" style="margin-bottom: 5px;">답글접기</button>
									<form action="${initParam.rootPath }/deleteAnswer.do" method="post">
										<input type="hidden" value="${question.answer.answerNum}" name="answerNum">
										<input type="submit" class="btn btn-default" value="삭제" onclick="return confirm('정말 답글을 삭제하시겠습니까?')">
										<button type="button" class="btn btn-default updateAnswerBtn">수정</button>
										<sec:csrfInput /> 
									</form>
								</div>
							</div>  
						</div>
						
						
						<!-- 답글 수정 div -->
						<div class="col-sm-10 updateAnswerFormDiv" style="display:none">
							<button type="button" class="btn btn-default updateCancelBtn">취소</button>     
							<form action="${initParam.rootPath}/updateAnswer.do" method="post" class="questionUpdateFrom">
								<textarea class="form-control" rows="3" style="resize: none;" name="answerText" id="answerText" required>${question.answer.answerText}</textarea>
								<input type="hidden" name="businessId" value="${currentBusinessId}" />
								<input type="hidden" name="answerNum" value="${question.answer.answerNum}">
								<input type="hidden" name="questionNum" value="${question.questionNum}">
								<div class="col-sm-2">
									<input type="submit"  value="문의수정 등록" class="btn btn-info updateRegistQBtn" onclick="return confirm('수정하시겠습니까?')">
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

