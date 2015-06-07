<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.bstek.com/dorado" prefix="d"%>
<html>
<head>
<title></title>
</head>
<body>
	<d:View config="com.cyf.demo.dorado.controller.demo">
		<d:Layout type="vflow">
			<d:Pane>
				<d:AutoForm id="form1" />
			</d:Pane>
			<d:Pane>
				<d:Layout type="Hflow">
					<d:Pane width="100%">
					</d:Pane>
					<d:Pane>
						<d:Button id="button1" />
					</d:Pane>
				</d:Layout>
			</d:Pane>
			<d:Pane>
				<d:DataTable id="table1" />
			</d:Pane>
			<d:Pane>
				<d:DataPilot id="datapilotDsUser" />
			</d:Pane>
		</d:Layout>
	</d:View>
</body>
</html>
