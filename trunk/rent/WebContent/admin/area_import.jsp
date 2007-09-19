<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HTML>
<HEAD>
<TITLE>导入城市热点区域</TITLE>
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

<form name="importForm" action="AreaMgr.a" enctype="multipart/form-data" method="post" onsubmit="return validate_importForm(this)">
<nest:from/>
<input type="hidden" name="provinceId" value="${provinceId }">

<table border="1">
	<tr>
		<td>选择文件（CSV格式）:</td>
		<td>
			<input type="file" name="file" value="">
		</td>
	</tr>
	<tr>
		<td>城市:</td>
		<td>
			<select name="cityId">
				<c:forEach items="${cityList }" var="city">
					<option value="${city.id }">${city.name }</option>
				</c:forEach>
			</select>
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
<nest:javascript action="/admin/AreaMgr.a" form="importForm" on="importCSV"/>
</HTML>
