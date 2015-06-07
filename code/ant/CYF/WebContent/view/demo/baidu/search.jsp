<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${rootPath }/resources/js/jquery/jquery-1.8.3.min.js"></script>
<style type="text/css">
.button{
	height: 30px;
	width: 100px;
}
.select{
	width: 100px;
	height: 30px;
}
.text{
	width: 300px;
	height: 25px;
}
</style>
</head>
<body>
<!-- <table width="100%"> -->
<!-- 	<tr> -->
<!-- 		<td align="right"><label for="telSearch">手机号查询：</label></td> -->
<!-- 		<td width="300px"><input type="text" class="input" id="telSearch" /></td> -->
<!-- 		<td align="left"><input type="button" value="查询" onclick="search(1);" /></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td align="right"><label for="idSearch">身份证查询：</label></td> -->
<!-- 		<td width="300px"><input type="text" class="input" id="idSearch" /></td> -->
<!-- 		<td align="left"><input type="button" value="查询" onclick="search(2);" /></td> -->
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td width="40%" align="right"><label for="ipSearch">IP查询：</label></td> -->
<!-- 		<td width="300px"><input type="text" class="input" id="ipSearch" /></td> -->
<!-- 		<td align="left"><input type="button" value="查询" onclick="search(3);" /></td> -->
<!-- 	</tr> -->
<!-- </table> -->
<center>
	<select id="type" class="select">
		<option value="1" selected="selected">手机号查询</option>
		<option value="2">身份证查询</option>
		<option value="3">身份证泄露查询</option>
		<option value="4">身份证挂失查询</option>
		<option value="5">IP查询</option>
	</select>
	<input type="text" class="text" id="id" />
	<input type="button" class="button" value="查询" onclick="search();" />
</center>
<div id="show"></div>
</body>
<script type="text/javascript">
	function search() {
		var type=$("#type").val();
		var id = $("#id").val();
		var url = null;
		var sendData = null;
		if(type == "1"){
			url="http://apistore.baidu.com/microservice/mobilephone";
			sendData = {tel:id};
		}else if(type == "2"){
			url="http://apistore.baidu.com/microservice/icardinfo";
			sendData = {id:id};
		}else if(type == "3"){
			url="http://apistore.baidu.com/microservice/icardleak";
			sendData = {id:id};
		}else if(type == "4"){
			url="http://apistore.baidu.com/microservice/icardloss";
			sendData = {id:id};
		}else if(type == "5"){
			url="http://apistore.baidu.com/microservice/iplookup";
			sendData = {ip:id};
		}
// 		alert(url);
		$.ajax({
			async : false,
			type : "get",
			url : url,
			data : sendData,
			dataType : 'json',
			success : function(data) {
				showData(data.retData);
			}
		});
// 		alert("--------------");
	}
	
	function showData(type,data){
		var text = "";
		if(type == "1"){
			text = "省份:"+data.province+"\n运营商:"+data.carrier+"\n手机号码:"+data.telString;
		}else if(type == "2"){
			text = "性别:"+data.sex+"\n年龄:"+data.age+"\n地址:"+data.address;
		}else if(type == "3"){
			text = "状态:"+data.message;
		}else if(type == "4"){
			text = "状态:"+data.message;
		}else if(type == "5"){
			text = "ip:"+data.ip+"\n国家:"+data.country+"\n地区:"+data.area+"\n省:"+data.province+"\n市:"+data.city+"\n区:"+data.district+"\n运营商:"+data.carrier;
		}
		document.getElementById("show").innerHTML = text;
	}
</script>
</html>