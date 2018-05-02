<%@include file="templates/head.jsp"%>
<%@include file="templates/navbar.jsp"%>
<jsp:include page="templates/alert.jsp"></jsp:include>

<div id="signup">

  <form class="form-signin" action="signup" method="post">     

      <div class="form-label-group">
      	<label for="username">Nombre Usuario</label>
        <input type="text" class="form-control" id="username"
               name="username" 
               placeholder="Nombre Usuario" onfocusout="buscarNombre(event)"
               required autofocus>
        <div id="confirmar-nombre">
        </div>       
      </div>

      <div class="form-label-group">
      	<label for="password">Contrase�a</label>
        
        <input id=password type="password" 
               name="password" 
               class="form-control" onfocusout="validar(event)"
               placeholder="Contrase�a" required>
               
        
      </div>
     
     <div class="form-label-group">
     	<label for="password">Confirmar contrase�a</label>
        <input id="confirmacion" type="password" 
               name="password-confirm" 
               class="form-control" 
               placeholder="Confirmar contrase�a" onfocusout="validar(event)" required>
        <div id="confirmar-contrasena">
        
        </div>
               
        
      </div>
      <button id="submit" class="btn btn-lg btn-primary btn-block" disabled type="submit">Registrar</button>
      
    </form>

</div>
<script src="js/registro.js"></script>
</body>
</html>