<%@page import="com.ipartek.formacion.nidea.controller.backoffice.MaterialesController"%>
<%@include file="/templates/head.jsp"%>
<%@include file="/templates/alert.jsp" %>

<input type="hidden" name="op" value="0">
	<div class="col-xs-12 col-sm-4"></div>
	<div class="col-xs-12 col-sm-6">
		<a class="btn" href="backoffice/materiales">Volver</a>
		<form action="backoffice/materiales" method="post">
			<div class="form-group">
				<input type="hidden" class="form-control" name="id" value="${ material.getId() }">
    			
    			<input type="text" disabled class="form-control" name="id" value="${ material.getId() }"placeholder="">
  			</div>
			<div class="form-group">
    			<label for="nombre">Nombre:</label>
	    		<input type="text" class="form-control" name="nombre" value="${ material.getNombre() }">
	  		</div>
  			<div class="form-group">
    			<label for="precio">Precio:</label>
    			<input type="text" class="form-control" name="precio" value="${ material.getPrecio() }">
  			</div> 
  			<c:if test="${ (op==1) }">
  				<input type="hidden" name="op" value="<%=MaterialesController.OP_ANADIR %>">
  				<input type="submit" style="width:100%;"class="btn btn-lg btn-success" value="Crear material">
  			</c:if>
  			
  			<c:if test="${ (op==2) }">
  				<input type="hidden" name="op" value="<%=MaterialesController.OP_MODIFICAR %>">
  				<input type="submit" style="width:100%;" name="op" class="btn btn-lg btn-secondary" value="Modificar">
  			</c:if>
		</form>
			<c:if test="${ (op==2) }">
				<form action="backoffice/materiales" method="post">
				<input type="hidden"  name="op" value="<%=MaterialesController.OP_BORRAR %>">
					<input type="hidden" class="form-control" name="id" value="${ material.getId() }"placeholder="">
					<input type="submit" style="width:100%;" class="btn btn-lg btn-danger" value="Borrar">
				</form>
			</c:if>
	</div>

<%@include file="/templates/footer.jsp"%>