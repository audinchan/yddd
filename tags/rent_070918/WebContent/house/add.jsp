<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>发布出租信息</title>
<script src='../dwr/engine.js'></script>
<script src='../dwr/util.js'></script>
<script src='../dwr/interface/rentUtil.js'></script>
<script src='../js/func.js'></script>
<script
	src="http://ditu.google.com/maps?file=api&amp;v=2&amp;key=ABQIAAAAHGYqsQNmu-Zmlz7HIyBjBxTkC2hDZ_uphd3RfMN_0RVjLMwHphSBNw9kDe7ld4vZEiwsMbVQ9VzG2A"
	type="text/javascript"></script>
<script type="text/javascript">

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

    //<![CDATA[
    
    var lng = ${param.lng};
    var lat = ${param.lat};
    var zoom = ${param.zoom};
    var lastId = "";
    
    var info1 = "";
	var info2 = "";
	var info3 = "";
	var infoPublishing = "";
				
	var info = info1;
	var isMoved = false;				
    
    function submitInfo() {
    	info = infoPublishing;
    	
    	rentUtil.saveHouse(
    		{
    			longitude: lng,
    			latitude: lat,
    			addressDetail: $('address').value,
    			rooms: $("rooms").value,
    			price: $("price").value,
    			provider: $("provider").value,
    			phone: $("phone").value,
    			email: $("email").value, 
    		},
    		function(id) {
    			lastId = id;
				info = info3;
				map.closeInfoWindow();
    			marker.openInfoWindowHtml(info);
    		}
    	);
    	
    	map.closeInfoWindow();
    	marker.openInfoWindowHtml(info);
    	
		
    }
    
    var map;
    var marker;

    function load() {
    	info1 = tpl('step1Tpl');
    	info2 = tpl('step2Tpl');
    	info3 = tpl('step3Tpl');
    	infoPublishing = tpl('publishingTpl');
    	info = info1;
    	
      if (GBrowserIsCompatible()) {
        map = new GMap2(document.getElementById("map"));
        map.addControl(new GLargeMapControl ());
		map.addControl(new GScaleControl());
		map.addControl(new GOverviewMapControl());
        map.setCenter(new GLatLng(lat, lng), zoom);
        
        
		// 将标注置于地图中央，并自动打开气泡提示窗口
		marker = new GMarker(map.getCenter(), {draggable: true});
		GEvent.addListener(marker, "click", function() {
		  marker.openInfoWindowHtml(info);
		});

		GEvent.addListener(marker, "dragstart", function() {
		  map.closeInfoWindow();
		  });
		
		GEvent.addListener(marker, "dragend", function() {
		  if (!isMoved) {
		  	info = info2;
		  	isMoved = true;
		  }
		  var p = marker.getPoint();
		  lng = p.lng();
		  lat = p.lat();
		  marker.openInfoWindowHtml(info);
		  
		  // 自动保存新位置到数据库
		  if (lastId != "") {
		  	rentUtil.changePosition(lastId, lng, lat);
		  }
		  
		  });
		  
		GEvent.addListener(map, "zoomend", function() {
				resetMarkerPos(map, marker);
			});
		
		GEvent.addListener(map, "moveend", function() {
				resetMarkerPos(map, marker);
			});

		map.addOverlay(marker);
		marker.openInfoWindowHtml(info);
      }
    }
    
function resetMarkerPos(map, marker) {
	if (!map.getBounds().contains(marker.getPoint())) {
		marker.setPoint(map.getCenter());
		marker.openInfoWindowHtml(info);
	}
}

/**
 * 发布新信息
 */
function addNew() {
	d1('pubHelp');
}

    //]]>
    </script>
</head>
<body onload="load()" onunload="GUnload()">
<table>
	<tr>
		<td valign="top">
			<div style="width: 200px">
				<span><a href="javascript: addNew()">发布出租信息</a></span>
				<div id="pubHelp" style="display: none">
					<h3><span id="findingHouse">出租房屋只需简单两步</span></h3>
					
					<div>
						<span style="font-size: 18pt; font-weight: bold">1. </span>将右侧地图中的标记拖到合适的位置。
						<p/>
						<span style="font-size: 18pt; font-weight: bold">2. </span>填写出租房的相关信息。
						<p/>
						<span style="color: gray">
							注： 准确的位置和说明有助于求租者更快的找到您的信息。
						</span>
					</div>
				</div>
			</div>
		</td>
		
		<td><div id="map" style="width: 600px; height: 500px"></div></td>
	</tr>
</table>
<div style="display: none">
	<div id="step1Tpl">
		<strong>1.请拖拽此标记到出租房屋的实际位置。</strong>
	</div>
	
    <div id='step2Tpl'>
    	<strong>2.请输入出租房屋的信息</strong><br/><br/>
    	地址:<input type='text' name='address' id='address' size='20' maxlength='20' /><br/>
    	居室:<select name='rooms' id='rooms'>
    		<option value='1'>一居</option>
    		<option value='2'>两居</option>
    		<option value='3'>三居</option>
    		<option value='4'>四居</option>
    		<option value='5'>五居</option>
    		<option value='0'>更多</option>
    	</select><br/>
    	租金:<input type='text' name='price' id='price' size='3' maxlength='6' />元/月<br />
    	联系人:<input type='text' name='provider' id='provider' size='8' maxlength='12' /><br/>
    	电话:<input type='text' name='phone' id='phone' size='12' maxlength='20' /><br/>
    	Email:<input type='text' name='email' id='email' size='12' maxlength='80' /><br />
    	<input type='button' value='确定' onclick='submitInfo()'/>
    </div>
    
    <div id="publishingTpl">
    	<strong>正在发布信息，请稍候……</strong>
    </div>
    
    <div id='step3Tpl'>
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