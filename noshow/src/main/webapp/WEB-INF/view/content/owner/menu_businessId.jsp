<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="sec"
   uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   <div>
   <sec:csrfInput/>
      <h1>VIEW - 메뉴 보기</h1>
      <hr>
      
      <table id="menu_tb">
         <thead id="thead">
            <tr>
               <td>메뉴이름</td>
               <td>메뉴 설명</td>
               <td>메뉴가격</td>
               <td>이미지 </td>
            </tr>
         </thead>
         <tbody id="tbody">
            <c:forEach items="${requestScope.menu}" var="item">
               <tr>
                  <td>${item.menuName}</td>
                  <td>${item.menuComment}</td>
                  <td>${item.menuPrice}</td>
                  <td> <img src="${initParam.rootPath }/menuPicture/${item.menuPicture}" width="350px" height=""></td>
               </tr>
            </c:forEach>
         </tbody>
      </table>
</div>