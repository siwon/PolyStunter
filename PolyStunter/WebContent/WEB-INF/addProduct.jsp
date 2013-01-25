<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="addAProduct" /></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="/PolyStunter/design/design.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-fileupload.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-fileupload.min.css" rel="stylesheet">
				
	</head>

	<body>
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
		
			<div class="page-header">
				<h1><fmt:message key="addAProduct" /></h1>
				<a href="<c:url value="/store"/>"><button class="btn btn-link"><fmt:message key="back"/></button></a>
			</div>
			
			<% if(request.getAttribute("errors") != null) {
				out.print("<div class='alert alert-error'>"+ request.getAttribute("errors").toString() + "</div>");
			}
			%>
			<% if(request.getAttribute("success") != null) {
				out.print("<div class='alert alert-success'>"+ request.getAttribute("success").toString() + "</div>");
			}
			%>		
			
			<form class="form-horizontal" id="addProduct" action="<c:url value="/addProduct" />" method="POST" enctype="multipart/form-data">
				<div class="control-group">
					<label class="control-label" for="name"><fmt:message key="productName" /></label>
					<div class="controls">
						<input type="text" id="name" name="name" required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="reference"><fmt:message key="reference" /></label>
					<div class="controls">
						<input type="text" id="reference" name="reference"  required>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="information"><fmt:message key="information" /></label>
					<div class="controls">
						<textarea rows="4" id="information" name="information"></textarea>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="photo"><fmt:message key="photo" /></label>
					<div class="controls">
						<div class="fileupload fileupload-new" data-provides="fileupload">
							<div class="fileupload-new thumbnail" style="width: 200px; height: 150px;"><img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&text=no+image" />
							</div>
							<div class="fileupload-preview fileupload-exists thumbnail" style="max-width: 200px; max-height: 150px; line-height: 20px;">
							</div>
							<div>
								<span class="btn btn-file btn-info"><span class="fileupload-new"><fmt:message key="selectImage" /></span><span class="fileupload-exists"><fmt:message key="change" /></span><input  name="photo" type="file" /></span>
								<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><fmt:message key="remove" /></a>
							</div>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="warehouse"><fmt:message key="warehouseAddress" /></label>
					<div class="controls">
						<c:if test="${warehouses.isEmpty()}">
							<fmt:message key="noWarehouse"/>
						</c:if>
						<c:if test="${!warehouses.isEmpty()}">
							<select name="warehouse">
								<c:forEach var="warehouse" items="${warehouses}">
									<option value="${warehouse.getId()}">${warehouse.getName()}</option>									
								</c:forEach>
							</select>
						</c:if>
						<a href="#myModal" role="button" class="btn btn-info" data-toggle="modal"><fmt:message key="addWarehouse"/></a>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="price"><fmt:message key="unitPrice" /></label>
					<div class="controls">
						<input class="input-mini" id="price" name="price" type="text" pattern="[0-9]+[.]?[0-9]*" required> &euro;
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="quantity"><fmt:message key="quantity" /></label>
					<div class="controls">
						<input class="input-mini" type="text" pattern="[0-9]+"  id="quantity" name="quantity" required>
					</div>
				</div>						
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-success"><fmt:message key="add"/></button>
					</div>
				</div>
			</form>
			<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<%@include file="modalAddWarehouse.jsp"%>
			</div>
		</div>
		<hr>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>