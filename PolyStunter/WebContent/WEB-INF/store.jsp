<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8" ?>
<%@page import="java.util.*"%>
<%@page import="beans.Product"%>
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
		<h3>Actions possibles</h3>
		<div>
			<a class="button" href="<c:url value="/addProduct"/>">Ajouter un produit</a>
		</div>
		
		<h3>Mes produits en vente</h3>
		<%
			List<Product> myProducts = (List<Product>)request.getAttribute("productsOfSeller");
			for(Product p : myProducts) {%>
				<div class="market">
				<img src="/PolyStunter/products/<%=p.getPhoto()%>" width="100px" height="100px" /> <br/><b>
				<% if(p.getName().length()>30) {%>
					<%=p.getName().substring(0, 20) + "..."%>
				<%} else {%>
					<%=p.getName()%>
				<%}%>
				</b> <br /> 
				<b style="color: red;">Prix :</b><%=p.getPrice()%>&euro;<%
				
				if(p.inStock()) {%>
					<span style="color: green;">En stock</span>
				<%} else { %>
					<span style="color: red;">En rupture</span>
				<%
				}%>
				<br /> <a href="<%=request.getContextPath()+ "/modifyProduct?id=" + p.getId()%>" class="button">Modifier</a>
				<a href="<%=request.getContextPath()+ "/removeProduct?id=" + p.getId()%>" class="button">Retirer</a>
				</div>
				<%
			}
		%>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>