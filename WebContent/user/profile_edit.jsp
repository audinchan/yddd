<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>修改个人资料</TITLE>
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
		<td>
			<input type="text" name="profile.fullName" value="${profile.fullName }" maxlength="64"/>
		</td>
	</tr>
	<tr>
		<td><fmt:message key="profile.phoneNo"/>:</td>
		<td><input type="text" name="profile.phoneNo" value="${profile.phoneNo }" maxlength="12"/></td>
	</tr>
	<tr>
		<td><fmt:message key="profile.cellPhone"/>:</td>
		<td><input type="text" name="profile.cellPhone" value="${profile.cellPhone }" maxlength="11"/></td>
	</tr>
	<tr>
		<td><fmt:message key="profile.email"/>:</td>
		<td><input type="text" name="profile.email" value="${profile.email }" maxlength="64"></td>
	</tr>

</table>

<input type="submit" name="save" value="保存"/>
<a href="../">首页</a>

</form>
</BODY>
</HTML>
