<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title>Polystunter</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		
		<style type="text/css">
		body {
			padding-top: 60px;
			padding-bottom: 40px;
		}
		</style>
		
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		
	</head>

	<body>
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
	
			<div class="hero-unit">
				<img src="/PolyStunter/images/logo_Large.png" width="500px" />
				<fmt:message key="home.welcome" />
				<button class="btn btn-large btn-primary" type="button">Commencer maintenant !</button>
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
			<%@include file="/WEB-INF/includes/footer.jsp" %>
		</div>
	</body>
</html>