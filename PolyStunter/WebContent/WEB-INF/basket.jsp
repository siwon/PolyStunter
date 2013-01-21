<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="beans.Market"%>
<%@page import="beans.Basket"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/PolyStunter/design/design.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/PolyStunter/js/form.js"></script>
<title>Panier</title>
</head>
<body>
	<%@include file="/WEB-INF/includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Mon Panier</div>

		<c:if test="${! userSession.getBasket().isEmpty()}">
			<p>
				<b>Articles sélectionnés</b> <span style="float: right;"><span><b>Prix
							Unitaire</b></span><span style="padding-left: 20px;"><b>Quantité</b></span></span>
				<br />
			</p>
			<hr style="height: 1px; background-color: red;" />
			<div id="basketList">

				<c:forEach var="entry"
					items="${userSession.getBasket().getProducts().entrySet()}">
					<p>
						<span><a
							href="<c:url value="/productSheet?id=${entry.getKey().getId()}"/>"><b>${entry.getKey().getName()}</b></a></span>
						<c:if test="${entry.getKey().inStock()}">
							<span style="color: green;">En stock</span>
						</c:if>
						<c:if test="${!entry.getKey().inStock()}">
							<span style="color: red;">En rupture</span>
						</c:if>
						<span>Ref. ${entry.getKey().getReference()}</span> <span
							style="float: right;"><span>${entry.getKey().getPrice()}
								&euro;</span><span style="padding-left: 55px; padding-right: 10px">${entry.getValue()}</span></span>
					</p>
					<hr />
				</c:forEach>
				<span style="float: right;"><span
					style="color: red; font-weight: bold;">TOTAL TTC</span> <span
					style="padding-left: 20px;"><b>${userSession.getBasket().getCost()}
							&euro;</b></span></span>
			</div>
			<div align="center">
				<form name="actionOnBasket" action="<c:url value="/basket" />"
					onsubmit="return confirmation();" method="POST">
					<input class="button" type="submit" value="Vider"
						name="actionBasket" /><input class="button" type="submit"
						value="Valider" name="actionBasket" />
				</form>
			</div>
		</c:if>
		<c:if test="${userSession.getBasket().isEmpty()}">
			<div align="center">
				<p>
					Votre panier est vide.<br /> <a href="<c:url value="/products" />">Commencer
						vos achats.</a>
				</p>
			</div>
		</c:if>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>