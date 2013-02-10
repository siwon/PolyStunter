<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="orderSummary" /></title>
		
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
				<h1><fmt:message key="orderSummary" /></h1>
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
			
			<c:if test="${not empty basket}" >
				<table class="table table-hover">
					<thead>
						<th colspan="3" ><b><fmt:message key="selectedProducts" /></b></th>
						<th><b><fmt:message key="unitPrice" /></b></th>
						<th><b><fmt:message key="quantity" /></b></th>
					<thead>
					<c:forEach var="e" items="${basket}">
						<tr>
							<td>
								<c:choose>
									<c:when test="${e.key.name.length() > 20}">
										${e.key.name.substring(0,20)} ...
									</c:when>
									<c:otherwise>
										${e.key.name}
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<span>
									Ref. ${e.key.reference}
								</span>
							</td>
							<td>
								<c:choose>
									<c:when test="${e.key.inStock()}">
										<span class='green'><fmt:message key="inStock"/></span>
									</c:when>
									<c:otherwise>
										<span class='red'><fmt:message key="outOfStock"/></span>
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<fmt:formatNumber value="${e.key.price}" minFractionDigits="2"/> &euro;
							</td>
							<td>
								${e.value}
							</td>
						</tr>					
					</c:forEach>
					<tr>
						<th colspan="3" ></th>
						<th><b><fmt:message key="totalTTC" /></b></th>
						<th><b><fmt:formatNumber value="${basketCost}" minFractionDigits="2"/> &euro;</b></th>
					</tr>
				</table>
						
				<div class="row">
					<form class="center" name="forwardingAddress" action="<c:url value="/basketValidate" />" method="POST">
						<div class="span4">
							<h4><fmt:message key="forwardingAddress"/> <a href="#myModal" role="button" class="btn btn-link" data-toggle="modal"><fmt:message key="update" /></a></h4>
							${street} <br/>
							<input name="street" type="hidden" value="${street}"/>
							${zipCode}
							<input name="zipCode" type="hidden" value="${zipCode}"/>
							${city}
							<input name="city" type="hidden" value="${city}"/>
						</div>
						<div class="span4">
							<div class="center">
								<a href="<c:url value="/basket"/>"><button type="button" class="btn"><fmt:message key="back" /></button></a>
								<button type="submit" class="btn btn-success" ><fmt:message key="confirm" /></button>
							</div>
						</div>
					</form>	
				</div>
				
				
				<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<%@include file="modalForwardingAddress.jsp"%>
			</div>
			</c:if>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>