package com.ipartek.formacion.nidea.model;

import java.util.ArrayList;

public interface Persistible<P> {
	public ArrayList<P> getAll();
	public P getById(int id);
	public void crear(P p);
	public void borrar(int id);

}
