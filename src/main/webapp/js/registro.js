var boton=document.getElementById("submit");
var usuarioDisponible;
function buscarNombre( event ){
	  			//console.log('buscarUsuario: click %o', event);
	  			var nombreBuscar = event.target.value;
	  			var url = "api/usuario?nombre=" + nombreBuscar;
	  			
	  			var nombre = document.getElementById('confirmar-nombre');
	  			//eliminar options antiguas
	  			nombre.innerHTML = "";
	  			
	  			//llamada Ajax
	  			if(nombreBuscar!=""){
	  				var xhttp = new XMLHttpRequest();
	  		    	xhttp.onreadystatechange = function() {
	  		    		//llamada terminada y correcta
	  		        	if (this.readyState == 4 && this.status == 200) {
	  		        		
	  		            	nombre.innerHTML = "<p style='color:red;'>Ese usuario ya existe</p>";
	  		            	usuarioDisponible=false;
	  		            	validar(event);
	  		            	
	  		       		}
	  		        	if (this.readyState == 4 && this.status == 204){
	  		        		usuarioDisponible=true;
	  		        		validar(event);
	  		        	}
	  		    	};
	  		   		xhttp.open("GET", url , true);
	  		    	xhttp.send(); 
	  			}
  			}
function validar(event){
	var nombre=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	var passwordConfirm=document.getElementById("confirmacion").value;
	var contrasena=document.getElementById('confirmar-contrasena');
	contrasena.innerHTML="";
	if(password!=passwordConfirm && password!=""){
		contrasena.innerHTML = "<p style='color:red;'>Las passwords deben coincidir</p>";
		boton.disabled= true;
	}
	else{
		if(usuarioDisponible){
			boton.disabled= false;
			
		}
		else{
			boton.disabled= true;
		}
	}
	
	
	
}
