package com.ipartek.formacion.nidea.ejemplos.graficos;

public class Mueble implements Ordenable{
	int numPatas;
	public Mueble() {
		numPatas=0;
	}
	public int getNumPatas() {
		return numPatas;
	}
	public void setNumPatas(int numPatas) {
		this.numPatas = numPatas;
	}
	@Override
	public int getValor() {
		
		return this.getNumPatas();
	}

}
