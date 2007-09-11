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

姓名: <input type="text" name="profile.fullName" value="${profile.fullName }"/><br/>
电话: <input type="text" name="profile.phoneNo" value="${profile.phoneNo }"/><br/>
手机: <input type="text" name="profile.cellPhone" value="${profile.cellPhone }"/><br/>
Email: <input type="text" name="profile.email" value="${profile.email }"/><br/>

<input type="submit" name="save" value="保存"/>

</form>
</BODY>
</HTML>
