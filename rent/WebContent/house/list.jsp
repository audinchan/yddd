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
<script src='../dwr/engine.js'></script>
<script src='../dwr/util.js'></script>
<script src='../dwr/interface/rentUtil.js'></script>
<script src='../js/func.js'></script>
<script
	src="http://ditu.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAHGYqsQNmu-Zmlz7HIyBjBxTkC2hDZ_uphd3RfMN_0RVjLMwHphSBNw9kDe7ld4vZEiwsMbVQ9VzG2A"
	type="text/javascript"></script>
<script type="text/javascript">

// 需要处理异常信息。
dwr.engine.setErrorHandler(errh);

//<![CDATA[

var roomDesc = ["", "一居 ", "两居 ", "三居 ", "四居 ", "五居 ", "多居 "];
var timeDiff = 0;
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

var mymap;
var newMarker;
var addInfo;
var isMoved = false;


var lng1 = '${param.lng}';
var lat1 = '${param.lat}';
var zoom1 = '${param.zoom}';
var add = '${param.add}';

var lng = lng1 == "" ? 116.397 : parseFloat(lng1);
var lat = lat1 == "" ? 39.917 : parseFloat(lat1);
var zoom = zoom1 == "" ? 14 : parseInt(zoom1);



function tpl(id) {
	var el = $(id);
	if (el) {
		var s = el.innerHTML;
		el.parentNode.removeChild(el);
		return s;
	} else {
		return "";
	}
}

function tplClone(which, callback) {
	var id = "_edit";

	dwr.util.cloneNode(which, { idSuffix: id });
	$(which + id).style.display = "";
	
	if (typeof callback == "function") {
		callback();
	}

	return tpl(which + id);
}

function publishRent() {
	isMoved = false;
	$('btnPublish').disabled = true;
	
	newMarker = new GMarker(mymap.map.getCenter(), {
		draggable: true/*,
		icon: icon*/
	});
	GEvent.addListener(newMarker, "click", function() {
		newMarker.openInfoWindowHtml(addInfo);
	});
	
	GEvent.addListener(newMarker, "dragstart", function() {
		mymap.map.closeInfoWindow();
	});
	
	GEvent.addListener(newMarker, "dragend", function() {
		if (!isMoved) {
			isMoved = true;
			addInfo = tplClone('tplEdit', function() {
				dwr.util.setValue('editTitle_edit', '2.请输入出租房屋的信息');
			});
		}
		newMarker.openInfoWindowHtml(addInfo);
	});
		  
	mymap.map.addOverlay(newMarker);
	
	addInfo = tplClone('tplPublishHint');
	newMarker.openInfoWindowHtml(addInfo);

}

function submitInfo() {
	var houseId = $('houseid_edit').value;
	var marker = markerCache[houseId];
	if (!marker) {
		marker = newMarker;
	}
	var p = marker.getPoint();
	
   	rentUtil.saveHouseEdit(
   		{
   			longitude: p.lng(),
   			latitude: p.lat(),
   			id: houseId,
   			addressDetail: $('address_edit').value,
   			rooms: $("rooms_edit").value,
   			price: $("price_edit").value,
   			provider: $("provider_edit").value,
   			phone: $("phone_edit").value,
   			email: $("email_edit").value, 
   		},
   		function(house) {
   			var id = house.id;
   			if (!houseCache[id]) {
   				$('btnPublish').disabled = false;
   				fetchMarkers();
   				return;
   				/*
   				var index = 0;
   				var icon = "<img border='0' onclick='this.blur()' src='http://www.google.com/intl/zh-CN_cn/mapfiles/icon"+String.fromCharCode("A".charCodeAt(0) + index)+".png' />";
				dwr.util.cloneNode("pattern", { idSuffix:id });
				dwr.util.setValue("idIcon" + id, icon, { escapeHtml:false });
				$("pattern" + id).style.display = "";
				
				markerCache[id] = newMarker;
				*/
   			}
   			houseCache[id] = house;
   			
			dwr.util.setValue("idAddress" + id, house.addressDetail);
			dwr.util.setValue("idRooms" + id, roomDesc[house.rooms]);
			dwr.util.setValue("idPrice" + id, house.price);
			dwr.util.setValue("idProvider" + id, house.provider);
			dwr.util.setValue("idPhone" + id, house.phone);
			dwr.util.setValue("idDate" + id, house.publishTime.friendlyFormat(timeDiff));
			
			markerCache[id].openInfoWindowHtml('保存成功。<p/><input type="button" value="确定" onclick="mymap.map.closeInfoWindow()"/>');
   		}
   	);
   	
   	marker.openInfoWindowHtml("正在保存，请稍候……");
}

