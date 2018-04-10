package com.ipartek.formacion.nidea.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Persistible<P> {
	public ArrayList<P> getAll();
	public P getById(int id);
	public boolean crear(P p);
	public void borrar(int id);
	/** nos mapea un resultado de la base de datos a un pojo
	 * @param rs ResulSet 1 registro de la consulta
	 * @return Pojo con los valores del ResulSet o null si no hay valores 
	 **/
	
	public P mapper (ResultSet rs) throws SQLException;
}
