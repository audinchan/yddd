<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<c:if test="${not empty sessionScope[userKey]}">
Hello, ${sessionScope[userKey].displayName} [<a href="${ctx }/j_acegi_logout">退出</a>]
<a href='${ctx }/user/Profile.a' target="_blank">个人设置</a>
</c:if>
<c:if test="${empty sessionScope[userKey]}">
[<a href="${ctx }/reg/Register.a">注册</a> | 
<a href="${ctx }/login.jsp">登录</a>]
</c:if>