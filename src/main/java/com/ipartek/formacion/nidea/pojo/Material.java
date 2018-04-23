package com.ipartek.formacion.nidea.pojo;

public class Material {

	private int id;
	private String nombre;
	private float precio;
	private Usuario usuario;

	public Material() {
		super();
		this.id = -1;
		this.nombre = "";
		this.precio = 0f;
		usuario=new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", usuario=" + usuario + "]";
	}


}
