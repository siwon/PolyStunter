<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="properties.text" />

<div id="notificationForm" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="addNotification" aria-hidden="true">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		<h3 id="myModalLabel"><fmt:message key="addNotification"/></h3>
	</div>
	<div class="modal-body">
		<div class="control-group">
			<label class="control-label" for="type"><fmt:message key="type"/></label>
			<div class="controls">
				<select name="type" id="type">
					<option value="BUILDING_SITE"><fmt:message key="buildingSite"/></option>
					<option value="ACCIDENT"><fmt:message key="accident"/></option>
					<option value="DEMONSTRATION"><fmt:message key="demonstration"/></option>
					<option value="OTHER"><fmt:message key="other"/></option>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="information"><fmt:message key="information"/></label>
			<div class="controls">
				<textarea id="information" name="information" rows="3"></textarea>
			</div>
		</div>
		<div id="info">
		</div>
		<div class="control-group">
			<div class="controls controls-row">
				<input type="text" id="latitude" name="latitude" pattern="[0-9]+[.]?[0-9]*" class="span2" placeholder="<fmt:message key="latitude" />"/>
				<input type="text" id="longitude" name="longitude" pattern="[0-9]+[.]?[0-9]*" class="span2" placeholder="<fmt:message key="longitude" />"/>
			</div>
		</div>
		<script type="text/javascript">
		if(navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(function(position)
			{
				document.getElementById("info").innerHTML = "<div class='alert alert-success'>Votre position a \351t\351 obtenue par g\351olocalisation via votre navigateur.</div>";
				document.getElementById("latitude").value = position.coords.latitude;
				document.getElementById("longitude").value = position.coords.longitude;
				document.getElementById('latitude').setAttribute("readonly");
				document.getElementById('longitude').setAttribute("readonly");
				
			},function(error) {
				
				switch(error.code) {
				    case error.TIMEOUT:
				    	document.getElementById("info").innerHTML = "<div class='alert alert-warning'>Timeout !</div>";
				    break;
				    case error.PERMISSION_DENIED:
				    	document.getElementById("info").innerHTML = "<div class='alert alert-warning'>Vous n'avez pas autoris\351 la g\351olocalisation</div>";
				    break;
				    case error.POSITION_UNAVAILABLE:
				    	document.getElementById("info").innerHTML = "<div class='alert alert-warning'>Votre position n'a pas pu \352tre d\351termin\351ee.</div>";
				    break;
				    case error.UNKNOWN_ERROR:
				    	document.getElementById("info").innerHTML = "<div class='alert alert-warning'>Erreur inconnue</div>";
				    default:
				    	break;
			    }
				
			});
		} else {
			document.getElementById("info").innerHTML = "<div class='alert alert-warning'>Votre navigateur ne supporte pas la g\351olocalisation.<br/>Veuillez saisir les champs latitude et longitude</div>";
		}
		</script>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="cancel"/></button>
		<button class="btn btn-info"><fmt:message key="save"/></button>
	</div>
</div>