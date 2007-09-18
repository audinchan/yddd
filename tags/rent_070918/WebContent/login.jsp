<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="css/main.css" rel="stylesheet" type="text/css">
</HEAD>
<BODY>
<form method="post" action="j_acegi_security_check">
登录名: <input name="j_username" type="text" size="19" value="${param.j_username }"><br>
密&nbsp;&nbsp;码: <input name="j_password" type="password" size="19"><br>
<input name="submit" type="submit" value='登录'>
<p>
还没有建立帐号？<a href="${ctx }/reg/Register.a">点这里免费注册一个</a>。<p/>
<a href="./?lng=${param.lng }&lat=${param.lat }&zoom=${param.zoom }">返回首页</a><p/>
<c:if test="${not empty param.errcode}">
<c:choose>
	<c:when test='${param.errcode == 0}'><span class="error_common">登录名或密码错误。</span></c:when>
	<c:when test='${param.errcode == 1}'><span class="error_common">登录名或密码错误。</span></c:when>
	<c:when test='${param.errcode == 2}'><span class="error_common">帐号已被禁用。</span></c:when>
	<c:when test='${param.errcode == 3}'><span class="error_common">帐号已被禁用。</span></c:when>
	<c:when test='${param.errcode == 4}'><span class="error_common">登录用户太多，请稍后重试。</span></c:when>
	<c:when test='${param.errcode == 5}'><span class="error_common">数据库错误，请稍后重试。</span></c:when>
	<c:otherwise><span class="error_common">系统错误，请稍后重试。</span></c:otherwise>
</c:choose>
</c:if>

</form>
</BODY>
</HTML>
