package com.ipartek.formacion.nidea.controller.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ipartek.formacion.nidea.model.UsuarioDAO;
import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Servlet implementation class ApiEmailController
 */
@WebServlet("/api/email")
public class ApiEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UsuarioDAO daoUsuario=UsuarioDAO.getUsuarioDAO();   
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String email= request.getParameter("email");
		
		if(email==null) {
			email="";
		}
		Usuario usuario= daoUsuario.getByEmail(email);
		if(usuario.getId() ==-1) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}else {
			//por defecto siempre retorna 200=SC_OK
		}
		out.print(new Gson().toJson(usuario));
		out.flush();
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
