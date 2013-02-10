<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<%@page import="dao.WarehouseDAO"  %>
<%@page import="beans.Warehouse"  %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="myDelivery" /></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="/PolyStunter/design/design.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		
	</head>

	<body>
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
			<div class="page-header">
				<h1><fmt:message key="myDelivery" /></h1>
			</div>
			<c:if test="${not empty orders}">
				<table class="table table-striped">
					<tr>
						<th><fmt:message key="orderFrom"/></th>
						<th><fmt:message key="forwardingAddress"/></th>
						<th><fmt:message key="productsToDeliver"/></th>
						<th><fmt:message key="orderCost"/></th>
						<th></th>
					</tr>
					<c:forEach var="o" items="${orders}">
						<tr>
							<td>${o.date}</td>
							<td>${o.forwardingAddress}</td>
							<td>
								<ul>
									<c:forEach var="e" items="${o.productsOrdered.entrySet()}">
										<li>Ref. ${e.getKey().reference} <b>x</b> ${e.getValue()} --->
										${WarehouseDAO.getInstance().getWarehouseById(e.getKey().warehouse).street}
										</li>
									</c:forEach>
								</ul>
							</td>
							<td><fmt:formatNumber value="${o.cost}" minFractionDigits="2"/> &euro;</td>
							<td>
								<c:choose>
									<c:when test="${o.isReady()}">						
										<button class="btn btn-success"><fmt:message key="deliver"/></button>
									</c:when>
									<c:otherwise>
										<button class="btn" disabled><fmt:message key="delivered"/></button>
									</c:otherwise>
								</c:choose>
							</td>
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty orders}">
				<div class="center">
					<fmt:message key="noDelivery"/>
				</div>
			</c:if>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>