<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="page">
	<div id="leadbar">
		<a href="<c:url value="/home" />" class="logo"></a>
		<c:if test="${not empty userSession}">
			<div id="userCard">
				<img style="float: left; margin: -10px 6px 6px 0;"
					src="images/userCard.png" width="50px" height="50px" /> Bonjour,
				${userSession.getLogin()} ! (<a href="<c:url value="/logout" />">Déconnexion</a>)
				Status : ${userSession.getStatus().toLowerCase()}
			</div>
		</c:if>
		<c:if test="${not empty userSession and userSession.isCustomer()}">
			<div style="width: 100%;">
				<div id="searchBar">
					<form name="searchBar" action="/search" method="POST">
						<a id="logoSearch" href="javascript:;" onclick="parentNode.submit();"></a>
						<input type="search" value="Rechercher un produit" name="search" onclick="parentNode.submit();"/>
					</form>
				</div>
				<div id="basket">
					<a href="<c:url value="/basket" />" id="basketLogo"></a>(${userSession.getBasket().size()})
				</div>
			</div>
		</c:if>
	</div>
	<div id="wrap">
		<div class="wrap_inner">
			<div id="header">
				<div id='cssmenu'>
					<c:choose>
						<c:when test="${empty userSession}">
							<!-- Si non connecté -->
							<ul>
								<li class='active'><a href="<c:url value="/login" />"><span>Connexion</span></a></li>
								<li><a href="<c:url value="/register" />"><span>S'inscrire</span></a></li>
							</ul>
						</c:when>
						<c:when test="${userSession.isCustomer()}">
							<ul>
								<li class='active'><a href="<c:url value="/home" />"><span>Home</span></a></li>
								<li><a href="<c:url value="/profile" />"><span>Mon
											profil</span></a></li>
								<li><a href="<c:url value="/products" />"><span>Produits</span></a></li>
								<li><a href="<c:url value="/basket" />"><span>Panier</span></a></li>
							</ul>
						</c:when>
						<c:when test="${userSession.isSeller()}">
							<ul>
								<li class='active'><a href="<c:url value="home" />"><span>Home</span></a></li>
								<li><a href="<c:url value="/profile" />"><span>Mon
											profil</span></a></li>
								<li><a href='<c:url value="/store" />'><span>Gerer
											ma boutique</span></a></li>
							</ul>
						</c:when>
						<c:otherwise>
							<ul>
								<li class='active'><a href="<c:url value="home" />"><span>Home</span></a></li>
								<li><a href="<c:url value="/profile" />"><span>Mon
											profil</span></a></li>
								<li><a href='#'><span>Pense-Bête</span></a></li>
								<li><a href='#'><span>Notifications</span></a></li>
							</ul>
						</c:otherwise>
					</c:choose>
				</div>
			</div>