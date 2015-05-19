<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
function load()
{
		var url=document.URL;
		url=url.split("happyshopping.jsp");
	   // window.location.href=url[0]+"/ListBookServlet";
		window.location.href=url[0]+"PageServlet";	
}

</script>
</head>
<body onload="load();">
</body>
</html>