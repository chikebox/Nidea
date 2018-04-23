package com.ipartek.formacion.nidea.Listenner;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ipartek.formacion.nidea.pojo.Usuario;

/**
 * Application Lifecycle Listener implementation class UsuariosConectadosListener
 *
 */
@WebListener
public class UsuariosConectadosListener implements HttpSessionAttributeListener, HttpSessionListener {

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext context =se.getSession().getServletContext();
        HashMap<Integer, String> usuarios=(HashMap<Integer, String>) context.getAttribute("usuarios_conectados");
        Usuario usuario=(Usuario) se.getSession().getAttribute("uPublic");
        if(usuario!=null) {
        	usuarios.remove(usuario.getId());
        }
        
         
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event)  { 
         ServletContext context =event.getSession().getServletContext();
         HashMap<Integer, String> usuarios=(HashMap<Integer, String>) context.getAttribute("usuarios_conectados");
         if(usuarios==null) {
				usuarios= new HashMap<Integer,String>();
         }
         Usuario usuario= new Usuario();
         if(event.getName().equals("uPublic")) {
        	usuario=(Usuario) event.getSession().getAttribute("uPublic");
        	//insertamos el usuario en el mapa de usuarios
			usuarios.put(usuario.getId(),usuario.getNombre());	
			//creamos el atributo para cargarlo en la jsp
         }
         context.setAttribute("usuarios_conectados", usuarios);		
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }
	
}
