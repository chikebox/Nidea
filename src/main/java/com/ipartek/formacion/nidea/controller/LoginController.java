package com.ipartek.formacion.nidea.controller;

import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	private static final int SESSION_EXPIRATION=60*15;
	UsuarioDAO dao;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		request.getRequestDispatcher("login.jsp").forward(request, response);

	}
	public void init()throws ServletException{
		dao= UsuarioDAO.getUsuarioDAO();
		super.init();
	}
	public void destroy(){
		dao=null;
		super.destroy();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	try {
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		Usuario user=dao.getUsuario(usuario, password);
		if (!"Elija un usuario para crear".equals(user.getNombre())) {

			// guardar usuario en session
			HttpSession session = request.getSession();
			session.setAttribute("usuario", user);

			/*
			 * Tiempo expiracion session, tambien se puede configurar web.xml un valor
			 * negativo, indica que nunca expira
			 * 
			 * <session-config> <session-timeout>-1</session-timeout> </session-config>
			 * 
			 */
			session.setMaxInactiveInterval(SESSION_EXPIRATION);
			if(user.getRol().getId()==1) {
				// enviar como atributo la lista de materiales
				MaterialDAO dao = MaterialDAO.getMiMaterialDAO();
				request.setAttribute("materiales", dao.getAll());
				view = "backoffice/index.jsp";

				alert = new Alert("Ongi Etorri", Alert.TIPO_PRIMARY);
			}
			else {
				view = "/views/usuarios/hola.jsp";
			}
		} 
		
		else {

			view = "login.jsp";
			alert = new Alert("Credenciales incorrectas, prueba de nuevo");
		}

	} catch (Exception e) {
		e.printStackTrace();
		view = "login.jsp";
		alert = new Alert();

	} finally {
		request.setAttribute("alert", alert);
		request.getRequestDispatcher(view).forward(request, response);
	}

	}

}
