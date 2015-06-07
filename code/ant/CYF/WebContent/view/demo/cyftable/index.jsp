<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${rootPath }/resources/js/cyftable/cyfTable-1.0.js"></script>
<script type="text/javascript" src="${rootPath }/resources/js/jquery/jquery-1.8.3.min.js"></script>
<link rel="stylesheet" href="${rootPath }/resources/js/cyftable/themes/cyfTable-1.0.css">
</head>
<body>
<div id="test1" style="width:100%"></div>
<div id="test2" style="width:100%"></div>
</body>
<script type="text/javascript">
var list = [
    {id:"name",name:"姓名",width:100,heigth:20},
    {id:"age",name:"年龄",width:100,heigth:20},
    {id:"sex",name:"性别",width:100,heigth:20},
    {id:"sex1",name:"性别",width:100,heigth:20},
    {id:"sex2",name:"性别",width:100,heigth:20},
    {id:"sex3",name:"性别",width:100,heigth:20},
    {id:"sex4",name:"性别",width:100,heigth:20},
    {id:"sex5",name:"性别1",width:100,heigth:20}
            ];
var dataSet = [
               {name:"测试1",age:"1",sex:"2"},
               {name:"测试2",age:"2",sex:"3"},
               {name:"测试3",age:"3",sex:"4"}
                       ];
var dataSet1 = [
            {name:"测试1",age:"1",sex:"2"},
            {name:"测试2",age:"2",sex:"3"}
                    ];
var cyfTable1 = null;
	cyfTable1 = new cyf_Table("test1",list);
	cyfTable1.initList({
		showRowNum : true,
		showPage : true,
		height : 300,
		url : "${rootPath}/demo/table.do",
		pageSize : 10,
		autowidth:true,
		pageAlign:"center"
	});
cyfTable1.loadData();


// var cyfTable2 = null;
// cyfTable2 = new cyf_Table("test2",list);
// cyfTable2.initList({
// 	showRowNum : true,
// 	showPage : true,
// 	height : 300,
// 	url : "${rootPath}/demo/table.do",
// 	pageSize : 10,
// 	autowidth:true
// });
// cyfTable2.loadData();
</script>
</html>