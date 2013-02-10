<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

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
				<c:forEach var="o" items="${orders}">
					<b><fmt:message key="orderFrom"/></b> ${o.dateOrder}<br/>
					<i><fmt:message key="productsToDeliver"/></i>
					<c:forEach var="p" items="o.productsOrdered">
						Ref. 
					</c:forEach>
				
				</c:forEach>
			</c:if>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>