function deleteHouse(id) {
	if (confirm('确定要删除吗？')) {
		rentUtil.deleteHouse(id, function() {
			var el = $("pattern" + id);
			if (el) {
				el.parentNode.removeChild(el);
			}
			mymap.removeMarker(id);
		});
	}
}

function clearCache() {
	houseCache = new Array();
	markerCache = new Array();
}

function gotoPage(toPage) {
	pageNo = toPage;
	fetchMarkers();
}

function gotoNextPage() {
	gotoPage(pageNo + 1);
}

function gotoPrievPage() {
	gotoPage(pageNo - 1);
}


function displayPageNav(pageObj) {
	pageNo = pageObj.currPageNumber;
	pageSize = pageSize;
	
	var info = "";
	
	dwr.util.setValue("findingHouse", "您已发布" + pageObj.totalCount + "处出租信息.");
	
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

function fetchMarkers() {
	
	rentUtil.findUserHouses('', pageNo, pageSize, function(pageObj) {
	
		displayPageNav(pageObj);
		clearCache();
		
		mymap.clearMarkers();
		dwr.util.removeAllRows("houseList", { filter:function(tr) {
	      return (tr.id != "pattern");
	    }});
	    
	    var toSetCenter = true;
		var houses = pageObj.pageElements;
		for (var i in houses) {
		    var index = parseInt(i);
			var house = houses[i];
			var id = house.id;
			
			// 在地图上标注出租信息
			mymap.addMarker(index, house);
			
			// 设置地图中心为第一个出租信息处
			if (add != '1' && toSetCenter) {
				toSetCenter = false;
				mymap.map.setCenter(new GLatLng(house.latitude, house.longitude), 14);
			}
			
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

function MyMap(lng, lat, zoom) {
	this.map = new GMap2(document.getElementById("map"));
	this.map.addControl(new GLargeMapControl ());
	this.map.addControl(new GScaleControl());
	this.map.addControl(new GOverviewMapControl());
	this.map.setCenter(new GLatLng(lat, lng), zoom);

}

MyMap.prototype.addMarker = function(index, house) {
	var letter = String.fromCharCode("A".charCodeAt(0) + index);
	var icon = new GIcon(baseIcon);
	icon.image = "http://ditu.google.com/mapfiles/marker" + letter + ".png";
	
	var marker = new GMarker(new GLatLng(house.latitude, house.longitude), {
		draggable: true,
		icon: icon
		});
	GEvent.addListener(marker, "click", function() {
		openInfoOfHouse(house.id);
	});
	
	GEvent.addListener(marker, "dragstart", function() {
		mymap.map.closeInfoWindow();
	});
	
	GEvent.addListener(marker, "dragend", function() {
		var p = marker.getPoint();
		rentUtil.changePosition(house.id, p.lng(), p.lat());
	});

	this.map.addOverlay(marker);
	markerCache[house.id] = marker;
	houseCache[house.id] = house;
}

MyMap.prototype.removeMarker = function(id) {
	this.map.removeOverlay(markerCache[id]);
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
	if (marker) {
	
		var id = "_edit";
	
		dwr.util.cloneNode("tplEdit", { idSuffix: id });
		$("tplEdit" + id).style.display = "";
	
		marker.openInfoWindowHtml(tpl("tplEdit" + id));
		
		if (house) {
		
			dwr.util.setValue("address" + id, house.addressDetail);
			dwr.util.setValue("rooms" + id, house.rooms);
			dwr.util.setValue("price" + id, house.price);
			dwr.util.setValue("provider" + id, house.provider);
			dwr.util.setValue("phone" + id, house.phone);
			dwr.util.setValue("email" + id, house.email);
			
			dwr.util.setValue("houseid" + id, house.id);
		
		}
	}
}

function load() {
	mymap = new MyMap(lng, lat, zoom);
	rentUtil.getServerTime(function(serverTime) {
		timeDiff = new Date().getTime() - serverTime;
	});
	resizeApp();
	fetchMarkers();
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
									<a href="../?lng=${param.lng }&lat=${param.lat }&zoom=${param.zoom }">首页</a>
								</td>
							</tr>
							<tr>
								<td valign="bottom">
									<a href="../?lat=39.917&lng=116.397">北京</a> 
									<a href="../?lat=31.248&lng=121.473">上海</a> 
									<a href="../?lat=30.25&lng=120.167">杭州</a> 
									<a href="../?lat=22.5435&lng=114.1096">深圳</a> 
									<a href="../?lat=23.12&lng=113.25">广州</a> 
									<a href="../?lat=24.460&lng=118.079">厦门</a>
									<a>其它城市</a>
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
							<input type="button" value="发布出租信息" id="btnPublish" onclick="publishRent()"/>
							<h3><span id="findingHouse">正在查找发布的信息...</span></h3>
							<span id="pageNav"></span>
							<table>
								<tbody id="houseList">
									<tr id="pattern" style="display:none;">
										<td valign="top">
											<a href="#" id="idIcon" onclick="openInfoOfHouse(this.id.substring(6)); return false;"><img border="0"/></a>
										</td>
										<td valign="top">
											<strong><a href="javascript:;" id="idAddress" onclick="openInfoOfHouse(this.id.substring(9)); return false;">address</a></strong>
											<a href="javascript:;" id="delete" onclick="deleteHouse(this.id.substring(6)); return false;" title="删除">[x]</a><br/>
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

<!-- 页面模板 -->
    <div id='tplEdit' style="display: none;">
    	<strong><span id="editTitle">修改出租信息</span></strong><br/><br/>
    	<table>
    		<tr>
    			<td>地址:</td>
    			<td><input type='text' name='address' id='address' size='20' maxlength='20' /></td>
    		</tr>
    		<tr>
    			<td>居室:</td>
    			<td>
			    	<select name='rooms' id='rooms'>
			    		<option value='1'>一居</option>
			    		<option value='2'>两居</option>
			    		<option value='3'>三居</option>
			    		<option value='4'>四居</option>
			    		<option value='5'>五居</option>
			    		<option value='0'>更多</option>
			    	</select>
    			</td>
    		</tr>
    		<tr>
    			<td>租金:</td>
    			<td><input type='text' name='price' id='price' size='3' maxlength='6' />元/月</td>
    		</tr>
    		<tr>
    			<td>联系人:</td>
    			<td><input type='text' name='provider' id='provider' size='20' maxlength='12' /></td>
    		</tr>
    		<tr>
    			<td>电话:</td>
    			<td><input type='text' name='phone' id='phone' size='20' maxlength='20' /></td>
    		</tr>
    		<tr>
    			<td>Email:</td>
    			<td><input type='text' name='email' id='email' size='20' maxlength='80' /></td>
    		</tr>
    		<tr>
    			<td><input type="hidden" name="houseid" id="houseid"/></td>
    			<td>
    				<input type='button' value='确定' id='submitinfo' onclick='submitInfo()'/>
    				<input type="button" value="取消" onclick="mymap.map.closeInfoWindow()"/>
    			</td>
    		</tr>
    	</table>
    </div>
    
<div style="display: none">
	<div id="tplPublishHint">
		<strong>1.请拖拽此标记到出租房屋的实际位置。</strong>
	</div>
	   
    <div id="tplPublishing">
    	<strong>正在发布信息，请稍候……</strong>
    </div>
    
    <div id='tplPublished'>
    	<strong>信息发布成功！</strong>
    	<p/>
		需要添加照片或视频吗？<br/>
		<p/>
		<input type='button' value='添加照片' /> 
		<input type='button' value='添加视频' />
	</div>
</div>
</body>
</html>