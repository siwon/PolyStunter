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
			
				
			<% Product p = (Product) request.getAttribute("product"); %>
			<div class="row">
				<%= request.getAttribute("productSheet") %>
				<div class="span3">
				<form name="productSheet" action=<c:url value="/productSheet"/>	method="POST">
						<p>
							<b><fmt:message key="quantity" /> :</b> <br />
							<select name="quantity"	<%if (!p.inStock())	out.println("disabled='disabled'");%> >
								<c:forEach var="entry" begin="1" end="${product.getQuantity()}">
									<option value="${entry}"> ${entry} </option>
								</c:forEach>
							</select>
							<br /> 
							<input name="id" type="hidden" value="<%=p.getId()%>" />
							<button type="submit" class="btn btn-success <%if (!p.inStock())
				out.println("disabled");%>"><fmt:message key="addToBasket" /></button>
						</p>
					</form>
				</div>
			</div>
			<i><%= p.getInformation() %></i>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>