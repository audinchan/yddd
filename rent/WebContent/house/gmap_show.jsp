<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>出租房详细信息</title>
<script src='js/func.js'></script>
<script
	src="http://ditu.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAHGYqsQNmu-Zmlz7HIyBjBxTkC2hDZ_uphd3RfMN_0RVjLMwHphSBNw9kDe7ld4vZEiwsMbVQ9VzG2A"
	type="text/javascript"></script>
<script type="text/javascript">
var roomDesc = ["", "一居 ", "两居 ", "三居 ", "四居 ", "五居 ", "多居 "];

function load() {
	var map = new GMap2(document.getElementById("map"));
	map.addControl(new GLargeMapControl ());
	map.addControl(new GScaleControl());
	//map.addControl(new GOverviewMapControl());
	map.setCenter(new GLatLng(${info.latitude}, ${info.longitude}), 14);
	
	var marker = new GMarker(new GLatLng(${info.latitude}, ${info.longitude}));
	map.addOverlay(marker);
}
</script>
</head>

<body onload="load()" onunload="GUnload()">
<table>
	<tr>
		<td>
			地址: ${info.addressDetail }<br/>
			居室: <script>document.write(roomDesc[${info.rooms }]);</script><br/>
			租金: ${info.price }/月<br/>
			联系人: ${info.provider }<br/>
			电话: ${info.phone }<br/>
			Email: ${info.email }<br/>
			发布时间: ${info.publishTime }<br/>
			<hr/>
			<c:if test="${not empty visitUsers}">
				<c:forEach items="${visitUsers}" var="u">
					${u.nickname }<br/>
				</c:forEach>
			</c:if>
			<hr/>
			<c:if test="${not empty relatedHouses}">
				<c:forEach items="${relatedHouses}" var="h">
					<c:if test="${h.id != info.id}">
						<a href='House.a?show&info.id=${h.id }'>${h.addressDetail }</a>
						<script>document.write(roomDesc[${h.rooms }]);</script>
						￥${h.price }/月
						<br/>
					</c:if>
				</c:forEach>
			</c:if>
		</td>
		<td>
			<div id="map" style="width: 400px; height: 300px">
			</div>
		</td>
	</tr>
</table>

</body>
</html>