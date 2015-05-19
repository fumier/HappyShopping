<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/easyui.css">
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
</head>
<body>
	<div id="dd" class="easyui-dialog" title="来自清风书苑的温馨提示" style="width:400px;height:200px;padding:10px"
			buttons="#dlg-buttons">
		<table>
		<tr>
		<td align="center">书籍已成功添加到购物车,欢迎继续选购~</td>
		<tr>
		</table>
	</div>
	<div id="dlg-buttons">
		<table cellpadding="0" cellspacing="0" style="width:100%">
			<tr>
				<td style="text-align:right">
					<a href="#" class="easyui-linkbutton" onclick="javascript:alert('save')">进入购物车</a>
					<a href="#" class="easyui-linkbutton" onclick="javascript:$('#dd').dialog('close')">继续购买</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>