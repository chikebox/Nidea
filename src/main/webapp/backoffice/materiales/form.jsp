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
  			<div class="input-group">
  			<c:if test="${ (op==2) }">
	    	<div class="col">
		    	<input type="text" value="${material.usuario.nombre}" disabled readonly />
	    	</div>
	    	</c:if>
	  		<div class="col">
	  	  		<p>Elegir usuario</p>
	      		<input type="search" id="search" placeholder="Nombre usuario" onkeyup="buscarUsuario(event)">
	      		<input type="hidden" name="id_usuario" value="${material.usuario.id}">
	      		<select id="sUsuarios" name="id_usuario_cambio"></select>
	     	</div> 
	     	</div>
  			<script>
  			function buscarUsuario( event ){
	  			//console.log('buscarUsuario: click %o', event);
	  			var nombreBuscar = event.target.value;
	  			var url = "api/usuario?nombre=" + nombreBuscar;
	  			
	  			var options = '';
	  			var select = document.getElementById('sUsuarios');
	  			//eliminar options antiguas
	  			select.innerHTML = "";
	  			
	  			//llamada Ajax
	  			if(nombreBuscar!=""){
	  				var xhttp = new XMLHttpRequest();
	  		    	xhttp.onreadystatechange = function() {
	  		    		//llamada terminada y correcta
	  		        	if (this.readyState == 4 && this.status == 200) {
	  		        		var data = JSON.parse(this.responseText);
	  		            	console.log('retorna datos %o', data);
	  		            	data.forEach( el => {
	  		            		options += '<option value="'+ el.id + '">'+el.nombre+'</option>';
	  		            	});
	  		            	select.innerHTML = options;
	  		       		}
	  		    	};
	  		   		xhttp.open("GET", url , true);
	  		    	xhttp.send(); 
	  			}
  			}
  			</script>
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
					<button type="button" style="width:100%;" class="btn btn-lg btn-danger" data-toggle="modal" data-target="#exampleModal">
  Borrar
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">¿Eliminar material?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>¿Esta seguro de que quiere borrar el material?</p>
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