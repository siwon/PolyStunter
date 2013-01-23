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
			</a> <a class="brand" href="<c:url value="/home"/>">PolyStunter</a>
			<div class="nav-collapse collapse">
			
				<ul class="nav">
					<c:choose>				
						<c:when test="user.isCustomer()">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profil"/>" ><fmt:message key="myProfil" /></a></li>
							<li class="active"><a href="<c:url value="/products"/>" ><fmt:message key="products" /></a></li>
							<li class="active"><a href="<c:url value="/basket"/>" ><fmt:message key="myBasket" /></a></li>
						</c:when>
						
						<c:when test="user.isSeller()">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profil"/>" ><fmt:message key="profil" /></a></li>
							<li class="active"><a href="<c:url value="/store"/>" ><fmt:message key="myStore" /></a></li>
						</c:when>
		
						<c:when test="user.isDeliveryMan()">
							<li class="active"><a href="<c:url value="/home"/>" ><fmt:message key="home" /></a></li>
							<li class="active"><a href="<c:url value="/profil"/>" ><fmt:message key="profil" /></a></li>
							<li class="active"><a href="<c:url value="#"/>" ><fmt:message key="deliverySpace" /></a></li>
							<li class="active"><a href="<c:url value="#"/>" ><fmt:message key="notifications" /></a></li>
						</c:when>
						
						<c:otherwise>
								<li class="active"><a href="<c:url value="/login"/>" ><fmt:message key="connect" /></a></li>
								<li class="active"><a href="<c:url value="/register"/>" ><fmt:message key="register" /></a></li>
						</c:otherwise>
					</c:choose>
				</ul>
				
				<c:choose>
					<c:when test="empty user">
						<div class="navbar-form pull-right">
							<fmt:message key="welcome" />,${user.getLogin()} 
						</div>
					</c:when>
					<c:otherwise>
						<script src="/PolyStunter/bootstrap/js/jquery.js"></script>
						<script src="/PolyStunter/bootstrap/js/jqBootstrapValidation.js"></script>
						<script>
							$(function () { $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
						</script>
						<form class="navbar-form pull-right" action="<c:url value="/login" />" method="POST">
							<input class="span2" type="text" placeholder="Login" required>
							<input class="span2" type="password" placeholder="Password" required>
							<button type="submit" class="btn btn-success">
								<fmt:message key="connect" />
							</button>
						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>