<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/table/jquery.dataTables.css">
<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="../js/table/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready( function () {
	var t= $('#addressTable').DataTable();
	var counter = 1;
	 
    $('#addRow').on( 'click', function () {
        t.row.add( [
            counter +'.1',
            counter +'.2',
            counter +'.3',
            counter +'.4',
            counter +'.5'
        ] ).draw();
        counter++;
    } );
} );

</script>
</head>
<body>
<input  id="addRow" type="button" value="add new row">
<div>
	<table id="addressTable" class="displayTable">
	<thead>
	<tr>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	<th></th>
	</tr>
	</thead>
		<c:set var="i" value="0" />
		<c:forEach var="info" items="${infoList}">
			<c:set var="defaultAddress" value="${info.defaultAddress}" />
			<c:set var="i" value="${i+1}" />
			<c:if test="${i<6}">
				<c:if test="${i==1}">
				<tbody>
					<tr>
						<td><input type="radio"
							name="addressradio" checked="checked"></td>
						<td>${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
						<td>${info.personName}</td>
						<td>${info.telephone}</td>
						<td><span><a href="#"
								class="updateAddress">修改</a>&nbsp; <c:if
									test="${defaultAddress==false}">
									<a href="#" class="setDefaultAddress">设为默认地址</a>
								</c:if></span></td>
					</tr>
				</c:if>
				<c:if test="${i!=1}">
					<tr>
						<td><input type="radio"
							name="addressradio"></td>
						<td> ${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
						<td>${info.personName}</td>
						<td>${info.telephone}</td>
						<td><span><a href="#"
								class="updateAddress">修改</a>&nbsp; <c:if
									test="${defaultAddress==false}">
									<a href="#" class="setDefaultAddress">设为默认地址</a>
								</c:if></span></td>
					</tr>
				</c:if>
			</c:if>
			<c:if test="${i>=6}">
				<tr style="display: none;" class="changeTr">
					<td><input type="radio"
						name="addressradio"></td>
					<td>${info.address.province},${info.address.city},${info.address.area},${info.address.details},${info.address.zipcode}</td>
					<td>${info.personName}</td>
					<td>${info.telephone}</td>
					<td><span><a href="#"
							class="updateAddress">修改</a>&nbsp; <c:if
								test="${defaultAddress==false}">
								<a href="#" class="setDefaultAddress">设为默认地址</a>
							</c:if></span></td>
				</tr>
			</c:if>
		</c:forEach>
		</tbody>
	</table>
	</div>
</body>
</html>