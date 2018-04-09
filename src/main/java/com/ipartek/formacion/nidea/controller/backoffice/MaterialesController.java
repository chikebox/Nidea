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

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;

/**
 * Servlet implementation class MaterialesController
 */
@WebServlet("/backoffice/materiales")
public class MaterialesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW_INDEX = "materiales/index.jsp";
	private static final String VIEW_FORM = "materiales/form.jsp";
	public static final int OP_MOSTRAR_FORMULARIO_ANADIR = 1;
	public static final int OP_MOSTRAR_FORMULARIO_MODIFICAR = 2;
	public static final int OP_MODIFICAR = 3;
	public static final int OP_BORRAR = 4;
	public static final int OP_ANADIR = 5;
	private RequestDispatcher dispatcher;
	private Alert alert;
	private MaterialDAO dao;

	// parametros comunes
	private String search; // para el buscador por nombre matertial
	private int op; // operacion a realizar
	// parametros del Material
	private int id;
	private String nombre;
	private float precio;

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialesController() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init(ServletConfig config)throws ServletException {
    	super.init(config);
    	dao = MaterialDAO.getMiMaterialDAO();
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
		dao.modificarMaterial(nombre, precio,id);
		request.setAttribute("nombre", nombre);
		request.setAttribute("precio", precio);
		request.setAttribute("id", id);
		mostrarFormulario(request, OP_MODIFICAR);

	}
	private void guardar(HttpServletRequest request) {
		Material material= new Material();
		material.setNombre(nombre);
		material.setPrecio(precio);
		dao.crear(material);
		listar(request);

	}


	private void eliminar(HttpServletRequest request) {
		dao.borrar(id);
		listar(request);

	}

	private void mostrarFormulario(HttpServletRequest request, int op) {
		
		Material material = new Material();
		if (id > -1) {
			// TODO recuperar de la BBDD que es un material que existe
			alert = new Alert("Mostramos Detall id:" + id, Alert.TIPO_WARNING);
			material=dao.getById(id);

		} else {
			alert = new Alert("Nuevo Producto", Alert.TIPO_WARNING);
		}
		request.setAttribute("op", op);
		request.setAttribute("material", material);
		dispatcher = request.getRequestDispatcher(VIEW_FORM);
	}

	private void listar(HttpServletRequest request) {

		ArrayList<Material> materiales = new ArrayList<Material>();
		String searchText=request.getParameter("search");
		if(searchText==null) {
			searchText="";
		}
		
		materiales = dao.getAll(searchText);
		
		request.setAttribute("materiales", materiales);
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
		} else {
			nombre = "";
		}

		if (request.getParameter("precio") != null) {
			precio = Float.parseFloat(request.getParameter("precio"));
		} else {
			precio = 0;
		}

	}

}
