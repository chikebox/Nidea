package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
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
 * Servlet implementation class SignUpController
 */
@WebServlet("/signup")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String view = "";
	private Alert alert = new Alert();

	UsuarioDAO dao;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		request.getRequestDispatcher("signup.jsp").forward(request, response);

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
		Usuario user= new Usuario();
		String nombreUsuario = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm=request.getParameter("password-confirm");
		if(password!=null && passwordConfirm!=null) {
			if(password.equals(passwordConfirm)) {
				user= dao.getByName(nombreUsuario);
				if (user.getId()!=-1) {
					view = "signup.jsp";
					alert = new Alert("Credenciales incorrectas, prueba de nuevo");
				}
				else {
					user.setNombre(nombreUsuario);
					user.setPassword(password);
					if(!dao.registrar(user)) {
						
						view = "signup.jsp";
						alert = new Alert("Credenciales incorrectas, prueba de nuevo");
						
					}
					else {
						view = "login.jsp";
						alert = new Alert("Se ha registrado con éxito",Alert.TIPO_PRIMARY);
					}
					
					
				}

			
			}
			else {
				view = "signup.jsp";
				alert = new Alert("Credenciales incorrectas, prueba de nuevo");
			}
		}
		request.setAttribute("alert", alert);
		request.getRequestDispatcher(view).forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
