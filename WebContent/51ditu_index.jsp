<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>找房子</title>
<link rel="stylesheet" href="css/main.css" />
<style type="text/css">
v\:* {behavior: url(#default#VML);}
html,body{overflow:hidden;}
#map {margin-left:0;left:0;margin-right:0;border-width: 0px 1px 1px 1px;}
</style>
<script src='dwr/engine.js'></script>
<script src='dwr/util.js'></script>
<script src='dwr/interface/rentUtil.js'></script>
<script src='js/func.js'></script>
<script
	src="http://ditu.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAHGYqsQNmu-Zmlz7HIyBjBxTkC2hDZ_uphd3RfMN_0RVjLMwHphSBNw9kDe7ld4vZEiwsMbVQ9VzG2A"
	type="text/javascript"></script>
<script type="text/javascript">

//<![CDATA[
// 需要处理异常信息。
dwr.engine.setErrorHandler(errh);

var defaultLng = getCookie('lng');
var defaultLat = getCookie('lat');
if (! defaultLng) {
	defaultLng = '116.397';
}
if (! defaultLat) {
	defaultLat = '39.917';
}

var roomDesc = ["", "一居 ", "两居 ", "三居 ", "四居 ", "五居 ", "多居 "];
var timeDiff = 0;
var lngStr = "${lng}";
var latStr = "${lat}";
var lng = lngStr == "" ? parseFloat(defaultLng) : parseFloat(lngStr);
var lat = latStr == "" ? parseFloat(defaultLat) : parseFloat(latStr);
var houseCache = new Array();
var markerCache = new Array();
var lastClickId = "";

var oldMinLng = 0;
var oldMaxLng = 0;
var oldMinLat = 0;
var oldMaxLat = 0;

var pageNo = 1;
var pageSize = 10;

// 为所有的标注创建一个“基准”图标，并指定它的阴影、尺寸、锚点等
var baseIcon = new GIcon();
baseIcon.shadow = "http://ditu.google.com/mapfiles/shadow50.png";
baseIcon.iconSize = new GSize(20, 34);
baseIcon.shadowSize = new GSize(37, 34);
baseIcon.iconAnchor = new GPoint(9, 34);
baseIcon.infoWindowAnchor = new GPoint(9, 2);
baseIcon.infoShadowAnchor = new GPoint(18, 25);

function setDefaultPos() {
	var center = mymap.map.getCenter();
	setCookie('lng', center.lng(), 10000);
	setCookie('lat', center.lat(), 10000);
}

// 清除页面中的缓存（房屋信息和标注信息）
function clearCache() {
	houseCache = new Array();
	markerCache = new Array();
}

// 翻页
function gotoPage(toPage) {
	pageNo = toPage;
	fetchMarkers();
}

// 下一页
function gotoNextPage() {
	gotoPage(pageNo + 1);
}

// 上一页
function gotoPrievPage() {
	gotoPage(pageNo - 1);
}

// 清除查询条件
function clearConditions() {
	var sf = $('searchForm');
	sf.priceFrom.value = '';
	sf.priceTo.value = '';
	sf.rooms.value = '';
	sf.keyword.value = '';
	
	fetchMarkers();
}

// 显示翻页条
function displayPageNav(pageObj) {
	pageNo = pageObj.currPageNumber;
	pageSize = pageSize;
	
	var info = "";
	var sf = $('searchForm');
	
	var pf = sf.priceFrom.value.trim();
	var pt = sf.priceTo.value.trim();
	
	if (pf != "" || pt != "") {
		info += " 价格";
		if (pf != "") {
			info += '大于' + pf;
		}
		if (pt != "") {
			info += '小于' + pt;
		}
		info += '元';
	}
	var rooms = sf.rooms.value;
	if (rooms != "") {
		info += ' ' + roomDesc[rooms];
	}
	var keyword = sf.keyword.value.trim();
	if (keyword != "") {
		info += ' 包含文字"' + keyword + '"';
	}
	info = info.trim();
	
	var isLimitSearch = info != "";
	
	if (info != "") {
		info = "<span style='color: blue;'>(查找条件: " + info + ")</span> <a href='javascript: clearConditions()' title='去掉查找条件'>查看全部</a><br/>";
	}
	
	if (pageObj.totalCount < 1) {
		dwr.util.setValue("findingHouse", "没找到此处的出租信息");
		$("pageNav").innerHTML = info;
		if (isLimitSearch) {
			d1('advancedSearch');
		}
		return;
	}
	
	
	dwr.util.setValue("findingHouse", "共找到" + pageObj.totalCount + "处出租信息.");
	
	var content = info;
	
	if (pageObj.lastPageNumber == 1) {
		$("pageNav").innerHTML = content + "<hr size='1'/>";
		return;
	}
	
	if (pageNo > 1) {
		content += "&laquo; <a href='javascript:gotoPrievPage()'>上一页</a>";
	}
	
	for (var i = 1; i <= pageObj.lastPageNumber; i++) {
		if (i == pageNo) {
			content += " <b>" + i + "</b>";
		} else {
			content += " <a href='javascript: gotoPage("+i+")'>[" + i + "]</a>";
		}
		
	}
	
	if (pageNo < pageObj.lastPageNumber) {
		content += " <a href='javascript:gotoNextPage()'>下一页</a> &raquo;";
	}
	
	$("pageNav").innerHTML = content + "<hr size='1'/>";
}

// 从服务器获取相关房屋信息
function fetchMarkers() {
	var bounds = mymap.map.getBounds();
	var ne = bounds.getNorthEast();
	var sw = bounds.getSouthWest();
	
	var sf = $('searchForm');
	var elDate = $('orderByDate');
	var elPrice = $('orderByPrice');
	var orderBy = elDate.checked ? elDate.value : elPrice.value;

	rentUtil.findHouses(
		{
			oldMinLat:oldMinLat, 
			oldMaxLat:oldMaxLat, 
			oldMinLng:oldMinLng, 
			oldMaxLng:oldMaxLng,
			minLng:sw.lng(), 
			maxLng:ne.lng(), 
			minLat:sw.lat(),
			maxLat:ne.lat(),
			onlyNew:false,
			priceFrom:sf.priceFrom.value,
			priceTo:sf.priceTo.value,
			rooms:sf.rooms.value,
			keyword:sf.keyword.value,
			orderBy:orderBy
		}, 
		pageNo, pageSize, function(pageObj) {
		
		displayPageNav(pageObj);
		clearCache();
		
		mymap.clearMarkers();
		dwr.util.removeAllRows("houseList", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    var houses = pageObj.pageElements;
		for (var i in houses) {
			var index = parseInt(i);
			var house = houses[i];
			var id = house.id;
			
			//if (houseCache[id] == undefined) {
				mymap.addMarker(index, house);
			//}
			
			var icon = "<img border='0' onclick='this.blur()' src='http://www.google.com/intl/zh-CN_cn/mapfiles/icon"+String.fromCharCode("A".charCodeAt(0) + index)+".png' />";
			
			dwr.util.cloneNode("pattern", { idSuffix:id });
			
			dwr.util.setValue("idIcon" + id, icon, { escapeHtml:false });
			dwr.util.setValue("idAddress" + id, house.addressDetail);
			dwr.util.setValue("idRooms" + id, roomDesc[house.rooms]);
			dwr.util.setValue("idPrice" + id, house.price);
			dwr.util.setValue("idProvider" + id, house.provider);
			dwr.util.setValue("idPhone" + id, house.phone);
			dwr.util.setValue("idDate" + id, house.publishTime.friendlyFormat(timeDiff));
			
			$("pattern" + id).style.display = "";
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
     
	GEvent.addListener(this.map, "zoomend", function() {
		fetchMarkers();
	});

	GEvent.addListener(this.map, "moveend", function() {
		fetchMarkers();
	});
}

MyMap.prototype.addMarker = function(index, house) {
	var letter = String.fromCharCode("A".charCodeAt(0) + index);
	var icon = new GIcon(baseIcon);
	icon.image = "http://ditu.google.com/mapfiles/marker" + letter + ".png";
	
	var marker = new GMarker(new GLatLng(house.latitude, house.longitude), icon);
	GEvent.addListener(marker, "mouseover", function() {
		openInfoOfHouse(house.id);
	});

	this.map.addOverlay(marker);
	markerCache[house.id] = marker;
	houseCache[house.id] = house;
}

MyMap.prototype.clearMarkers = function() {
	this.map.clearOverlays();
}

function openInfo(eleid) {
	// we were an id of the form "idIcon{id}", eg "idIcon42". We lookup the "42"
	lastClickId = eleid.substring(6);
	openInfoOfHouse(lastClickId);
}

function openInfoOfHouse(houseId) {
	// we were an id of the form "idIcon{id}", eg "idIcon42". We lookup the "42"
	var marker = markerCache[houseId];
	var house = houseCache[houseId];
	if (marker != undefined && house != undefined) {
		marker.openInfoWindowHtml(
			"<strong>" + house.addressDetail + 
			"</strong><br/>" + roomDesc[house.rooms] + "￥<strong>" +
			house.price + "</strong>/月" + 
			"<br/>"+house.provider+" "+house.phone+"<br/>" + 
			house.publishTime.friendlyFormat(timeDiff)
		);
	}
}

var mymap;

function load() {
	mymap = new MyMap(lng, lat, 14);
	rentUtil.getServerTime(function(serverTime) {
		timeDiff = new Date().getTime() - serverTime;
	});
	resizeApp();
	fetchMarkers();
}
  
function gotoRent() {
	var center = mymap.map.getCenter();
	window.location.href = "user/MyHouse.a?add&lng=" + center.lng() + "&lat=" + center.lat() + "&zoom=" + mymap.map.getZoom();
}

function resizeApp() {
	var offsetTop = 0;
	var mapElem = $("map");
	for (var elem = mapElem; elem; elem = elem.offsetParent) {
		offsetTop += elem.offsetTop;
	}
	var height = getWindowHeight() - offsetTop - 10;
	if (height >= 0) {
		mapElem.style.height = height + "px";
		$("pannel").style.height = (height + 4) + "px";
	}
}

//]]>
</script>
</head>
<body onresize="resizeApp()" onload="load()" onunload="GUnload()">
<div id="page">

<table>
	<tr>
		<td>
			<div id="header">
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td>
						<table>
							<tr>
								<td valign="top">
									<c:import url="/common/userinfo.jsp"></c:import> 
									<a href="#" onclick="gotoRent()">我要出租</a>
									<a href="#" onclick="setDefaultPos()" title="将当前地图设为默认位置">设为默认位置</a>
								</td>
							</tr>
							<tr>
								<td valign="bottom">
									<jsp:include page="/common/links_city.jsp"></jsp:include>
								</td>
							</tr>
						</table>
					</td>
					<td>
						<h1>找房子</h1>
					</td>
				</tr>
			</table>   
			</div><!-- header -->
		</td>
	</tr>

	<tr>
		<td>
			<table cellpadding="0" cellspacing="0" border="0">
				<tr>
					
					<td valign="top" width="240">
						<div id="pannel" style="width: 240px; height: 300px; overflow: auto;">
							<h3><a href="javascript: showHide('advancedSearch')">高级查找&nbsp;&raquo;</a></h3>
							<div id="advancedSearch" style="display: none">
							<form action="/" name="searchForm" id="searchForm" onsubmit="pageNo=1;fetchMarkers();return false">
								<table>
									<tr>
										<td>
											价格从 <input type="text" size="3" name="priceFrom" value=""/>
											到 <input type="text" size="3" name="priceTo" value=""/>
										</td>
									</tr>
									<tr>
										<td>
											居室:<br/>
											<select name="rooms">
												<option value="" selected="selected">任意</option>
												<option value="1">一居</option>
												<option value="2">两居</option>
												<option value="3">三居</option>
												<option value="4">四居</option>
												<option value="5">五居</option>
												<option value="6">更多</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>
											包含以下文字:<br/>
											<input type="text" size="20" name="keyword" value=""/>
										</td>
									</tr>
									<tr>
										<td>
											<input type="radio" id="orderByDate" value="publishTime desc, price" name="orderBy" checked="checked"/><label for="orderByDate">先显示最近发布</label><br/> 
											<input type="radio" id="orderByPrice" value="price, publishTime desc" name="orderBy"/><label for="orderByPrice">先显示价格低的</label>
										</td>
									</tr>
									<tr>
										<td>
											<input type="submit" name="submit" value="查找..."/>
										</td>
									</tr>
								</table>
							</form>
							</div>
							<h3><span id="findingHouse">正在为您找房子...</span></h3>
							<span id="pageNav"></span>
							<table>
								<tbody id="houseList">
									<tr id="pattern" style="display:none;">
										<td valign="top">
											<a href="#" id="idIcon" onclick="openInfoOfHouse(this.id.substring(6)); return false;"><img border="0"/></a>
										</td>
										<td valign="top">
											<strong><a href="javascript:;" id="idAddress" onclick="openInfoOfHouse(this.id.substring(9)); return false;">address</a></strong><br/>
											<span id="idRooms">3</span>￥<strong><span id="idPrice">2000</span></strong>/月<br/>
											<span style="color: graytext;"><span id="idProvider">someone</span> <span id="idPhone">mobile</span> <span id="idDate">publish date</span></span>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
					
					<td valign="top" width="100%">
						<div id="content">
							<div id="map" style="width: 600px; height: 500px">
							</div><!-- map -->
						</div><!-- mapContent -->
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


</div><!-- page -->
</body>
</html>