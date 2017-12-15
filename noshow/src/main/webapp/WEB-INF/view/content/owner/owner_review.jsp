<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="container" style="max-width: 800px; padding-top: 40px;">
	<div class="row" id="reviewListDiv">
		<c:choose>
			<c:when test="${empty requestScope.reviewList}">
				<div class="col-md-12" style="margin-top: 10px;">해당 음식점에 등록된 리뷰가 없습니다.</div>
			</c:when>
			<c:otherwise>
				<c:forEach items="${requestScope.reviewList}" var="review">
					<div class="col-md-12 box1" style="background: #fff; min-height: 80px; padding: 10px; margin-top: 10px;">
						<div class="col-md-3">
							<img src="${initParam.rootPath }/reviewPicture/${review.reviewImg}" style="height: 100%;">
						</div>
						<div style="float: right;" class="col-md-9">
							<span class="label col-sm-9" style="color: #000; font-size: 14px;"> 작성자 : ${review.memberId}</span> <span class="label col-sm-9"
								style="color: #000; font-size: 18px;"
							> 평점 : ${review.reviewGrade}</span> <span class="label col-sm-9" style="color: #000; font-size: 14px;">내용 : ${review.reviewText}</span> <span
								class="label col-sm-9" style="color: #000; font-size: 14px;"
							>작성 시간 : ${review.reviewTime}</span>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<sec:csrfInput />
	</div>
</div>