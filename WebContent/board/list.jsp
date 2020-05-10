<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>게시판</h1>
<p><a href="${cp }/board/list">전체글목록</a> | <a href="${cp }/home">홈으로</a></p>
<table border="1" width="600">
	<tr>
		<th>글번호</th><th>작성자</th><th>제목</th>
	</tr>
	<c:forEach var="vo" items="${list }">
		<tr>
			<td>${vo.num }</td>
			<td>${vo.writer }</td>
			<td><a href="${cp }/board/detail?num=${vo.num}">${vo.title }</a></td>
		</tr>
	</c:forEach>
</table>
<br>
<div>
<c:choose>
	<c:when test="${startPage>4 }">
		<a href="${cp }/board/list?pageNum=${startPage-1}">[이전]</a>
	</c:when>
	<c:otherwise>
		이전
	</c:otherwise>
</c:choose>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${i==pageNum }">
				<a href="${cp }/board/list?pageNum=${i }&field=${field}&keyword=${keyword}">
				<span style='color:blue'>[${i }]</span></a>
			</c:when>
			<c:otherwise>
				<a href="${cp }/board/list?pageNum=${i }&field=${field}&keyword=${keyword}">
				<span style='color:#999'>[${i }]</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
<c:choose>
	<c:when test="${endPage<pageCount }">
		<a href="${cp }/board/list?pageNum=${endPage+1}">[다음]</a>
	</c:when>
	<c:otherwise>
		다음
	</c:otherwise>
</c:choose>
<div>
	<form method="post" action="${cp }/board/list">
		<select name="field">
			<option value="writer" <c:if test="${field=='writer' }">selected=selected</c:if>>작성자</option>
			<option value="title" <c:if test="${field=='title' }">selected=selected</c:if>>제목</option>
			<option value="content" <c:if test="${field=='content' }">selected=selected</c:if>>내용</option>
		</select>
		<input type="text" name="keyword" value="${keyword }">
		<input type="submit" value="검색">
	</form>
</div>
</div>







