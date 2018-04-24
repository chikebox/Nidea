<%@page import="com.ipartek.formacion.nidea.controller.backoffice.UsuariosController"%>
<%@include file="/templates/head.jsp"%>
<%@include file="/templates/alert.jsp" %>

<input type="hidden" name="op" value="0">
	<div class="col-xs-12 col-sm-4"></div>
	<div class="col-xs-12 col-sm-6">
		<a class="btn" href="backoffice/usuarios">Volver</a>
		<form action="backoffice/usuarios" method="post">
			<div class="form-group">
				<input type="hidden" class="form-cont" name="id" value="${ usuario.getId() }">
    			<input type="text" disabled class="form-cont" name="id" value="${ usuario.getId() }"placeholder="">
  			</div>
			<div class="form-group">
    			<label for="nombre">Nombre:</label>
	    		<input type="text" class="form-control" name="nombre" value="${ usuario.getNombre() }">
	  		</div>
	  		<div class="form-group">
    			<label for="password">Password:</label>
	    		<input type="text" class="form-cont" name="password" value="${ usuario.getPassword() }">
	  		</div>
	  		<div class="form-group">
    			<label for="pass-confirm">Confirmas-Passwword:</label>
	    		<input type="text" class="form-cont" name="pass-confirm" value="${ usuario.getPassword() }">
	  		</div>
	  		<select class="form-control"  name="rol">
  				<c:if test="${ (op==1) }">
  					<option value="${usuario.getRol().getId()}" selected>${usuario.getRol().getNombre()}</option>
    			</c:if>
    			<c:forEach items="${roles}" var="rol">
            			<option value=${rol.getId() } ${(rol.id==usuario.rol.id)?"selected":""}>${rol.getNombre() }</option>
    			</c:forEach>
				</select>
  			<c:if test="${ (op==1) }">
  				<input type="hidden" name="op" value="<%=UsuariosController.OP_ANADIR %>">
  				<input type="submit" style="width:100%;"class="btn btn-lg btn-success" value="Crear ">
  			</c:if>
  			
  			<c:if test="${ (op==2) }">
  				<input type="hidden" name="op" value="<%=UsuariosController.OP_MODIFICAR %>">
  				<input type="submit" style="width:100%;" name="op" class="btn btn-lg btn-secondary" value="Modificar">
  			</c:if>
		</form>
			<c:if test="${ (op==2) }">
				<form action="backoffice/usuarios" method="post">
				<input type="hidden"  name="op" value="<%=UsuariosController.OP_BORRAR %>">
					<input type="hidden" class="form-cont" name="id" value="${ usuario.getId() }"placeholder="">
					<button type="button" style="width:100%;" class="btn btn-lg btn-danger" data-toggle="modal" data-target="#exampleModal">
  Borrar
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" e="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" e="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">¿Eliminar ?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>¿Esta seguro de que quiere borrar el usuario?</p>
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