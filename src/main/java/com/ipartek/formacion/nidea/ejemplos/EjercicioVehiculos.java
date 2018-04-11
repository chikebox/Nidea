package com.ipartek.formacion.nidea.ejemplos;

public class EjercicioVehiculos {

	public static void main(String[] args) {
		/* NO se pueden crear instancias de una clase abstracta
		Vehiculo rayoMacQueen = new Vehiculo();
		System.out.println(rayoMacQueen.toString());
		System.out.println("----------------------------------");*/
		
		VehiculoElectrico tesla= new VehiculoElectrico();
		System.out.println(tesla.toString());
		System.out.println("Tesla");
		System.out.println(tesla.getColor());
		System.out.println(tesla.getPuertas());
		tesla.arrancar();
		
	}

}
