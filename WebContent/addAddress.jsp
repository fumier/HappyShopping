<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/addAddress.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
<fieldset id="city_china_val">
  <legend>添加新地址</legend>
	<table>
	 <tr>
	   <td>姓名:</td>
	   <td><input type="text" maxlength="10"></td>
	 </tr>
	 <tr>
	   <td>地址:</td>
	   <td>
		<select class="province" data-value="辽宁省" data-first-title="选择省" disabled="disabled"></select>
		<select class="city" data-value="沈阳市" data-first-title="选择市" disabled="disabled"></select>
		<select class="area" data-value="东陵区" data-first-title="选择地区" disabled="disabled"></select>
	   </td>
	 <tr>
		<td></td>
		<td><input type="text" maxlength="50" size="40"></td>
	 </tr>
	 <tr>
		<td>邮编:</td>
		<td><input type="text" maxlength="6"></td>
	 </tr>
	 <tr>
		<td>联系电话:</td>
		<td><input type="text" maxlength="12"></td>
	 </tr>
	 <tr>
	    <td></td>
	    <td>
	    <input type="radio" name="setDefaultAddress">设置为默认地址
	    </td>
	 </tr>
   </table>
</fieldset>
</div>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script src="js/jquery.cxselect.js"></script>
<script>
$.cxSelect.defaults.url = 'js/cityData.json';
$('#city_china_val').cxSelect({
	selects: ['province', 'city', 'area'],
	nodata: 'none'
});
</script>
</body>
</html>