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
		
			<% if(request.getAttribute("errors") != null) {
				out.print("<div class='alert alert-error'>"+ request.getAttribute("errors") + "</div>");
			}
			%>
			<% if(request.getAttribute("successMessage") != null) {
				out.print("<div class='alert alert-success'>"+ request.getAttribute("successMessage") + "</div>");
			}
			%>		
			<div class="hero-unit">
				<div style="text-align:center">
					<img src="/PolyStunter/images/logo_Large.png" width="500px" />
					<fmt:message key="home.welcome" />
					<a class="btn btn-large btn-primary" href="<c:url value="/products" />"><fmt:message key="startShopping" /> !</a>
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