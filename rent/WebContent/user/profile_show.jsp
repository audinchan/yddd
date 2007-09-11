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

姓名: ${profile.fullName }<br/>
电话: ${profile.phoneNo }<br/>
手机: ${profile.cellPhone }<br/>
Email: ${profile.email }<p/>

<a href="Profile.a?edit">修改</a>

</form>
</BODY>
</HTML>
