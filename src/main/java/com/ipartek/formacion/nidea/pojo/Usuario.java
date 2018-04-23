package com.ipartek.formacion.nidea.pojo;

public class Usuario {
	int id;
	String nombre;
	String password;
	Rol rol;
	public Usuario() {
		super();
		id=-1;
		nombre="Elija un usuario para crear";
		password="";
		rol=new Rol();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
