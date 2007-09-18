<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>我的个人资料</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script src='../js/func.js'></script>
<script>

</script>

</head>

<body>

<form name="userForm" action="Profile.a" method="post">
<table border="1">
	<tr>
		<td><fmt:message key="profile.fullName"/>:</td>
		<td>${profile.fullName }
		</td>
	</tr>
	<tr>
		<td><fmt:message key="profile.phoneNo"/>:</td>
		<td>${profile.phoneNo }</td>
	</tr>
	<tr>
		<td><fmt:message key="profile.cellPhone"/>:</td>
		<td>${profile.cellPhone }</td>
	</tr>
	<tr>
		<td><fmt:message key="profile.email"/>:</td>
		<td>${profile.email }</td>
	</tr>

</table>

<a href="Profile.a?edit">修改</a>
<a href="../">首页</a>

</form>
</BODY>
</HTML>
