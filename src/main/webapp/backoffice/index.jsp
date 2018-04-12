<%@page import="com.ipartek.formacion.nidea.controller.backoffice.MaterialesController"%>
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
	<h1>BackOffice</h1>
	<h2>Usuarios conectados:</h2>
	<%@include file="/templates/navbar.jsp"%>
	<c:if test="${alert!=null}">
		<div class="alert alert-danger" role="alert">
  			<p>${alert}</p>
		</div>
	
	</c:if>
	<div class="col-xs-12 col-sm-4">
	
		<div class="col-xs-12">
			<table id="example" class="table table-striped table-bordered" style="width:100%">
        		<thead>
           				<tr>
                		<th>Id</th>
                		<th>Nombre</th>

            		</tr>
        		</thead>
        		<tfoot>
            		<tr>
                		<th>Id</th>
                		<th>Nombre</th>

            		</tr>
        		</tfoot>
        		<tbody>
        			<c:forEach items="${usuarios}" var="usuario">
        			
                			<tr>
                				<td>${usuario.key}</td>
                				<td>${usuario.value} </td>
                			</tr>
        				
						
                		
                	</c:forEach>
        		</tbody>
        
    		</table>
    	</div>
    </body>

</html>