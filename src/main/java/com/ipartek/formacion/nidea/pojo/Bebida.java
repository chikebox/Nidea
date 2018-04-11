package com.ipartek.formacion.nidea.pojo;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Bebida {
	private int id;
	@NotNull
	@Size(min = 3, max = 45)
	private String nombre;
	
	@DecimalMin("0.01")
	@DecimalMax("99999999999999.99")
	private float precio;
	public Bebida() {
		super();
		id=-1;
		nombre="";
		precio=0;
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
	
	
}
