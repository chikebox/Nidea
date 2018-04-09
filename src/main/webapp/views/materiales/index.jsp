<%@page import="com.ipartek.formacion.nidea.pojo.Material"%>
<%@page import="com.ipartek.formacion.nidea.model.MaterialDAO"%>

<%@page import="java.util.ArrayList"%>
<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>
<ol>
	<c:forEach items="${materiales}" var="material">
    	<c:choose>
		<c:when test = "${(material.precio >=6) && (material.precio <25)}">

			
			<li style=color:blue>${material.nombre} - ${material.precio} </li>

		</c:when>
        <c:when test="${material.precio >=25 }">

			<li style=color:red>${material.nombre} - ${material.precio} </li>

        </c:when>
        <c:when test="${material.precio < 6}">

			<li>${material.nombre} - ${material.precio} </li>

        </c:when>
        </c:choose>
    </c:forEach>
</ol>
</body>

</html>