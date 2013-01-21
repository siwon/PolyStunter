<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/PolyStunter/design/design.css" rel="stylesheet"
	type="text/css">
<title>Produits</title>
</head>
<body>
	<%@include file="/WEB-INF/includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Produits</div>
		<c:if test="${not empty market }">
			<div>
				<c:forEach var="product" items="${market.getProducts()}">
					<div class="market">
						<img src="/PolyStunter/products/${product.getPhoto()}" width="100px" height="100px"/>
						<b>${product.getName()}</b> <br/>
						<b style="color:red;">Prix :</b> ${product.getPrice()} &euro;
						<c:if test="${product.inStock()}">
						<span style="color:green;">En stock</span>
						</c:if>
						<c:if test="${! product.inStock()}">
						<span style="color:red;">En rupture</span>
						</c:if>
						<br/>
						<a href="<c:url value="/productSheet?id=${product.getId()}"/>" class="button">Voir</a>
					</div>
				</c:forEach>
			</div>
		</c:if>
		<c:if test="${empty market }">
			<div align="center">Il n'y a plus de produits en vente.</div>
		</c:if>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>