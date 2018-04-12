<%@include file="/templates/head.jsp"%>
<%@include file="/templates/navbar.jsp"%>
<jsp:include page="/templates/alert.jsp"></jsp:include>

<div id="login">

  <form class="form-signin" action="loginUser" method="post">     

      <div class="form-label-group">
        <input type="number" class="form-control"
               name="id" 
               placeholder="id" 
               required autofocus>
               
      </div>

      <div class="form-label-group">
        <input type="text" 
               name="username" 
               class="form-control" 
               placeholder="username" required>
      </div>
     
      <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
      
    </form>

</div>
<%@include file="/templates/footer.jsp"%>