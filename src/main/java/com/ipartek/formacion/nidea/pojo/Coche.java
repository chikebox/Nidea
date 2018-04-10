package com.ipartek.formacion.nidea.pojo;

public class Coche implements AutoCloseable{
	
	public Coche() {
		super();
		System.out.println("Creamos el coche");
	}
	
	public void conducir() {
		System.out.println("Brum brum estamos conduciendo");
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("Paramos coche");
		
	}
	
	public static void main(String[] args) {
		// si declaramos un objeto que implemente la interfaz autocloseable dentro de los parentesis de try
		// cuando llega al finally, se ejecuta de forma automática el método close.
		try (Coche c=new Coche()){
			System.out.println("Empezamos programa");
			c.conducir();
		}
		catch(Exception e) {
			System.out.println("Tenemos una excepción");
		}
		finally {
			System.out.println("Finalizamos");
		}
	}

}
