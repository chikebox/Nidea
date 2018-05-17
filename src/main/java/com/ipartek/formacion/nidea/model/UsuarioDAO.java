package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.pojo.Rol;
import com.ipartek.formacion.nidea.pojo.Usuario;

public class UsuarioDAO implements Persistible<Usuario>{
	private static UsuarioDAO usuarioDAO;
	private UsuarioDAO() {
		
	}
	private synchronized static void createInstance() {
		if(usuarioDAO==null) {
			usuarioDAO=new UsuarioDAO();
		}
	}
	public static UsuarioDAO getUsuarioDAO() {
		if(usuarioDAO==null) {
			createInstance();
		}
		return usuarioDAO;
	}
	@Override
	public Usuario getById(int id) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario=new Usuario();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id WHERE u.id= ?;";
		

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);) {
				pst.setInt(1, id);
				try(ResultSet rs = pst.executeQuery();){
					while (rs.next()) {
						lista.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!lista.isEmpty())usuario=lista.get(0);

		return usuario;
	}
	public Usuario getByNombre(String nombre) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario=new Usuario();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id WHERE u.nombre= ?;";
		

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);) {
				pst.setString(1, nombre);
				try(ResultSet rs = pst.executeQuery();){
					while (rs.next()) {
						lista.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!lista.isEmpty())usuario=lista.get(0);

		return usuario;
	}
	public Usuario getByEmail(String email) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario=new Usuario();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id WHERE u.email= ?;";
		

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);) {
				pst.setString(1, email);
				try(ResultSet rs = pst.executeQuery();){
					while (rs.next()) {
						lista.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!lista.isEmpty())usuario=lista.get(0);

		return usuario;
	}
	public Usuario getUsuario(String nombre, String password) {

		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario usuario=new Usuario();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id WHERE u.nombre= ? AND u.password= ? ;";
		

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);) {
				pst.setString(1, nombre);
				pst.setString(2, password);
				try(ResultSet rs = pst.executeQuery();){
					while (rs.next()) {
						lista.add(mapper(rs));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(!lista.isEmpty())usuario=lista.get(0);

		return usuario;
	}
	public void modificarUsuario(int id,String nombre, String password, int idRol) {
		
		String sql = "UPDATE `nidea`.`usuario` SET nombre= ? ,password= ?, id_rol= ? WHERE id= ? ;";
		
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			
			pst.setString(1, nombre);
			pst.setString(2, password);
			pst.setInt(3, idRol);
			pst.setInt(4, id);
			pst.executeUpdate();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}
	public void borrar(int id) {
		String sql = "DELETE FROM `nidea`.`usuario` WHERE id= ? ;";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			pst.setInt(1, id);
			pst.execute();

			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public Usuario mapper(ResultSet rs) throws SQLException {
		Usuario u=null;
		if(rs!=null) {
			u = new Usuario();
			u.setId(rs.getInt("u.id"));
			u.setNombre(rs.getString("u.nombre"));
			u.setPassword(rs.getString("u.password"));
			Rol rol= new Rol();
			rol.setId(rs.getInt("r.id"));
			rol.setNombre(rs.getString("r.nombre"));
			u.setRol(rol);
		}
		return u;
		
	}
	@Override
	public boolean crear(Usuario p) {
		String sql = "INSERT INTO `nidea`.`usuario` (nombre,password, id_rol, email) VALUES (?, ?, ?, ?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getPassword());
			pst.setInt(3, p.getRol().getId());
			pst.setString(4, p.getNombre()+"@"+p.getNombre()+".com");
			pst.execute();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean registrar(Usuario p) {
		String sql = "INSERT INTO `nidea`.`usuario` (nombre,password, id_rol, email) VALUES (?, ?, 2, ?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getPassword());
			pst.setString(3, p.getNombre()+"@"+p.getNombre()+".com");
			pst.execute();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public ArrayList<Usuario> getAll() {
	  return getAll("");
	}
	
	/**
	 *  Lista de usuarios SOLO con id y nombre, sólo para la api.
	 * @param searchText
	 * @return ArrayList<Usuario>
	 */
	public ArrayList<Usuario> getAll(String searchText) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id WHERE u.nombre LIKE '%' ? '%'ORDER BY u.id DESC LIMIT 100;";

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {
			pst.setString(1, searchText);
			try(ResultSet rs = pst.executeQuery();){
				while (rs.next()) {
					lista.add(mapper(rs));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

		return lista;
	}
	public ArrayList<Usuario> getAllByName(String searchText) {
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id,u.nombre FROM `nidea`.`usuario` as u WHERE u.nombre LIKE '%' ? '%'ORDER BY u.nombre DESC LIMIT 20;";

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {
			pst.setString(1, searchText);
			try(ResultSet rs = pst.executeQuery();){
				Usuario u=null;
				while (rs.next()) {
					u=new Usuario();
					u.setNombre(rs.getString("u.nombre"));
					u.setId(rs.getInt("u.id"));
					lista.add(u);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

		return lista;
	}
	public Usuario getByName(String searchText) {
		String sql = "SELECT u.id,u.nombre FROM `nidea`.`usuario` as u WHERE u.nombre=? ORDER BY u.nombre DESC LIMIT 20;";
		Usuario u=new Usuario();
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {
			pst.setString(1, searchText);
			try(ResultSet rs = pst.executeQuery();){
				
				while (rs.next()) {
					u.setNombre(rs.getString("u.nombre"));
					u.setId(rs.getInt("u.id"));
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

		return u;
	}

}


