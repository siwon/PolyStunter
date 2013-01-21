<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="beans.Product"%>
<%@page import="beans.User"%>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fr" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/PolyStunter/design/design.css" rel="stylesheet"
	type="text/css">
<title>Fiche produit</title>
</head>
<body>
	<%@include file="/WEB-INF/includes/header.jsp"%>
	<div id="content">
		<div class="titlePage">Fiche produit</div>
		<a href=<c:url value="/products"/>>Retour</a>
		<%
			Product p = (Product) request.getAttribute("product");
		%>
		<table id="productSheet">
			<tr>
				<td><img src="/PolyStunter/products/<%=p.getPhoto()%>" width="100px" height="100px" /></td>
				<td><div>
						<p>
							<span class="name"><%=p.getName()%></span><br /> <i>Ref. <%=p.getReference()%></i><br />
							Vendu par : <b><%=User.getLoginFromId(p.getIdSeller())%></b>
							<br/>
							<br />
							<%
								if (p.inStock())
									out.println("<span style='color: green;''>En stock</span>");
								else
									out.println("<span style='color: red;''>En rupture</span>");
							%>
						</p>
					</div></td>
				<td
					style="border: 2px solid #019cde; border-radius: 10px; text-align: center;">
					<form name="productSheet" action=<c:url value="/productSheet"/>
						method="POST">
						<p>
							<b>Quantité :</b> <br /> <select name="quantity"
								<%if (!p.inStock())
				out.println("disabled='disabled'");%>>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select><br /> <input name="id" type="hidden"
								value="<%=p.getId()%>" /> <input class="button" type="submit"
								name="actionProductSheet" value="Ajouter au panier"
								<%if (!p.inStock())
				out.println("disabled='disabled'");%> />
						</p>
					</form>
				</td>
			</tr>
		</table>
		<div style="margin-top: 30px;">
			<i><%=p.getInformation()%></i>
		</div>
	</div>
	<%@include file="/WEB-INF/includes/footer.jsp"%>
</body>
</html>