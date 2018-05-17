d<%@include file="templates/head.jsp"%>
<%@include file="templates/navbar.jsp"%>
<jsp:include page="templates/alert.jsp"></jsp:include>

<div id="signup">

  <form class="form-signin" action="signup" method="post">     

      <div class="form-label-group">
      	<label for="username">Nombre Usuario (mínimo 5 letras))</label>
        <input type="text" class="form-control" id="username"
               name="username" 
               placeholder="Nombre Usuario" onkeyup="buscarNombre(event)"
               required autofocus>
        <div id="confirmar-nombre">
        </div>       
      </div>
      <div class="form-label-group">
      	<label for="email">Email:</label>
        <input type="text" class="form-control" id="email"
               name="email" 
               placeholder="email" onfocusout="buscarEmail(event)"
               required autofocus>
        <div id="confirmar-email">
        </div>       
      </div>

      <div class="form-label-group">
      	<label for="password">Contraseña</label>
        
        <input id=password type="password" 
               name="password" 
               class="form-control" onfocusout="validar(event)"
               placeholder="Contraseña" required>
               
        
      </div>
     
     <div class="form-label-group">
     	<label for="password">Confirmar contraseña</label>
        <input id="confirmacion" type="password" 
               name="password-confirm" 
               class="form-control" 
               placeholder="Confirmar contraseña" onfocusout="validar(event)" required>
        <div id="confirmar-contrasena">
        
        </div>
               
        
      </div>
      <button id="submit" class="btn btn-lg btn-primary btn-block" disabled type="submit">Registrar</button>
      
    </form>

</div>
<script src="js/ajax.js"></script>
<script src="js/registro.js"></script>
</body>
</html>