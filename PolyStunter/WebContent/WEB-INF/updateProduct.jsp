<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<%@page import="beans.Product"%>
<%@page import="dao.WarehouseDAO"%>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="updateProduct" /></title>
		
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
				<h1><fmt:message key="updateProduct" /></h1>
				<a href=<c:url value="/store"/>><fmt:message key="back" /></a>
			</div>
			
				
			<% Product p = (Product) request.getAttribute("product"); %>
			
			<form class="form-horizontal" name="productManager" action=<c:url value="/storeAction"/>	method="POST">
				<div class="control-group">
					<label class="control-label" for="name"><fmt:message key="productName"/></label>
					<div class="controls">
						<input type="text" id="name" name="name" value="<%= p.getName() %>">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="reference"><fmt:message key="reference" /></label>
					<div class="controls">
						<input type="text" id="reference" name="reference" value="<%= p.getReference() %>"required >
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="information"><fmt:message key="information" /></label>
					<div class="controls">
						<textarea rows="4" id="information" name="information"><%= p.getInformation() %></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="warehouse"><fmt:message key="warehouseAddress" /></label>
					<div class="controls">
						<input type="text" class="input-mini disabled" disabled value="<%= WarehouseDAO.getInstance().getWarehouseName(p.getWarehouse()) %>" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="price"><fmt:message key="unitPrice" /></label>
					<div class="controls">
						<input class="input-mini" id="price" name="price" type="text" pattern="[0-9]+[.]?[0-9]*" value="<%= p.getPrice()%>" required> &euro;
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="quantity"><fmt:message key="quantity" /></label>
					<div class="controls">
						<input class="input-mini" type="text" pattern="[0-9]+"  id="quantity" name="quantity" value="<%= p.getQuantity()%>" required>
					</div>
				</div>						
				<div class="control-group">
					<div class="controls">
						<a href="<c:url value="/store" />"><button type="button" class="btn"><fmt:message key="cancel"/></button></a>
						<button type="submit" class="btn btn-success"><fmt:message key="save"/></button>
					</div>
				</div>
			</form>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>