<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="myBasket" /></title>
		
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
				<h1><fmt:message key="myBasket" /></h1>
			</div>
			
			<c:if test="${not empty basket}" >
				<table class="table table-hover">
					<thead>
						<th colspan="4" ><b><fmt:message key="selectedProducts" /></b></th>
						<th><b><fmt:message key="unitPrice" /></b></th>
						<th><b><fmt:message key="quantity" /></b></th>
						<th></th>
					<thead>
					<%= request.getAttribute("basket") %>
					<tr>
						<th colspan="4" ></th>
						<th><b><fmt:message key="totalTTC" /></b></th>
						<th><b><fmt:formatNumber value="${basketCost}" minFractionDigits="2"/> &euro;</b></th>
						<th></th>
					</tr>
				</table>
				<div class="center">
						<a href="<c:url value="/basketAction?action=empty"/>"><button type="submit" class="btn btn-warning"><fmt:message key="empty" /></button></a>
						<a href="<c:url value="/basketAction?action=validate"/>"><button type="submit" class="btn btn-success"><fmt:message key="validate" /></button></a>
				</div>	
			</c:if>
			<c:if test="${empty basket}" >
				<div class="center">
					<p>
						<fmt:message key="emptyBasket" />.<br/>
						<a href="<c:url value="/products" />"><fmt:message key="startShopping" />.</a>
					</p>
				</div>
			</c:if>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>