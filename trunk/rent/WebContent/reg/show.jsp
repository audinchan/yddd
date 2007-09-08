<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>注册帐号</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../common/validateScript.jsp"></script>
<script src='../dwr/engine.js'></script>
<script src='../dwr/util.js'></script>
<script src='../dwr/interface/userUtil.js'></script>
<script>
function checkUser(un) {
	userUtil.checkUserExists(un, function(exists) {
		if (exists) {
			dwr.util.setValue("userExistError", "该用户名已经存在了，请换一个。");
			d1("userExistError");
		}
	});
}

function checkForm(frm) {
	if (frm.passwordConfirm.value != frm['user.password'].value) {
		alert("两次输入的密码不一致。");
		return false;
	}
	return validate_userForm(frm);
}
</script>

</head>

<body>
<span class="error">
<nest:errors/>
</span>

<form name="userForm" action="Register.a" method="post" onsubmit="return checkForm(this)">
<nest:from/>

<table border="1">
	<tr>
		<td><fmt:message key="user.username"/>:</td>
		<td>
			<input type="text" name="user.username" value="${user.username }" maxlength="64" onchange="checkUser(this.value)">
			<span id="userExistError" style="display: none" class="error"></span>
		</td>
	</tr>
	<tr>
		<td><fmt:message key="user.password"/>:</td>
		<td><input type="password" name="user.password" value=""></td>
	</tr>
	<tr>
		<td><fmt:message key="user.passwordConfirm"/>:</td>
		<td><input type="password" name="passwordConfirm" value=""></td>
	</tr>
	<tr>
		<td><fmt:message key="user.displayName"/>:</td>
		<td><input type="text" name="user.displayName" value="${user.displayName }" maxlength="64"></td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type="submit" value='<fmt:message key="opt.register"/>' name="register">
		</td>
	</tr>
</table>

</form>
</BODY>
<nest:javascript action="/reg/Register.a" form="userForm" on="register"/>
</HTML>
