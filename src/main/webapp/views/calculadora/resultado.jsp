<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>




<%
	float resul = (Float)request.getAttribute("resultado");	
%>		

<h1>Resultado de  la Operación <span class="badge badge-primary"><%=resul%></span> </h1>




<%@include file="/templates/footer.jsp"%>