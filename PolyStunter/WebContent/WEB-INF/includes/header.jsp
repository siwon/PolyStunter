<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<a class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse"> <span class="icon-bar"></span> <span
				class="icon-bar"></span> <span class="icon-bar"></span>
			</a> <a class="brand" href="<c:url value="/home"/>"><img src="/PolyStunter/images/logo_Small.png" width="25px"/> PolyStunter</a>
			<div class="nav-collapse collapse">
			
				<ul class="nav">
					<c:choose>				
						<c:when test="${user.isCustomer()}">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profile"/>" ><fmt:message key="myProfil" /></a></li>
							<li class="active"><a href="<c:url value="/products"/>" ><fmt:message key="products" /></a></li>
							<li class="active"><a href="<c:url value="/basket"/>" ><fmt:message key="myBasket" /></a></li>
						</c:when>
						
						<c:when test="${user.isSeller()}">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profile"/>" ><fmt:message key="profil" /></a></li>
							<li class="active"><a href="<c:url value="/store"/>" ><fmt:message key="myStore" /></a></li>
						</c:when>
		
						<c:when test="${user.isDeliveryMan()}">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profile"/>" ><fmt:message key="profil" /></a></li>
							<li class="active"><a href="<c:url value="#"/>" ><fmt:message key="deliverySpace" /></a></li>
							<li class="active"><a href="<c:url value="/notification"/>" ><fmt:message key="notifications" /></a></li>
						</c:when>
						
						<c:when test="${user.isAdmin()}">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profile"/>" ><fmt:message key="profil" /></a></li>
							<li class="active"><a href="<c:url value="/products"/>" ><fmt:message key="products" /></a></li>
							<li class="active"><a href="<c:url value="/basket"/>" ><fmt:message key="myBasket" /></a></li>
							<li class="active"><a href="<c:url value="/store"/>" ><fmt:message key="myStore" /></a></li>
							<li class="active"><a href="<c:url value="#"/>" ><fmt:message key="deliverySpace" /></a></li>
							<li class="active"><a href="<c:url value="/notification"/>" ><fmt:message key="notifications" /></a></li>
						</c:when>
						
						<c:otherwise>
								<li class="active"><a href="<c:url value="/login"/>" ><fmt:message key="connect" /></a></li>
								<li class="active"><a href="<c:url value="/register"/>" ><fmt:message key="register" /></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				
				<c:choose>
					<c:when test="${empty user}">
						<script src="/PolyStunter/bootstrap/js/jquery.js"></script>
						<script src="/PolyStunter/bootstrap/js/jqBootstrapValidation.js"></script>
						<script>
							$(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
						</script>
						<form class="navbar-form pull-right" action="<c:url value="/login" />" method="POST">
							<input class="span2" type="text" name="login" placeholder="Login" required>
							<input class="span2" type="password" name="password" placeholder="Password" required>
							<button type="submit" class="btn btn-success">
								<fmt:message key="connect" />
							</button>
						</form>						
					</c:when>
					<c:otherwise>
						<div class=".navbar-text pull-right">
							<p style="margin-top:10px;color: white;">
								<fmt:message key="welcome" />, ${user.getLogin()} ! (<a href="<c:url value="/logout" />"><fmt:message key="logout" /></a>)
							</p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>