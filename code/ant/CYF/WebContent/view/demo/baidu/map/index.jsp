<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=1znDZF1yjMgFeKiPNKG8ZAFs"></script>
<title>CYF Map</title>
<style type="text/css">
body,html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
	font-size: 14px;
	text-align: center;
}

#address {
	height: 30px;
	width: 400px;
}
</style>
<script type="text/javascript">
	var map;
	function query() {
		var address = document.getElementById("address").value;
		var v_Obj = document.frames("showMap");
	}

	function G(id) {
		return document.getElementById(id);
	}

	function init() {
		map = document.getElementById('showMap').contentWindow.map;
		var ac = new BMap.Autocomplete( //建立一个自动完成的对象
		{
			"input" : "address",
			"location" : map
		});
		ac.addEventListener("onhighlight", function(e) { //鼠标放在下拉列表上的事件
			var str = "";
			var _value = e.fromitem.value;
			var value = "";
			if (e.fromitem.index > -1) {
				value = _value.province + _value.city + _value.district
						+ _value.street + _value.business;
			}
			str = "FromItem<br />index = " + e.fromitem.index
					+ "<br />value = " + value;

			value = "";
			if (e.toitem.index > -1) {
				_value = e.toitem.value;
				value = _value.province + _value.city + _value.district
						+ _value.street + _value.business;
			}
			str += "<br />ToItem<br />index = " + e.toitem.index
					+ "<br />value = " + value;
			G("searchResultPanel").innerHTML = str;
		});

		ac.addEventListener("onconfirm", function(e) { //鼠标点击下拉列表后的事件
			var _value = e.item.value;
			var myValue = _value.province + _value.city + _value.district
					+ _value.street + _value.business;
			G("searchResultPanel").innerHTML = "onconfirm<br />index = "
					+ e.item.index + "<br />myValue = " + myValue;
			document.getElementById('showMap').contentWindow.setPlace(myValue);
		});
		addPosition();
	}
	//------------------------------------------------------------------------	
	var geolocationControl = new BMap.GeolocationControl();
	geolocationControl.addEventListener("locationSuccess", function(e) {
		// 定位成功事件
// 		var address = '';
// 		address += e.addressComponent.province;
// 		address += e.addressComponent.city;
// 		address += e.addressComponent.district;
// 		address += e.addressComponent.street;
// 		address += e.addressComponent.streetNumber;
		//	 	    alert("当前定位地址为：" + address);
		var p = new BMap.Point(e.point.lat, e.point.lng);
		document.getElementById('showMap').contentWindow.centerAndZoom1(p, 18);
		
	});
	geolocationControl.addEventListener("locationError", function(e) {
		// 定位失败事件
		alert(e.message);
	});
	// 添加定位控件
	function addPosition() {
		map.addControl(geolocationControl);
	}
	
	function removePosition() {
		map.removeControl(geolocationControl);
	}
	//------------------------------------------------------------------------
	var top_left_control = new BMap.ScaleControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});// 左上角，添加比例尺
	var top_left_navigation = new BMap.NavigationControl({
		// 靠左上角位置
		anchor : BMAP_ANCHOR_TOP_LEFT,
		// LARGE类型
		type : BMAP_NAVIGATION_CONTROL_LARGE,
		// 启用显示定位
		enableGeolocation : true
	}); //左上角，添加默认缩放平移控件
	var top_right_navigation = new BMap.NavigationControl({
		anchor : BMAP_ANCHOR_TOP_RIGHT,
		type : BMAP_NAVIGATION_CONTROL_SMALL
	}); //右上角，仅包含平移和缩放按钮
	/*缩放控件type有四种类型:
	BMAP_NAVIGATION_CONTROL_SMALL：仅包含平移和缩放按钮；BMAP_NAVIGATION_CONTROL_PAN:仅包含平移按钮；BMAP_NAVIGATION_CONTROL_ZOOM：仅包含缩放按钮*/

	//添加控件和比例尺
	function add_control() {
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);
		map.addControl(top_right_navigation);
	}
	//移除控件和比例尺
	function delete_control() {
		map.removeControl(top_left_control);
		map.removeControl(top_left_navigation);
		map.removeControl(top_right_navigation);
	}

	//------------------------------------------------------------------------
	var mapType1 = new BMap.MapTypeControl({
		mapTypes : [ BMAP_NORMAL_MAP, BMAP_HYBRID_MAP ]
	});
	var mapType2 = new BMap.MapTypeControl({
		anchor : BMAP_ANCHOR_TOP_LEFT
	});

	var overView = new BMap.OverviewMapControl();
	var overViewOpen = new BMap.OverviewMapControl({
		isOpen : true,
		anchor : BMAP_ANCHOR_BOTTOM_RIGHT
	});
	//添加地图类型和缩略图
	function add_control1() {
		map.addControl(mapType1); //2D图，卫星图
		map.addControl(mapType2); //左上角，默认地图控件
		map.setCurrentCity("北京"); //由于有3D图，需要设置城市哦
		map.addControl(overView); //添加默认缩略地图控件
		map.addControl(overViewOpen); //右下角，打开
	}
	//移除地图类型和缩略图
	function delete_control1() {
		map.removeControl(mapType1); //移除2D图，卫星图
		map.removeControl(mapType2);
		map.removeControl(overView);
		map.removeControl(overViewOpen);
	}
</script>
</head>
<body onload="init()">
	<div style="text-align: center; padding-top: 10px;">
		<div style="text-align: center;">
			<input type="text" id="address" />
		</div>
	</div>
	<div style="text-align: center; padding-top: 10px;">
		<input type="button" value="添加比例尺" onclick="add_control();" /> <input
			type="button" value="删除比例尺" onclick="delete_control();" /> <input
			type="button" value="添加定位" onclick="addPosition();" /> <input
			type="button" value="移除定位" onclick="removePosition();" /> <input
			type="button" value="添加地图类型和缩略图" onclick="add_control1();" /> <input
			type="button" value="移除地图类型和缩略图" onclick="delete_control1();" />
	</div>
	<div id="searchResultPanel" style="display: none;"></div>
	<iframe id="showMap" name="showMap" scrolling="no" src="showMap.jsp"
		width="90%" height="450px" style="margin-top: 10px"></iframe>
</body>
</html>
