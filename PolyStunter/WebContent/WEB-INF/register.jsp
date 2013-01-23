<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="register" /></title>
		
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
		
		<script src="/PolyStunter/bootstrap/js/jquery.js"></script>
		<script src="/PolyStunter/bootstrap/js/jqBootstrapValidation.js"></script>
		
	</head>

	<body>
	
		<script>
			$(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
		</script>	
		
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
		
			<div class="page-header">
				<h1><fmt:message key="register" /></h1>
			</div>
			
			<form class="form-horizontal" id="registerForm" action="<c:url value="/register" />" method="POST">
			
				<div class="control-group">
					<label class="control-label" for="login">Login</label>
					<div class="controls">
						<input type="text" id="login" required >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password">Password</label>
					<div class="controls">
						<input type="password" id="password" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="mail">Email</label>
					<div class="controls">
						<input type="email" id="mail" name="mail" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="status"><fmt:message key="status" /></label>
					<div class="controls">
						<label class="radio">
							<input type="radio" name="status" id="status" value="CUSTOMER" checked>
							<fmt:message key="customer" />
						</label>
						<label class="radio">
							<input type="radio" name="status" id="status" value="SELLER">
							<fmt:message key="seller" />
						</label>
						<label class="radio">
							<input type="radio" name="status" id="status" value="DELIVERYMAN">
							<fmt:message key="deliveryMan" />
						</label>
					</div>
				</div>
				<div class="form-actions">
					<button type="submit" class="btn"><fmt:message key="register"/></button>
				</div>
			</form>		
			<hr>
			<%@include file="/WEB-INF/includes/footer.jsp" %>
		</div>
	</body>
</html>