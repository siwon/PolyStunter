<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<%@page import="beans.Product"%>
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
						<label class="control-label" for="photo"><fmt:message key="photo" /></label>
						<div class="controls">
							<div class="fileupload fileupload-new" data-provides="fileupload">
								<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;"><img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image" />
								</div>
								<div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;">
									<img src="/products/<%= p.getPhoto() %>" />
								</div>
								<div>
									<span class="btn btn-file"><span class="fileupload-new"><fmt:message key="selectImage" /></span><span class="fileupload-exists"><fmt:message key="change" /></span><input required name="photo" type="file" /></span>
									<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="remove" /></a>
								</div>
							</div>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="address"><fmt:message key="warehouseAddress" /></label>
						<div class="controls">
							<input type="text" id="address" name="address" placeholder="<fmt:message key="street" />" required>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<input type="text" class="input-mini" id="zipCode" name="zipCode" placeholder="<fmt:message key="zipCode" />" required/>
							<input type="text" id="city" name="city" placeholder="<fmt:message key="city" />" required/>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-success"><fmt:message key="add"/></button>
						</div>
					</div>
			</form>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>