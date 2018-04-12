package com.ipartek.formacion.nidea.ejemplos.graficos;

public class Perro implements Ordenable{
	int numVacunas;
	public Perro() {
		numVacunas=0;
	}
	public int getNumVacunas() {
		return numVacunas;
	}
	public void setNumVacunas(int numVacunas) {
		this.numVacunas = numVacunas;
	}
	@Override
	public int getValor() {
		
		return this.getNumVacunas();
	}

}
