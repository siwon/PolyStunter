<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<fmt:setLocale value="fr" scope="application"/>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="updateWarehouse"/></title>
		
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
				<h1><fmt:message key="updateWarehouse" /></h1>
			</div>
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
			<form class="form-horizontal" id="updateWarehouse" name="updateWarehouse" action="<c:url value="/updateWarehouse" />" method="POST">
				<div class="control-group">
					<div class="controls">
						<input type="text" id="warehouseName" name="warehouseName" value="${warehouse.name}" placeholder="<fmt:message key="warehouseName" />" required>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input type="text" id="street" name="street" value="${warehouse.street}" placeholder="<fmt:message key="street" />" required>
					</div>
				</div>
				<div class="control-group">
					<div class="controls controls-row">
						<input class="span1 input-mini" type="text" maxlength="5" pattern="[0-9]{5}"  value="${warehouse.zipCode}" id="zipCode" name="zipCode" placeholder="<fmt:message key="zipCode" />" required>
						<input class="span2" type="text" id="city" name="city" value="${warehouse.city}" placeholder="<fmt:message key="city" />" required>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input type="hidden" name="id" value="${warehouse.id}" />
						<a href="<c:url value="/warehouse"/>"><button type="button" class="btn" ><fmt:message key="cancel"/></button></a>
						<button type="submit" class="btn btn-info"><fmt:message key="save"/></button>
					</div>
				</div>
			</form>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>