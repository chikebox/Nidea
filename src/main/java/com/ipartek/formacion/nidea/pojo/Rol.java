package com.ipartek.formacion.nidea.pojo;

public class Rol {
	String nombre;
	int id;
	public Rol() {
		this.nombre="noRol";
		this.id=-1;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
