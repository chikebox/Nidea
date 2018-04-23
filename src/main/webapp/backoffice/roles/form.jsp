<%@page import="com.ipartek.formacion.nidea.controller.backoffice.RolesController"%>
<%@include file="/templates/head.jsp"%>
<%@include file="/templates/alert.jsp" %>

<input type="hidden" name="op" value="0">
	<div class="col-xs-12 col-sm-4"></div>
	<div class="col-xs-12 col-sm-6">
		<a class="btn" href="backoffice/roles">Volver</a>
		<form action="backoffice/roles" method="post">
			<div class="form-group">
				<input type="hidden" class="form-control" name="id" value="${ rol.getId() }">
    			<input type="text" disabled class="form-control" name="id" value="${ rol.getId() }"placeholder="">
  			</div>
			<div class="form-group">
    			<label for="nombre">Nombre:</label>
	    		<input type="text" class="form-control" name="nombre" value="${ rol.getNombre() }">
	  		</div>
  			<c:if test="${ (op==1) }">
  				<input type="hidden" name="op" value="<%=RolesController.OP_ANADIR %>">
  				<input type="submit" style="width:100%;"class="btn btn-lg btn-success" value="Crear Rol">
  			</c:if>
  			
  			<c:if test="${ (op==2) }">
  				<input type="hidden" name="op" value="<%=RolesController.OP_MODIFICAR %>">
  				<input type="submit" style="width:100%;" name="op" class="btn btn-lg btn-secondary" value="Modificar">
  			</c:if>
		</form>
			<c:if test="${ (op==2) }">
				<form action="backoffice/roles" method="post">
				<input type="hidden"  name="op" value="<%=RolesController.OP_BORRAR %>">
					<input type="hidden" class="form-control" name="id" value="${ rol.getId() }"placeholder="">
					<button type="button" style="width:100%;" class="btn btn-lg btn-danger" data-toggle="modal" data-target="#exampleModal">
  Borrar
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">¿Eliminar rol?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>¿Esta seguro de que quiere borrar el rol?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
        <input type="submit" style="width:100%;" class="btn btn-danger" value="Borrar">
      </div>
    </div>
  </div>
</div>
					
				</form>
			</c:if>
	</div>

<%@include file="/templates/footer.jsp"%>