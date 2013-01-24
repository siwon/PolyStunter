<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="connect" /></title>
		
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
				<h1><fmt:message key="connect" /></h1>
			</div>
			
			<form class="form-horizontal" id="connectForm" action="<c:url value="/login" />" method="POST">
			
				<div class="control-group">
					<label class="control-label" for="login"><fmt:message key="login" /></label>
					<div class="controls">
						<input type="text" id="login" name="login" placeholder="<fmt:message key="login"/>" required>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label" for="password"><fmt:message key="password" /></label>
					<div class="controls">
						<input type="password" id="password" name="password" placeholder="<fmt:message key="password" />" required>
					</div>
				</div>
				
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-success"><fmt:message key="connect"/></button>
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