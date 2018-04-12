package com.ipartek.formacion.nidea.ejemplos.graficos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrdenacionTest {
	static ArrayList<Ordenable> lista= new ArrayList<Ordenable>();
	static Perro perro1;
	static Perro perro2;
	static Mueble mueble1;
	static Mueble mueble2;
	@BeforeClass
	public static void crearLista() {
		lista= new ArrayList<Ordenable>();
		perro1= new Perro();
		perro2= new Perro();
		mueble1=new Mueble();
		mueble2=new Mueble();
		
		perro1.setNumVacunas(0);
		lista.add(perro1);
		
		perro2.setNumVacunas(1);
		lista.add(perro2);
		
		mueble1.setNumPatas(3);
		lista.add(mueble1);
		
		mueble2.setNumPatas(9);
		lista.add(mueble2);
	}
	@AfterClass
	public static void borrarLista() {
		perro1=null;
		perro2=null;
		mueble1=null;
		mueble2=null;
		lista=null;
	}
	@Test
	public void ordenar() {
		Comparator<Ordenable> comparator= new Comparator<Ordenable>() {

			@Override
			public int compare(Ordenable o1, Ordenable o2) {
				if (o1.getValor() > o2.getValor())
		            return 1;
		        if (o1.getValor() < o2.getValor())
		            return -1;
		        return 0;
			}
		};
		lista.sort(comparator);
		assertEquals(0,lista.get(0).getValor());
		assertEquals(1,lista.get(1).getValor());
		assertEquals(3,lista.get(2).getValor());
		assertEquals(9,lista.get(3).getValor());
		
	}

}
