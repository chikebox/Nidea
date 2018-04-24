package com.ipartek.formacion.nidea.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.ejemplos.Utilidades;
import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.model.RolDAO;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.pojo.Rol;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class UsuariosController
 */
@WebServlet("/backoffice/usuarios")
public class UsuariosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_INDEX = "usuarios/index.jsp";
	private static final String VIEW_FORM = "usuarios/form.jsp";
	public static final int OP_MOSTRAR_FORMULARIO_ANADIR = 1;
	public static final int OP_MOSTRAR_FORMULARIO_MODIFICAR = 2;
	public static final int OP_MODIFICAR = 3;
	public static final int OP_BORRAR = 4;
	public static final int OP_ANADIR = 5;
	public static final int PRECIO_EN_LETRA = -4876;
	private RequestDispatcher dispatcher;
	private Alert alert;
	private UsuarioDAO daoUsuario;
	private RolDAO daoRol;

	// parametros comunes
	private String search; // para el buscador por nombre matertial
	private int op; // operacion a realizar
	
	// parametros del Usuario
	private int id;
	private String nombre;
	private String password;
	private Rol rol;

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosController() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config)throws ServletException {
    	super.init(config);
    	daoUsuario = UsuarioDAO.getUsuarioDAO();
    	daoRol=RolDAO.getRolDAO();
    }
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub

    	daoUsuario=null;
    	daoRol=null;
    	super.destroy();
    }
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Antes de Ejecutar doGET o doPost");
		super.service(request, response);
		System.out.println("Despues de Ejecutar doGET o doPost");
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			recogerParametros(request);

			switch (op) {
			case OP_MOSTRAR_FORMULARIO_ANADIR:
				mostrarFormulario(request,OP_MOSTRAR_FORMULARIO_ANADIR);
				break;
			case OP_MOSTRAR_FORMULARIO_MODIFICAR:
				mostrarFormulario(request,OP_MOSTRAR_FORMULARIO_MODIFICAR);
				break;
			case OP_MODIFICAR:
				modificar(request);
				mostrarFormulario(request,OP_MOSTRAR_FORMULARIO_MODIFICAR);
				break;
			case OP_BORRAR:
				eliminar(request);
				break;
			case OP_ANADIR:
				guardar(request);
				break;
			default:
				listar(request);
				break;
			}

		} catch (Exception e) {
			alert = new Alert();
			e.printStackTrace();
			dispatcher = request.getRequestDispatcher(VIEW_INDEX);

		} finally {
			request.setAttribute("alert", alert);
			dispatcher.forward(request, response);
		}
	}

	private void modificar(HttpServletRequest request) {
		if(!nombre.equals("")) {
			if(password.equals("")) {
				mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_MODIFICAR);
				alert = new Alert("La password no puede ser vacía" , Alert.TIPO_WARNING);
			}
			else if(password.equals("Las passwords no coinciden, deben coincidir para poder crear el usuario")) {
				mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_MODIFICAR);
				alert = new Alert(password , Alert.TIPO_WARNING);
			}
			else {
				if(rol.getId()==-1) {
					mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
					alert = new Alert("Debe seleccionar un rol para crear" , Alert.TIPO_WARNING);
				}
				else {
					daoUsuario.modificarUsuario(id, nombre, password,rol.getId());
					
					request.setAttribute("nombre", nombre);
					request.setAttribute("password", password);
					request.setAttribute("id", id);
					
					mostrarFormulario(request, OP_MODIFICAR);
				}
				
			}
		}
		else {
			mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
			alert = new Alert("El nombre no puede ser vacío" , Alert.TIPO_WARNING);
			
		}
	
	}
	private void guardar(HttpServletRequest request) {
		Usuario usuario= new Usuario();
		request.setAttribute("usario", usuario);
			if(!nombre.equals("")) {
				if(password.equals("")) {
					mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
					alert = new Alert("La password no puede ser vacía" , Alert.TIPO_WARNING);
				}
				else if(password.equals("Las passwords no coinciden, deben coincidir para poder crear el usuario")) {
					mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
					alert = new Alert(password , Alert.TIPO_WARNING);
				}
				else {
					usuario.setPassword(password);
					if(rol.getId()==-1) {
						mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
						alert = new Alert("Debe seleccionar un usuario para crear" , Alert.TIPO_WARNING);
					}
					else {
						usuario.setNombre(nombre);
						
						usuario.setRol(rol);
						if(!daoUsuario.crear(usuario)) {
				
							usuario=daoUsuario.getUsuario(nombre, password);
							id=usuario.getId();
							mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_MODIFICAR);
							alert = new Alert("Ya existe ese usuario,¿quiere modificarlo?" , Alert.TIPO_WARNING);
						}
						else {
							listar(request);
						}
					}
				}
			}
			else {
				mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
				alert = new Alert("El nombre no puede ser vacío" , Alert.TIPO_WARNING);
				
			}
			
		
		

	}


	private void eliminar(HttpServletRequest request) {
		daoUsuario.borrar(id);
		listar(request);

	}

	private void mostrarFormulario(HttpServletRequest request, int op) {
		
		Usuario usuario = new Usuario();
		ArrayList<Rol> roles=new ArrayList<Rol>();
		if (id > -1) {
			// TODO recuperar de la BBDD que es un material que existe
			alert = new Alert("Mostramos Detall id:" + id, Alert.TIPO_WARNING);
			usuario=daoUsuario.getById(id);

		} else {
			alert = new Alert("Nuevo Producto", Alert.TIPO_WARNING);
		}
		roles=daoRol.getAll();
		request.setAttribute("op", op);
		request.setAttribute("usuario", usuario);
		request.setAttribute("roles", roles);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) {

		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		String searchText=request.getParameter("search");
		if(searchText==null) {
			searchText="";
		}
		
		usuarios = daoUsuario.getAll(searchText);
		
		request.setAttribute("usuarios", usuarios);
		dispatcher = request.getRequestDispatcher(VIEW_INDEX);

	}

	/**
	 * Recogemos todos los posibles parametros enviados
	 * 
	 * @param request
	 */
	private void recogerParametros(HttpServletRequest request) {
		if (request.getParameter("op") != null) {
			op = Integer.parseInt(request.getParameter("op"));
		} else {
			op = 0;
		}
		
		search = (request.getParameter("search") != null) ? request.getParameter("search") : "";

		if (request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
		} else {
			id = -1;
		}

		if (request.getParameter("nombre") != null) {
			
			nombre = request.getParameter("nombre");
			nombre = Utilidades.limpiarEspacios(nombre);
			nombre = nombre.substring(0, Math.min(nombre.length(), 44));
		} else {
			nombre = "";
		}
		if (request.getParameter("password") != null) {
			
			password = request.getParameter("password");
			String passConfirm=request.getParameter("pass-confirm");
			if(!password.equals(passConfirm)){
				password="Las passwords no coinciden, deben coincidir para poder crear el usuario";
			}
			
		} else {
			password = "";
		}
		
		if(request.getParameter("rol")!=null) {
			rol=daoRol.getById(Integer.parseInt(request.getParameter("rol")));
		}
		else {
			rol=new Rol();
		}
	}

}
