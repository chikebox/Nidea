<%@page import="com.ipartek.formacion.nidea.controller.frontoffice.MaterialesController"%>
<%@page import="com.ipartek.formacion.nidea.controller.MaterialesControler"%>
<%@page import="com.ipartek.formacion.nidea.pojo.Material"%>
<%@page import="com.ipartek.formacion.nidea.model.MaterialDAO"%>

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
		<form action="frontoffice/materiales"method="POST">
			<input type="hidden" name="op" value="<%= MaterialesController.OP_MOSTRAR_FORMULARIO_ANADIR %>">
			<input class="btn btn-primary"type="submit" value="Añadir nuevo material">
		</form>
	</div>
	<div class="col-xs-12 col-sm-4"></div>
	<div class="col-xs-12 col-sm-4">
		<form id="busqueda"action="frontoffice/materiales" method="get">
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
                		<th>Precio</th>
                		<th></th>
            		</tr>
        		</thead>
        		<tfoot>
            		<tr>
                		<th>Id</th>
                		<th>Nombre</th>
                		<th>Precio</th>
                		<th></th>
            		</tr>
        		</tfoot>
        		<tbody>
        			<c:forEach items="${materiales}" var="material">
        			<c:choose>
        				 <c:when test = "${(material.precio >=6) && (material.precio <25)}">
        				 	<tr>
        				 		
                				<td style=color:blue>${material.id}</td>
                				<td style=color:blue>${material.nombre} </td>
                				<td style=color:blue>${material.precio}</td>
                				<td>
                					<form action="frontoffice/materiales" method="GET">
                						<input type=hidden name="id" value="${material.id}">
                						<input type="hidden" name="op" value="<%= MaterialesController.OP_MOSTRAR_FORMULARIO_MODIFICAR %>">
                						<input type=hidden name="id" value="${material.id}">
                						<input type="submit" value="Modificar/Borrar">
                					</form>
                				<td>
                			</tr>
        				 </c:when>
        				 <c:when test="${material.precio >=25 }">
							<tr>
								
                				<td style=color:red>${material.id}</td>
                				<td style=color:red>${material.nombre} </td>
                				<td style=color:red>${material.precio}</td>
                				<td>
                					<form action="frontoffice/materiales" method="GET">
                						<input type="hidden" name="op" value="<%= MaterialesController.OP_MOSTRAR_FORMULARIO_MODIFICAR %>">
                						<input type=hidden name="id" value="${material.id}">
                						<input type="submit" value="Modificar/Borrar">
                					</form>
                				<td>
                			</tr>
                		</c:when>
                		<c:when test="${material.precio < 6}">
                			<tr>
                				<td>${material.id}</td>
                				<td>${material.nombre} </td>
                				<td>${material.precio}</td>
                				<td>
                					<form action="frontoffice/materiales" method="GET">
                						<input type="hidden" name="op" value="<%= MaterialesController.OP_MOSTRAR_FORMULARIO_MODIFICAR %>">
                						<input type=hidden name="id" value="${material.id}">
                						<input type="submit" value="Modificar/Borrar">
                					</form>
                				<td>
                				
                				
                			</tr>
                		</c:when>
        			</c:choose>
        				
						
                		
                	</c:forEach>
        		</tbody>
        
    		</table>
    	</div>
    </body>

</html>