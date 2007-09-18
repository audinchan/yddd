<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>测试随机地址解析</title>
<link rel="stylesheet" href="../css/main.css" />
<style type="text/css">
v\:* {behavior: url(#default#VML);}
html,body{overflow:hidden;}
#map {margin-left:0;left:0;margin-right:0;border-width: 0px 1px 1px 1px;}
</style>
<script src='../dwr/engine.js'></script>
<script src='../dwr/util.js'></script>
<script src='../dwr/interface/rentUtil.js'></script>
<script src='../js/func.js'></script>
<script
	src="http://ditu.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAHGYqsQNmu-Zmlz7HIyBjBxTkC2hDZ_uphd3RfMN_0RVjLMwHphSBNw9kDe7ld4vZEiwsMbVQ9VzG2A"
	type="text/javascript"></script>
<script type="text/javascript">

//<![CDATA[
// 需要处理异常信息。
dwr.engine.setErrorHandler(errh);

var isSearching = false;

function getRandomAddr() {
	if (isSearching) {
		return;
	}
	isSearching = true;
	rentUtil.getRandomPoint(function (ltPoint) {
		isSearching = false;
		if (ltPoint) {
			dwr.util.setValue("address", ltPoint.address);
			dwr.util.setValue("city", ltPoint.city);
			dwr.util.setValue("lat", ltPoint.lat);
			dwr.util.setValue("lng", ltPoint.lng);
			mymap.show(ltPoint);
		}
	});
}

// 自定义的地图类
function MyMap(lng, lat, zoom) {
	this.map = new GMap2(document.getElementById("map"));
	this.map.addControl(new GLargeMapControl ());
	this.map.addControl(new GScaleControl());
	this.map.addControl(new GOverviewMapControl());
	this.map.setCenter(new GLatLng(lat, lng), zoom);
}

MyMap.prototype.show = function(ltPoint) {
	this.map.openInfoWindowHtml(new GLatLng(ltPoint.lat, ltPoint.lng), ltPoint.address);
}

var mymap;

function load() {
	mymap = new MyMap(116.397, 39.917, 11);
	
	setInterval("getRandomAddr()",10000);
}

//]]>
</script>
</head>
<body onload="load()" onunload="GUnload()">
<div>
	地址: <span id="address"></span><br/>
	城市: <span id="city"></span><br/>
	LAT: <span id="lat"></span><br/>
	LNG: <span id="lng"></span><br/>
</div>
<div id="map" style="width: 600px; height: 500px">
</div>

</body>
</html>