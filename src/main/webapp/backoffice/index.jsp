<%@page import="com.ipartek.formacion.nidea.pojo.Material"%>
<%@page import="com.ipartek.formacion.nidea.model.MaterialDAO"%>

<%@page import="java.util.ArrayList"%>
<%@include file="/templates/head.jsp"%>

<head>
    <meta charset="UTF-8">
    <title>BackOffice</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/r/bs-3.3.5/jq-2.1.4,dt-1.10.8/datatables.min.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/custom.css">
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
		<div>
			<table id="example" class="table table-striped table-bordered" style="width:100%">
        		<thead>
           				<tr>
                		<th>Id</th>
                		<th>Nombre</th>
                		<th>Precio</th>
            		</tr>
        		</thead>
        		<tfoot>
            		<tr>
                		<th>Id</th>
                		<th>Nombre</th>
                		<th>Precio</th>
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
                			</tr>
        				 </c:when>
        				 <c:when test="${material.precio >=25 }">
							<tr>
                				<td style=color:red>${material.id}</td>
                				<td style=color:red>${material.nombre} </td>
                				<td style=color:red>${material.precio}</td>
                			</tr>
                		</c:when>
                		<c:when test="${material.precio < 6}">
                			<tr>
                				<td>${material.id}</td>
                				<td>${material.nombre} </td>
                				<td>${material.precio}</td>
                			</tr>
                		</c:when>
        			</c:choose>
        				
						
                		
                	</c:forEach>
        		</tbody>
        
    		</table>
    	</div>
    </body>

</html>