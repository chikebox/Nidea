package com.ipartek.formacion.nidea.ejemplos;

public class VehiculoElectrico extends Vehiculo{
	private float potencia; //en KW
	@Override
	public String toString() {
		return "VehiculoElectrico [potencia=" + potencia + "]";
	}
	public VehiculoElectrico() {
		super();
		this.setPotencia(0.0f);
	}
	public VehiculoElectrico(float potencia) {
		this();
		this.setPotencia(potencia);
	}
	public float getPotencia() {
		return potencia;
	}
	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}
	@Override
	public void arrancar() {
		System.out.println("Pulsar boton encendido");
	}
}
