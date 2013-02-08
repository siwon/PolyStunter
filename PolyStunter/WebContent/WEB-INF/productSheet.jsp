<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<%@page import="beans.Product"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="productSheet" /></title>
		
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
				<h1><fmt:message key="productSheet" /></h1>
				<a href=<c:url value="/products"/>><fmt:message key="back" /></a>
			</div>
			
			<div class="row">
				<div class="span6">
					<p>
						<img src="/PolyStunter/products/${product.photo}" width="100px" height="100px" /><br/>
						<b>${product.name}</b><br/>
						<i>${product.reference}</i><br/>
						<fmt:message key="sellBy"/>
						<a href="<c:url value="/profile?id=${product.idSeller}"/>">${UserDAO.getLoginFromId(product.idSeller)}</a><br/>
						<c:choose>
							<c:when test="${product.inStock()}">
								<span class='green'>
									<fmt:message key="inStock"/>
								</span>
							</c:when>
							<c:otherwise>
								<span class='red'>
									<fmt:message key="outOfStock"/>
								</span>
							</c:otherwise>
						</c:choose> <br/>
						<b><fmt:message key="unitPrice"/> : <fmt:formatNumber value="${product.price}" minFractionDigits="2"/> &euro;</b>
					</p>
				</div>		
				<div class="span3">
				<form name="productSheet" action=<c:url value="/productSheet"/>	method="POST">
						<p>
							<b><fmt:message key="quantity" /> :</b> <br />
							<select name="quantity" <c:if test="${!product.inStock()}"> disabled </c:if>>
								<c:forEach var="entry" begin="1" end="${product.quantity}">
									<option value="${entry}"> ${entry} </option>
								</c:forEach>
							</select>
							<br /> 
							<input name="id" type="hidden" value="${product.id}" />
							<button type="submit" class="btn btn-success <c:if test="${!product.inStock()}"> disabled </c:if>"<c:if test="${!product.inStock()}"> disabled </c:if>><fmt:message key="addToBasket" /></button>
						</p>
					</form>
				</div>
			</div>
			<i>${product.information}</i>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>