package com.ipartek.formacion.nidea.pojo;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;

public class BebidaTest {
	Bebida bebida= new Bebida();
	@Test
	public void validar() {
		try {
			//Crear Factoria y Validador
			 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			 Validator validator = factory.getValidator();

			// Crear Objeto a validar
			 Bebida bebida = new Bebida();

			//Obtener las ConstrainViolation
			Set<ConstraintViolation<Bebida>> violations = validator.validate(bebida);
			assertTrue(violations.size()==2);
			bebida.setPrecio(1);
			violations = validator.validate(bebida);
			assertTrue(violations.size()==1);
			bebida.setNombre("oca");
			violations = validator.validate(bebida);
			assertTrue(violations.size()==0);
			String nombre="";
			for(int i=1;i<=45;i++) {
				nombre=nombre+"a";
			}
			bebida.setNombre(nombre);
			violations = validator.validate(bebida);
			assertTrue(violations.size()==0);
			for(int i=0;i<=46;i++) {
				nombre=nombre+"a";
			}
			bebida.setNombre(nombre);
			violations = validator.validate(bebida);
			assertEquals(1, violations.size());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
