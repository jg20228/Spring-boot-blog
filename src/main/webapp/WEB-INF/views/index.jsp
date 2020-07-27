<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="layout/header.jsp" %>
<div class="container">
  <table class="table table-hover">
    <thead>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성일</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach var="post" items="${posts}">
      <tr>
        <td>${post.id}</td>
        <td>${post.title}</td>
        <td><fmt:formatDate value="${post.createDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<%@include file="layout/footer.jsp" %>