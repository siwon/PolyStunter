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
<link href="/PolyStunter/design/form.css" rel="stylesheet"
	type="text/css">
<script type="text/javascript" src="/PolyStunter/js/form.js"></script>
<title>Connexion</title>
</head>
<body>
	<%@include file="includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Connexion</div>
		<img id="top" src="/PolyStunter/images/top.png" alt="">
		<div id="form_container">
			<form id="form_555931" class="appnitro" method="post"
				action="<c:url value="/login" />"
				onsubmit="return checkConnectForm(this);">
				<ul>
					<li id="li_1" style="width: 300px;"><label class="description"
						for="login">Login </label>
						<div>
							<input id="login" name="login" class="element text medium"
								type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_2" style="width: 300px;"><label class="description"
						for="password">Password </label>
						<div>
							<input id="password" name="password"
								class="element password medium" type="password" maxlength="255"
								value="" />
						</div></li>
					<li><input type="hidden" name="form_id" value="555931" /></li>
					<input id="saveForm" class="button" type="submit"
						name="submit" value="Se connecter" />
				</ul>
			</form>
		</div>
		<img id="bottom" src="/PolyStunter/images/bottom.png" alt="">
		<c:if test="${not empty connectForm }">
			<div class="error">
				<p>${connectForm.toString()}</p>
			</div>
		</c:if>
	</div>
	<%@include file="includes/footer.jsp"%>

</body>
</html>