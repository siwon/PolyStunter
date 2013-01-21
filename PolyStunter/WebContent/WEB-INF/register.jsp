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
<title>S'inscrire</title>
</head>
<body>
	<%@include file="/WEB-INF/includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Formulaire d'inscription</div>
		<img id="top" src="/PolyStunter/images/top.png" alt="">
		<div id="form_container">
			<form id="form_555931" class="appnitro" method="post"
				onsubmit="return checkRegisterForm(this);"
				action="<c:url value="/register" />">
				<ul>
					<li id="li_1"><label class="description" for="login">Login
					</label>
						<div>
							<input id="login" name="login" class="element text medium"
								type="text" maxlength="255" value="" />
						</div></li>
					<li id="li_2"><label class="description" for="password">Password
					</label>
						<div>
							<input id="password" name="password"
								class="element password medium" type="password" maxlength="255"
								value="" />
						</div></li>
					<li id="li_4"><label class="description" for="mail">Email
					</label>
						<div>
							<input id="mail" name="mail" class="element text large"
								type="text" maxlength="255" value="" />
						</div></li>

					<li id="li_5"><label class="description" for="status">Statut
					</label> <span> <input id="element_5_1" name="status"
							class="element radio" checked="checked" type="radio"
							value="CUSTOMER" /> <label class="choice" for="element_5_1">Client</label>
							<input id="element_5_2" name="status" class="element radio"
							type="radio" value="SELLER" /> <label class="choice"
							for="element_5_2">Vendeur</label> <input id="element_5_3"
							name="status" class="element radio" type="radio"
							value="DELIVERYMAN" /> <label class="choice" for="element_5_3">Livreur</label>

					</span></li>

					<li><input type="hidden" name="form_id" value="555931" /></li>

					<input id="saveForm" class="button" type="submit" name="submit"
						value="S'inscrire" />
				</ul>
			</form>
		</div>
		<img id="bottom" src="/PolyStunter/images/bottom.png" alt="">
		<c:if test="${not empty registerForm }">
			<div class="error">
				<p>${registerForm.toString()}</p>
			</div>
		</c:if>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>