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

import com.ipartek.formacion.nidea.model.RolDAO;
import com.ipartek.formacion.nidea.pojo.Alert;

import com.ipartek.formacion.nidea.pojo.Rol;

/**
 * Servlet implementation class RolesController
 */
@WebServlet("/backoffice/roles")
public class RolesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_INDEX = "roles/index.jsp";
	private static final String VIEW_FORM = "roles/form.jsp";
	public static final int OP_MOSTRAR_FORMULARIO_ANADIR = 1;
	public static final int OP_MOSTRAR_FORMULARIO_MODIFICAR = 2;
	public static final int OP_MODIFICAR = 3;
	public static final int OP_BORRAR = 4;
	public static final int OP_ANADIR = 5;
	public static final int PRECIO_EN_LETRA = -4876;
	private RequestDispatcher dispatcher;
	private Alert alert;
	private RolDAO dao;

	// parametros comunes
	private String search; // para el buscador por nombre matertial
	private int op; // operacion a realizar
	
	// parametros del Material
	private int id;
	private String nombre;

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RolesController() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config)throws ServletException {
    	super.init(config);
    	dao = RolDAO.getRolDAO();
    }
    @Override
    public void destroy() {
    	// TODO Auto-generated method stub
    	dao=null;
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
			
				dao.modificarRol(id, nombre);
				request.setAttribute("nombre", nombre);
				request.setAttribute("id", id);
				mostrarFormulario(request, OP_MODIFICAR);
		}
		else {
			mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
			alert = new Alert("El nombre no puede ser vacío" , Alert.TIPO_WARNING);
			
		}
	
	}
	private void guardar(HttpServletRequest request) {
		Rol rol= new Rol();
		rol.setNombre(nombre);
		
		request.setAttribute("rol", rol);
			if(!nombre.equals("")) {
					if(!dao.crear(rol)) {
				
						rol=dao.getByNombre(nombre);
						id=rol.getId();
						mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_MODIFICAR);
						alert = new Alert("Ya existe ese Rol,¿quiere modificarlo?" , Alert.TIPO_WARNING);
					}
					else {
						listar(request);
					}
				
			}
			else {
				mostrarFormulario(request, OP_MOSTRAR_FORMULARIO_ANADIR);
				alert = new Alert("El nombre no puede ser vacío" , Alert.TIPO_WARNING);
				
			}
			
		
		

	}


	private void eliminar(HttpServletRequest request) {
		dao.borrar(id);
		listar(request);

	}

	private void mostrarFormulario(HttpServletRequest request, int op) {
		
		Rol rol = new Rol();
		if (id > -1) {
			// TODO recuperar de la BBDD que es un material que existe
			alert = new Alert("Mostramos Detall id:" + id, Alert.TIPO_WARNING);
			rol=dao.getById(id);

		} else {
			alert = new Alert("Nuevo Producto", Alert.TIPO_WARNING);
		}
		request.setAttribute("op", op);
		request.setAttribute("rol", rol);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) {

		ArrayList<Rol> roles = new ArrayList<Rol>();
		String searchText=request.getParameter("search");
		if(searchText==null) {
			searchText="";
		}
		
		roles = dao.getAll(searchText);
		
		request.setAttribute("roles", roles);
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


	}
}
