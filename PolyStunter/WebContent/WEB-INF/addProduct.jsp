<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/PolyStunter/design/design.css" rel="stylesheet"
	type="text/css">
<link href="/PolyStunter/design/form.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/PolyStunter/js/form.js"></script>
<title>Gérer ma boutique : ajout produit</title>
</head>
<body>
	<%@include file="/WEB-INF/includes/header.jsp"%>
	
	<div id="content">
		<div class="titlePage">Ajouter un produit</div>

		<img id="top" src="/PolyStunter/images/top.png" alt="">
		<div id="form_container">

			<form id="form_557391" class="appnitro" enctype="multipart/form-data"
				method="post" action="">
				<div class="form_description"></div>
				<ul>

					<li id="li_1"><label class="description" for="name">Nom
							du produit </label>
						<div>
							<input id="element_1" name="name" class="element text medium"
								type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_3"><label class="description" for="reference">Référence
							du produit </label>
						<div>
							<input id="element_3" name="reference"
								class="element text medium" type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_5"><label class="description" for="information">Information
					</label>
						<div>
							<textarea id="element_5" name="information"
								class="element textarea medium"></textarea>
						</div></li>
					<li id="li_6"><label class="description" for="photo">Photo
					</label>
						<div>
							<input id="element_6" name="photo" class="element file"
								type="file" />
						</div></li>
					<li id="li_4"><label class="description" for="quantity">Quantité
					</label>
						<div>
							<input id="element_4" name="quantity" class="element text small"
								type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_8"><label class="description" for="latitude">Latitude
					</label>
						<div>
							<input id="element_8" name="latitude" class="element text medium"
								type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_7"><label class="description" for="longitude">Longitude
					</label>
						<div>
							<input id="element_7" name="longitude"
								class="element text medium" type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_2"><label class="description" for="price">Prix
					</label>
						<div>
							<input id="element_2" name="price" class="element text small"
								type="text" maxlength="255" value="" />
						</div></li>
					<li><input type="hidden" name="form_id" value="557391" /></li>
					<input id="saveForm" class="button" type="submit" name="submit"
						value="Ajouter" />

				</ul>
			</form>
			<% if(request.getAttribute("errors") != null) {
			%><div class="error"><%=request.getAttribute("errors") %></div>
			<%}%>
		</div>
		<img id="bottom" src="/PolyStunter/images/bottom.png" alt="">
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>