<%@include file="templates/head.jsp"%>
<%@include file="templates/navbar.jsp"%>

<%
	// Scriplet < %  ...   % >
	// varias sentencias 
	String nombre = "pepe";
	String hora = "10:78";

%>

<h2>Hello <%=nombre%></h2>
<p><%=hora%></p>

<a href="generar-mesa"> �Quieres Comprar una Mesa ?</a>


<%@include file="templates/footer.jsp"%>