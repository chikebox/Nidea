package com.ipartek.formacion.nidea.filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class BackofficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, description = "Filter para dejar pasar sólo a usuarios registrados", urlPatterns = { "/backoffice/*" })
public class BackofficeFilter implements Filter {


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("BackOfficeFilter destroy");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest  req = (HttpServletRequest)  request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session=req.getSession();
		if(session.getAttribute("usuario")!=null) {
			chain.doFilter(request, response);
		}
		else {
			informacionPeticion(req);
			res.sendRedirect(req.getContextPath()+"/login");
		}
	}
	private void informacionPeticion(HttpServletRequest request) {
		System.out.println("***************ACCESO NO PERMITIO*******************");
		System.out.println("IP= "+request.getLocalAddr());
		System.out.println(request.getHeader("User-Agent"));
		
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("BackOfficeFilter init");
	}

}
