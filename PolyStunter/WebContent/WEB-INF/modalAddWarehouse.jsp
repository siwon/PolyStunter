<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<form class="form-horizontal" id="addWarehouse" name="addWarehouse" action="<c:url value="/addWarehouse" />" method="POST">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		<h3 id="myModalLabel"><fmt:message key="addWarehouse"/></h3>
	</div>
	<div class="modal-body">
		
			<div class="control-group">
				<label class="control-label" for="warehouseName"><fmt:message key="warehouseName" /></label>
				<div class="controls">
					<input type="text" id="warehouseName" name="warehouseName" placeholder="<fmt:message key="warehouseName" />" required>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="street"><fmt:message key="warehouseAddress" /></label>
				<div class="controls">
					<input type="text" id="street" name="street" placeholder="<fmt:message key="street" />" required>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="address"></label>
				<div class="controls controls-row">
					<input class="span1 input-mini" type="text" maxlength="5" pattern="[0-9]{5}"  id="zipCode" name="zipCode" placeholder="<fmt:message key="zipCode" />" required>
					<input class="span2" type="text" id="city" name="city" placeholder="<fmt:message key="city" />" required>
				</div>
			</div>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true"><fmt:message key="cancel"/></button>
		<button type="submit" class="btn btn-info"><fmt:message key="save"/></button>
	</div>
</form>