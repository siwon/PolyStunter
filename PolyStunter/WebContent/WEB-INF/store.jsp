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
<title>Insert title here</title>
</head>
<body>
<%@include file="/WEB-INF/includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Ma boutique</div>
		<a class="button" href="<c:url value="/addProduct"/>">Ajout produit</a>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>