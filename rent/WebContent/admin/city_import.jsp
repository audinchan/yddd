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

<form name="importForm" action="CityMgr.a" enctype="multipart/form-data" method="post" onsubmit="return validate_importForm(this)">
<nest:from/>

<table border="1">
	<tr>
		<td>选择文件（CSV格式）:</td>
		<td>
			<input type="file" name="file" value="">
		</td>
	</tr>
	<tr>
		<td>省份:</td>
		<td>
			<select name="provinceId">
				<c:forEach items="${provinceList }" var="province">
					<option value="${province.id }">${province.name }</option>
				</c:forEach>
			</select>
			<br>
			<strong>说明：</strong><br>
			如果不选省份，则导入文件的格式为“省份名称,城市名称,经度,纬度”；<br>
			如果选择了某个省份，则导入文件的格式为“城市名称,经度,纬度”。
		</td>
	</tr>
	<tr>
		<td></td>
		<td>
		<input type="submit" value='导入' name="importCSV">
		</td>
	</tr>
</table>

点击进入各个城市的热点区域管理:<br>
<c:forEach items="${provinceList }" var="province">
	<a href="AreaMgr.a?provinceId=${province.id }" target=_blank>${province.name }</a> 
</c:forEach>

</form>
</BODY>
<nest:javascript action="/admin/CityMgr.a" form="importForm" on="importCSV"/>
</HTML>
