package com.ipartek.formacion.nidea.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.nidea.model.MaterialDAO;
import com.ipartek.formacion.nidea.pojo.Alert;
import com.ipartek.formacion.nidea.pojo.Material;

/**
 * Servlet implementation class MaterialesControler
 */
@WebServlet("/materiales")
public class MaterialesControler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaterialesControler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Material> materiales= new ArrayList<Material>();
		Alert alert=new Alert();
		try {
			MaterialDAO dao = MaterialDAO.getMiMaterialDAO();
			materiales=dao.getAll();
			
		}
		catch (Exception e){
			alert.setMensaje("Ha ocurrido un problema, vuelva a intentarlo más tarde, intentaremos resolverlo lo antes posible");
			e.printStackTrace();
		}
		finally {
			request.setAttribute("alert", alert.getMensaje());
			request.setAttribute("materiales", materiales);
			request.getRequestDispatcher("views/materiales/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
