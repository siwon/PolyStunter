<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="register" /></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="/PolyStunter/design/design.css" rel="stylesheet">
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
					<label class="control-label" for="login"><fmt:message key="login" /></label>
					<div class="controls">
						<input type="text" id="login" name="login" minlength="6" required  maxlength="10">
						<p class="help-block"><fmt:message key="minSize" /> : 6</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="password"><fmt:message key="password" /></label>
					<div class="controls">
						<input type="password" id="password" name="password" minlength="6" required >
						<p class="help-block"><fmt:message key="minSize" /> : 6</p>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="mail"><fmt:message key="mail" /></label>
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
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-success"><fmt:message key="register"/></button>
					</div>
				</div>
			</form>
			<% if(request.getAttribute("errors") != null) {
				out.print("<div class='alert alert-error'>"+ request.getAttribute("errors") + "</div>");
			}
			%>
			<hr>
			<%@include file="/WEB-INF/includes/footer.jsp" %>
		</div>
	</body>
</html>