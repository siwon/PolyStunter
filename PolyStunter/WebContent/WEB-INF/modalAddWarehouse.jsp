<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
	<h3 id="myModalLabel"><fmt:message key="addWarehouse"/></h3>
</div>
<div class="modal-body">
	<p>Formulaire d'ajout</p>
</div>
<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="cancel"/></button>
	<a href="<c:url value="/warehouse"/>"><button class="btn btn-primary"><fmt:message key="save"/></button></a>
</div>