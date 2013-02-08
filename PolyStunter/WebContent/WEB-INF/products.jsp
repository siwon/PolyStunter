<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="products" /></title>
		
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
				<h1><fmt:message key="products" /></h1>
			</div>
				
			<ul class="thumbnails market">
				<c:forEach var="p" items="${products}">
					<li class="span3">
						<img src="/PolyStunter/products/${p.photo}" width="100px" height="100px"/><br/>
						<c:choose>
							<c:when test="${p.name.length()> 20}">
								${p.name.substring(0,20)}...
							</c:when>
							<c:otherwise>
							${p.name}
							</c:otherwise>
						</c:choose><br/>
						<b><fmt:message key="unitPrice"/> : </b> <fmt:formatNumber value="${p.price}" minFractionDigits="2"/> &euro;<br/>
						<c:choose>
							<c:when test="${p.inStock()}">
								<span class='green'>
									<fmt:message key="inStock"/>
								</span>
							</c:when>
							<c:otherwise>
								<span class='red'>
									<fmt:message key="outOfStock"/>
								</span>
							</c:otherwise>
						</c:choose>
						<br/>
						<a href="<c:url value="/productSheet?id=${p.id}" />" class='btn btn-info'>
							<fmt:message key="see"/>
						</a>
					</li>
				</c:forEach>
			</ul>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>