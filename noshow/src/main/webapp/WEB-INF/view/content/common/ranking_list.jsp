<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">					
	<div class="row">
		<c:forEach items="${requestScope.rankingList }" var="rank" varStatus="cnt">
			<div class="col-md-4" style="margin-top: 10px; padding-bottom: 10px; background-color: aliceblue;" >
				<div style="margin-right: 10px; width: 100%; height: 100%; padding: 10px; text-align: center;">
					<sec:authorize access="!isAuthenticated()">
						<a href="${initParam.rootPath }/login_form.do">
							<img src="${initParam.rootPath }/rtPicture/${rank.restaurant.rtPicture }"  style="text-align: center; min-height: 100px;" class="col-sm-12"> 
						</a>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<a href="${initParam.rootPath }/restaurantListByName.do?businessId=${rank.businessId}">
							<img src="${initParam.rootPath }/rtPicture/${rank.restaurant.rtPicture }"  style="text-align: center; min-height: 100px;" class="col-sm-12"> 
						</a>
					</sec:authorize>
						<div class="col-sm-12" style="margin-top: 10px;">
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							순위 : ${cnt.count }
						</span> 
						<c:if test="${requestScope.avgCnt != null }">
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							평점 : ${rank.avgGrade }
						</span>
						</c:if>
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							이름 : ${rank.restaurant.rtName }
						</span> 
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							업종 : ${rank.restaurant.field.fieldVal}
						</span> 
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							위치 : ${rank.restaurant.rtAddress }
						</span>
						<span class="label col-sm-12" style="color: #000; font-size: 14px;">
							전화번호 : ${rank.restaurant.rtTel }
						</span>
						</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
</div>
