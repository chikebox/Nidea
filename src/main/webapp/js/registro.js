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
	  			if(nombreBuscar.length>4){
	  				let promesa= ajax("GET",url);
	  		        promesa.then(data=>{nombre.innerHTML = "<p style='color:red;'>Ese usuario ya existe</p>";
	  		            	usuarioDisponible=false;
	  		            	validar(event);})
	  		        		.catch(data=>{nombre.innerHTML = "<p style='color:green;'>Usuario disponible</p>";
	  		        		usuarioDisponible=true;
	  		        		validar(event);});
	  			}
  			}
function buscarEmail(event){
		//console.log('buscarUsuario: click %o', event);
		var emailBuscar = event.target.value;
		var url = "api/email?email=" + emailBuscar;
		
		var email = document.getElementById('confirmar-email');
		//eliminar options antiguas
		email.innerHTML = "";
		
		//llamada Ajax
		if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(emailBuscar))
		  {
  				let promesa= ajax("GET",url);
  		        promesa.then(data=>{email.innerHTML = "<p style='color:red;'>Ese email ya existe</p>";
  		            	validar(event);})
  		        		.catch(error=>{email.innerHTML = "<p style='color:green;'>Email disponible</p>";
  		        		validar(event);});
		  }
		else if(email!=""){
			email.innerHTML = "<p style='color:red;'>Formato de email incorrecto</p>";
		}
	}
function validar(event){
	var password=document.getElementById("password").value;
	var passwordConfirm=document.getElementById("confirmacion").value;
	var contrasena=document.getElementById('confirmar-contrasena');
	contrasena.innerHTML="";
	if(password==""){
		boton.disabled= true;
	}
	else if(password!=passwordConfirm){
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
