<%@page import="org.jfree.chart.axis.DateTickMarkPosition"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<p>
			<a href="${rootPath }/demo/showChart.do?type=ZZT1" target="showframe">柱状图1</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=ZZT2" target="showframe">柱状图2</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=ZZT3" target="showframe">柱状图3</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=ZZT4" target="showframe">柱状图4</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		<p>
			<a href="${rootPath }/demo/showChart.do?type=BZT1" target="showframe">饼状图1</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=BZT2" target="showframe">饼状图2</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		<p>
			<a href="${rootPath }/demo/showChart.do?type=QXT1" target="showframe">曲线图1</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=QXT2" target="showframe">曲线图2</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="${rootPath }/demo/showChart.do?type=QXT3" target="showframe">曲线图3</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
	</div>
	<iframe name="showframe" width="100%" height="450px"></iframe>
</body>
</html>