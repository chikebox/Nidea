package com.ipartek.formacion.nidea.migracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class LeerFicheroTxt {
	
	private static final String FILENAME = "C:\\Desarrollo\\jee-oxigen\\workspace\\nidea\\doc\\personas.txt";
	private static final int MAXIMO_EDAD_PERSONA=115;
	private static final int MAYOR_EDAD=18;
	private static final int NUM_CAMPOS_CORRECTO=7;
	
	public static void main(String[] args) throws SQLException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
			
			//datos útiles para usuarios
			String nombre;
			int edad;
			String email;
			
			//lineasTotales
			int totalLineas=0;
			
			//contadores de errores
			int erroresTotales=0;
			int inserciones=0;
			int lineasErroneas=0;
			int menoresEdad=0;
			int duplicados=0;
			int emailsErroneos=0;
			int edadesErroneas=0;
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				totalLineas++;
				String[] cadenas= sCurrentLine.split(",");
				
				//comprobación de campos
				if(cadenas.length==NUM_CAMPOS_CORRECTO) {
					
					//Recogida de nombre
					nombre=recogerNombre(cadenas);
					
					//Recogida y Comprobación de la edad
					edad=recogerEdad(cadenas);
					if(edad<MAYOR_EDAD) {
						menoresEdad++;
					}
					else if(edad>MAXIMO_EDAD_PERSONA) {
						edadesErroneas++;
						
					}
					else {
					
						//Recogida y Comprobación del email
						email=recogerEmail(cadenas);
						if(email.equals("")) {
							emailsErroneos++;
						}
						else {
							//comprobación de los duplicados.
							if(insertar(nombre,email)) {
								
								inserciones++;
							}
							else {
								duplicados++;
							}
						}
						
					}
				}
				else {
					lineasErroneas++;
				}
			}
			
			//Resumen
			System.out.println("Datos insertados:"+inserciones);
			System.out.println("Lineas erroneas (7 campos):"+lineasErroneas);
			System.out.println("Menores de edad:"+menoresEdad);
			System.out.println("Edades en formato erroneo:"+edadesErroneas);
			System.out.println("Duplicados:"+duplicados);
			System.out.println("Emails en formato erroneo:" + emailsErroneos);
			System.out.println("----------------------------------------------");
			erroresTotales=lineasErroneas+menoresEdad+duplicados+inserciones+emailsErroneos+edadesErroneas;
			System.out.println("Lineas insertadas + erroneas / Lineas leidas:"+erroresTotales+" / "+totalLineas);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * 
	 * @param cadenas
	 * @return nombre (los tres primeros campos de cadenas)
	 */
	private static String recogerNombre(String[] cadenas) {
		String nombre="";
		for(int i=0;i<=2;i++) {
			nombre=nombre+" "+cadenas[i];
		}
		return nombre;
	}
	
	
	/**
	 * Si no sigue un formato correcto, lo devuelve vacío.
	 * 
	 * @param cadenas
	 * @return email
	 */
	private static String recogerEmail(String[] cadenas) {
		String email;
		email=cadenas[4];
		Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(email);
		if(!matcher.find()) {
			email="";
		}
		return email;

		
	}
	
	
	/**
	 * Si la edad no está en formato correcto, devuelve una edad muy mayor para saber que no está bien escrita.
	 * @param cadenas
	 * @return edad
	 */
	private static int recogerEdad(String[] cadenas) {
		int edad;
		try {
			edad=Integer.parseInt(cadenas[3]);
		}
		catch(Exception e){
			edad=130;
		}
		
		return edad;
	}
	
	/**
	 * 
	 * @param nombre
	 * @param email
	 * @return boolean (True si ha ido bien, False si no ha podido insertar por duplicidad.
	 * @throws SQLException
	 */
	private static boolean insertar(String nombre,String email) throws SQLException {
		boolean todoBien=true;
		final String URL="jdbc:mysql://localhost/nidea?user=root&password=root";
		final String SQL="INSERT INTO usuario (nombre, password, id_rol, email) VALUES( ? ,'123456', 2, ?);";
		Connection con= null;
		PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL);
			con.setAutoCommit(false);
			
			
				 pst= con.prepareStatement(SQL);
				 pst.setString(1, nombre);
				 pst.setString(2, email);
				 if(1==pst.executeUpdate()) {
					 System.out.println("usuarios insertados");
				 }
				 else {
					 System.out.println("*** Error al insertar usuario");
				 }
			
			con.commit();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			con.rollback();
			todoBien=false;
		}
		finally {
			if(pst!=null) {
				pst.close();
			}
			if(con!=null) {
				con.close();
			}
			
		}
		return todoBien;
	}

}
