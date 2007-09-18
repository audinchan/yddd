<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>导入城市</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../common/validateScript.jsp"></script>
<script src='../js/func.js'></script>
<script>

</script>

</head>

<body>
<span class="error">
<nest:errors/>
</span>

<form name="importForm" action="ProvinceMgr.a" enctype="multipart/form-data" method="post" onsubmit="return validate_importForm(this)">
<nest:from/>

<table border="1">
	<tr>
		<td>选择文件（CSV格式）:</td>
		<td>
			<input type="file" name="file" value="">
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type="submit" value='导入' name="importCSV">
		</td>
	</tr>
</table>

</form>
</BODY>
<nest:javascript action="/admin/ProvinceMgr.a" form="importForm" on="importCSV"/>
</HTML>
