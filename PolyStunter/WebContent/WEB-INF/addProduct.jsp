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
			<div class="row">
				<div class="span6">
					<form class="form-horizontal" id="addProduct" action="<c:url value="/addProduct" />" method="POST">
						<div class="control-group">
							<label class="control-label" for="name"><fmt:message key="productName" /></label>
							<div class="controls">
								<input type="text" id="name" name="name" required>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="reference"><fmt:message key="reference" /></label>
							<div class="controls">
								<input type="text" id="reference" name="reference" required >
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
										<span class="btn btn-file"><span class="fileupload-new"><fmt:message key="selectImage" /></span><span class="fileupload-exists"><fmt:message key="change" /></span><input required name="photo" type="file" /></span>
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
											<option value="${warehouse.getId()}">${warehouse.getStreet()}, ${warehouse.getZipCode().toString()} ${warehouse.getCity()}</option>									
										</c:forEach>
									</select>
								</c:if>
								<a href="#myModal" role="button" class="btn" data-toggle="modal"><fmt:message key="addWarehouse"/></a>
								<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
									<%@include file="modalAddWarehouse.jsp"%>
								</div>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<button type="submit" class="btn btn-success"><fmt:message key="add"/></button>
							</div>
						</div>
					</form>
				</div>
				<div class="span3">
				</div>
			</div>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>