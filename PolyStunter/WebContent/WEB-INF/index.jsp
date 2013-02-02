<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<fmt:setLocale value="fr" scope="application"/>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title>Polystunter</title>
		
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
		
			<c:if test="${not empty message}">
				<c:if test="${not empty message.getErrorMessages()}">
					<div class='alert alert-error'>
						${message.getErrorMessages().toString()}
					</div>
				</c:if>
				<c:if test="${not empty message.getSuccessMessages()}">
					<div class='alert alert-success'>
						${message.getSuccessMessages().toString()}
					</div>
				</c:if>
			</c:if>
			
			<div class="hero-unit">
				<div style="text-align:center">
					<img src="/PolyStunter/images/logo_Large.png" width="500px" />
					<fmt:message key="home.welcome" />
					<c:choose>
						<c:when test="${user.isCustomer()}">
							<a class="btn btn-large btn-info" href="<c:url value="/products" />"><fmt:message key="startShopping" /> !</a>
						</c:when>
						<c:when test="${user.isSeller()}">
							<a class="btn btn-large btn-info" href="<c:url value="/store" />"><fmt:message key="manageMyStore" /> !</a>
						</c:when>
						<c:when test="${user.isDeliveryMan()}">
							<a class="btn btn-large btn-info" href="<c:url value="#" />"><fmt:message key="manageMyDeliveries" /> !</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-large btn-info" href="<c:url value="/register" />"><fmt:message key="register" /> !</a>
						</c:otherwise>
					</c:choose>
					
					
				</div>
			</div>
	
			<div class="row">
				<div class="span4">
					<h2>
						<fmt:message key="customer" />
					</h2>
					<p>
						<fmt:message key="home.presentation.customer" />
					</p>
				</div>
				<div class="span4">
					<h2>
						<fmt:message key="deliveryMan" />
					</h2>
					<p>
						<fmt:message key="home.presentation.deliveryMan" />
					</p>
				</div>
				<div class="span4">
					<h2>
						<fmt:message key="seller" />
					</h2>
					<p>
						<fmt:message key="home.presentation.seller" />
					</p>
				</div>
			</div>
		<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>