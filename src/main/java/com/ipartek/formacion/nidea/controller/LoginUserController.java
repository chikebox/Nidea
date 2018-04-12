package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.nidea.pojo.Alert;

/**
 * Servlet implementation class LoginUserController
 */
@WebServlet("/loginUser")
public class LoginUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<Integer, String> usuarios;
	private String view = "";
	private Alert alert = new Alert();

	private static final String USER = "admin";
	private static final String PASS = "admin";
	private static final int SESSION_EXPIRATION=60*15;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/views/usuarios/login.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//Recuperamos el contexto y el mapa donde van a estar todos los usuarios
				ServletContext context= request.getServletContext();
				usuarios=(HashMap<Integer, String>) context.getAttribute("usuarios_conectados");
				if(usuarios==null) {
					usuarios= new HashMap<Integer,String>();
				}
				
				//Recogemos los parámetros del login
				int id=Integer.parseInt(request.getParameter("id"));
				String nombre=request.getParameter("username");
				HttpSession session = request.getSession();
				session.setAttribute("usuario", id);
				
				
				/*Establecemos el tiempo que durará la sesión
				 *también se puede configurar  en el xml, y un valor negativo indica que nunca expira, es lo mejor, de hecho.
				 *
				 *<session-config>
				 *	<session-timeout>-1</session-timeout>
				 *</session-config>
				 */
				session.setMaxInactiveInterval(SESSION_EXPIRATION);
				
				//insertamos el usuario en el mapa de usuarios
				usuarios.put(id, nombre);
				
				//creamos el atributo para cargarlo en la jsp
				context.setAttribute("usuarios_conectados", usuarios);
				request.getRequestDispatcher("/views/usuarios/hola.jsp").forward(request,response);
	}

}
