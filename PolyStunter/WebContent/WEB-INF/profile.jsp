<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />
<fmt:setLocale value="fr" scope="application"/>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="myProfile"/></title>
		
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
				<h1><fmt:message key="myProfil" /></h1>
			</div>
			<c:if test="${not empty user}">
				<div>
					<p>
						<b><fmt:message key="myLogin"/></b> : ${user.login} <br/>
						<b><fmt:message key="myEmail"/></b> : ${user.mail} <br/>
						<b><fmt:message key="myStatus"/></b> :
						<c:choose>
							<c:when test="${user.isCustomer()}">
								<fmt:message key="customer"/>
							</c:when>
							<c:when test="${user.isSeller()}">
								<fmt:message key="seller"/>
							</c:when>
							<c:when test="${user.isDeliveryMan()}">
								<fmt:message key="deliveryMan"/>
							</c:when>
							<c:otherwise>
								<fmt:message key="admin"/>
							</c:otherwise>
						</c:choose>
					</p>				
				</div>
			</c:if>
		<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>