package com.ipartek.formacion.nidea.migracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearUsuariosFromFileText {
	public static void main(String[] args) throws SQLException {
		System.out.println("Crear Usuarios desde un fichero de texto");
		final String URL="jdbc:mysql://localhost/nidea?user=root&password=root";
		final String SQL="INSERT INTO usuario (nombre, password, id_rol) VALUES( ? ,'123456', 2);";
		Connection con= null;
		PreparedStatement pst=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(URL);
			con.setAutoCommit(false);
			
			for(int i=0; i<5;i++) {
				 pst= con.prepareStatement(SQL);
				 pst.setString(1, "usuario"+i);
				 if(1==pst.executeUpdate()) {
					 System.out.println("usuarios insertados");
				 }
				 else {
					 System.out.println("*** "+i+"Error al insertar usuario");
				 }
			}
			con.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		finally {
			if(pst!=null) {
				pst.close();
			}
			if(con!=null) {
				con.close();
			}
		}
	}
}
