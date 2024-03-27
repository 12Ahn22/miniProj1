<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<!-- CSS 작업 시, 로고 수정 -->
	<div>
		<a href="/">RATTY</a>
	</div>
	<nav>
		<ul>
			<li><a href="intro.jsp">회사소개</a></li>
			<c:if test="${loginMember != null}">
				<!-- 로그인 상태일 때 수행할 내용 -->
				<li><a href="member?action=logout">로그아웃</a></li>
			</c:if>
			<c:if test="${loginMember == null}">
				<!-- 비로그인 상태일 때 수행할 내용 -->
				<li><a href="member?action=insertForm">회원가입</a></li>
				<li><a href="member?action=loginForm">로그인</a></li>
			</c:if>
			<li><a href="board?action=list">게시판</a></li>
		</ul>
	</nav>
</header>