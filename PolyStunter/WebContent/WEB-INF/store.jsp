<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="myStore" /></title>
		
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
				<h1><fmt:message key="myStore" /></h1>
			</div>
			
			<div class="form-actions center">
				<div class="controls">
					<a href="<c:url value="/addProduct"/>"><button type="submit" class="btn btn-info"><fmt:message key="addAProduct" /></button></a>
				</div>
			</div>
			
			<ul class="thumbnails market">
				<%= request.getAttribute("products") %>
			</ul>
			
			<div class="form-actions center">
				<div class="controls">
					<a href="<c:url value="/addProduct"/>"><button type="submit" class="btn btn-info"><fmt:message key="addAProduct" /></button></a>
				</div>
			</div>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>