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
<script>
var roomDesc = ["", "一居 ", "两居 ", "三居 ", "四居 ", "五居 ", "多居 "];
var timeDiff = 0;

function load() {
	rentUtil.findUserHouses('', 1, 100, function(pageObj) {
		var houses = pageObj.pageElements;
		for (var i in houses) {
		    var index = parseInt(i);
			var house = houses[i];
			var id = house.id;
			
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
</script>
</head>
<body onload="load()">

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

</body>
</html>