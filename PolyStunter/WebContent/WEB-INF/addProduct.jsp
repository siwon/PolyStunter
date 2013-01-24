<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="addAProduct" /></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="/PolyStunter/design/design.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		
		<script src="http://axe.mappy.com/1v1/init/get.aspx?auth=[token]&version=2.01&solution=ajax"></script>
		
	</head>

	<body>
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
		
			<div class="page-header">
				<h1><fmt:message key="addAProduct" /></h1>
			</div>
			<div id="mymap" style="width:100%;height:400px;"></div>
			<script>
				var map = new Mappy.api.map.Map({
				    container:'#mymap'
				});
				map.setCenter(new Mappy.api.geo.Coordinates(1.44295,43.6044),7);
			</script>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>