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
	public void modificarUsuario(int id,String nombre, String password) {
		
		String sql = "UPDATE `nidea`.`usuario` SET nombre= ? ,password= ? WHERE id= ? ;";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			
			pst.setString(1, nombre);
			pst.setString(2, password);
			pst.setInt(3, id);
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
		String sql = "INSERT INTO `nidea`.`usuario` (nombre,password, id_rol) VALUES (?, ?, ?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, p.getNombre());
			pst.setString(2, p.getPassword());
			pst.setInt(2, p.getRol().getId());
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
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		String sql = "SELECT u.id,u.nombre, u.password, r.id, r.nombre FROM `nidea`.`usuario` as u INNER JOIN `nidea`.`rol` as r on u.id_rol=r.id ORDER BY u.id DESC LIMIT 100;";

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {
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
	

}

