<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<fmt:setLocale value="fr" scope="application"/>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="myWarehouses"/></title>
		
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
				<h1><fmt:message key="myWarehouses" /></h1>
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
			<div class="form-actions center">
				<div class="controls">
					<a href="#myModal" role="button" class="btn btn-info" data-toggle="modal"><fmt:message key="addWarehouse"/></a>
					<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<%@include file="modalAddWarehouse.jsp"%>
					</div>
				</div>
			</div>
			<c:if test="${not empty warehouses}" >
				<table class="table table-hover">
					<thead>
						<th><b><fmt:message key="warehouseName" /></b></th>
						<th><b><fmt:message key="warehouseAddress" /></b></th>
						<th></th>
					<thead>
					<c:forEach var="w" items="${warehouses}">
						<tr>
							<td>${w.name}</td>
							<td>${w.street} - ${w.zipCode} ${w.city}</td>
							<td>
								<a href="<c:url value="/removeWarehouse?id=${w.id}" />">
									<button type="button" class="btn btn-danger btn-mini" onclick="return confirm('<fmt:message key="messageConfirmation"/>')">
										<fmt:message key="remove"/>
									</button>
								</a>
								
								<a href="<c:url value="/updateWarehouse?id=${w.id}" />">
									<button type="button" class="btn btn-warning btn-mini">
										<fmt:message key="update"/>
									</button>
								</a>
						</tr>					
					</c:forEach>
				</table>
			</c:if>
			<c:if test="${empty warehouses}" >
				<div class="center">
					<p>
						<fmt:message key="emptyWarehouse" /><br/>
					</p>
				</div>
			</c:if>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>