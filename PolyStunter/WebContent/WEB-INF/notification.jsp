<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		
		<title><fmt:message key="notifications" /></title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="description" content="">
		<meta name="author" content="Alexandre Bisiaux and Simon Rousseau">
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		
		<link href="/PolyStunter/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="/PolyStunter/design/design.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="/PolyStunter/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		<script src="http://axe.mappy.com/1v1/init/get.aspx?auth=NL0bUXbzmVeI5dG4e8zDAf8lTj+xnGxGOT3cd1RcTvG8FHwPu9F8tvuOhDGtwRlBLi+BAF3GBtY=&version=2.01&solution=ajax"></script>
		
	</head>

	<body>
		<%@include file="/WEB-INF/includes/header.jsp" %>
		
		<div class="container">
		
			<div class="page-header">
				<h1><fmt:message key="notifications" /></h1>
			</div>		
							
			<form name="addNotification" action="" method="POST">
				
				<div class="form-actions center">
					<div class="controls">
						<a href="#notificationForm" role="button" class="btn btn-info" data-toggle="modal"><fmt:message key="addNotification"/></a>
						<a href="#" role="button" class="btn btn-info" data-toggle="modal"><fmt:message key="myNotifications"/></a>
					</div>
				</div>
				<%@include file="modalAddNotification.jsp" %>
				
			</form>
			<h3><fmt:message key="currentNotification"/></h3>
			<div id="mymap" style="width:100%;height:400px;"></div>
			<script>
				var map = new Mappy.api.map.Map({
				    container:'#mymap'
				});
				map.setCenter(new Mappy.api.geo.Coordinates(-1.553879,47.218286),7);
				var markerLayer = new Mappy.api.map.layer.MarkerLayer(40);
				map.addLayer(markerLayer);
				var ACCIDENT = new Mappy.api.ui.Icon(Mappy.api.ui.Icon.DEFAULT);
				ACCIDENT.image = "/PolyStunter/images/accident.png";
				var BUILDING_SITE = new Mappy.api.ui.Icon(Mappy.api.ui.Icon.DEFAULT);
				BUILDING_SITE.image = "/PolyStunter/images/building_site.png";
				var DEMONSTRATION = new Mappy.api.ui.Icon(Mappy.api.ui.Icon.DEFAULT);
				DEMONSTRATION.image = "/PolyStunter/images/demonstration.png";
				var OTHER = new Mappy.api.ui.Icon(Mappy.api.ui.Icon.DEFAULT);
				OTHER.image = "/PolyStunter/images/other.png";
			</script>
			<%= request.getAttribute("markers")%>
			<hr>
		</div>
		<%@include file="/WEB-INF/includes/footer.jsp" %>
	</body>
</html>