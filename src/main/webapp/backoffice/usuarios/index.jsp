<%@page import="com.ipartek.formacion.nidea.controller.backoffice.UsuariosController"%>
<%@page import="com.ipartek.formacion.nidea.pojo.Rol"%>
<%@page import="com.ipartek.formacion.nidea.model.RolDAO"%>

<%@page import="java.util.ArrayList"%>
<%@include file="/templates/head.jsp"%>

<head>
    <meta charset="UTF-8">
    <title>DataTables</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#example').DataTable({
                "language": {
                    "sProcessing": "Procesando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "No se encontraron resultados",
                    "sEmptyTable": "Ningún dato disponible en esta tabla",
                    "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
                    "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "sInfoThousands": ",",
                    "sLoadingRecords": "Cargando...",
                    "oPaginate": {
                        "sFirst": "Primero",
                        "sLast": "Último",
                        "sNext": "Siguiente",
                        "sPrevious": "Anterior"
                    },
                    "oAria": {
                        "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                        "sSortDescending": ": Activar para ordenar la columna de manera descendente"
                    }
                }
            });
        });
    </script>
</head>


	<body>
	
	<%@include file="/templates/navbar.jsp"%>
	<c:if test="${alert!=null}">
		<div class="alert alert-danger" role="alert">
  			<p>${alert}</p>
		</div>
	
	</c:if>
	<div class="col-xs-12 col-sm-4">
		<form action="backoffice/usuarios"method="POST">
			<input type="hidden" name="op" value="<%= UsuariosController.OP_MOSTRAR_FORMULARIO_ANADIR %>">
			<input class="btn btn-primary"type="submit" value="Añadir nuevo Usuario">
		</form>
	</div>
	<div class="col-xs-12 col-sm-4"></div>
	<div class="col-xs-12 col-sm-4">
		<form id="busqueda"action="backoffice/usuarios" method="get">
			<div class="input-group">
  				<input class="form-control" name="search" placeholder="Buscar por nombre">
  				 <div class="input-group-addon" onclick="document.getElementById('busqueda').submit()" style="width:50px;" ><i class="fa fa-search"></i></div>
			</div>
		</form>
	</div>
	
		<div class="col-xs-12">
			<table id="example" class="table table-striped table-bordered" style="width:100%">
        		<thead>
           				<tr>
                		<th>Id</th>
                		<th>Nombre</th>
                		<th>Rol</th>
                		<th></th>
            		</tr>
        		</thead>
        		<tfoot>
            		<tr>
                		<th>Id</th>
                		<th>Nombre</th>
                		<th>Rol</th>
                		<th></th>
            		</tr>
        		</tfoot>
        		<tbody>
        			<c:forEach items="${usuarios}" var="usuario">
        			
        				 	<tr>
        				 		
                				<td>${usuario.id}</td>
                				<td>${usuario.nombre} </td>
                				<td>${usuario.getRol().getNombre() } </td>
                				<td>
                					<form action="backoffice/usuarios" method="GET">
                						<input type=hidden name="id" value="${usuario.id}">
                						<input type="hidden" name="op" value="<%= UsuariosController.OP_MOSTRAR_FORMULARIO_MODIFICAR %>">
                						<input type=hidden name="id" value="${usuario.id}">
                						<input type="submit" value="Modificar/Borrar">
                					</form>
                				<td>
                			</tr>

        				
							
                	</c:forEach>
        		</tbody>
        
    		</table>
    	</div>
    </body>

</html>