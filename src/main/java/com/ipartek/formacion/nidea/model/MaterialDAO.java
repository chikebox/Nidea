package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.pojo.Usuario;

public class MaterialDAO implements Persistible<Material>{
	private static MaterialDAO miMaterialDAO=null;

	/**
	 * Recupera todos los materiales de la BBDD ordenados por id descendente
	 * 
	 * @return ArrayList<Material> si no existen registros new ArrayList<Material>()
	 */
	private MaterialDAO() {
	}
	private synchronized static void createInstance() {
		if(miMaterialDAO==null) {
			miMaterialDAO=new MaterialDAO();
		}
	}
	public static MaterialDAO getMiMaterialDAO() {
		if(miMaterialDAO==null) {
			createInstance();
		}
		return miMaterialDAO;
	}
	public ArrayList<Material> getAll() {

		return this.getAll("");
	}
	public ArrayList<Material> getAll(String searchText) {

		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT m.id, m.nombre, m.precio, u.nombre, u.id FROM nidea.material as m INNER JOIN nidea.usuario as u ON m.id_usuario=u.id WHERE m.nombre LIKE '%' ? '%' ORDER BY m.id DESC LIMIT 100;";

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
	public Material getById(int id) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Material material=new Material();
		String sql = "SELECT m.id, m.nombre, m.precio, u.nombre, u.id FROM nidea.material as m INNER JOIN nidea.usuario as u ON m.id_usuario=u.id WHERE m.id= ? ;";
		

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
		if(!lista.isEmpty())material=lista.get(0);

		return material;
	}
	public Material getByNombre(String nombre) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Material material=new Material();
		String sql = "SELECT m.id, m.nombre, m.precio, u.nombre, u.id FROM nidea.material as m INNER JOIN nidea.usuario as u ON m.id_usuario=u.id WHERE m.nombre= ? ;";
		

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
		if(!lista.isEmpty())material=lista.get(0);

		return material;
	}
	public boolean crear(Material material){
				String sql = "INSERT INTO nidea.material (nombre,precio,id_usuario) VALUES (?, ?, ?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, material.getNombre());
			pst.setFloat(2, material.getPrecio());
			pst.setInt(3, material.getUsuario().getId());
			pst.execute();
			return true;

			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public void modificarMaterial(String nombre, float precio, int id, int idUsuario) {
		
		String sql = "UPDATE nidea.material SET nombre= ? ,precio= ?, id_usuario= ? WHERE id= ? ;";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.setInt(3, idUsuario);
			pst.setInt(4, id);
			pst.executeUpdate();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}
	public void borrar(int id) {
		String sql = "DELETE FROM nidea.material WHERE id= ? ;";
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
	public Material mapper(ResultSet rs) throws SQLException {
		Material m=null;
		if(rs!=null) {
			m = new Material();
			m.setId(rs.getInt("m.id"));
			m.setNombre(rs.getString("m.nombre"));
			m.setPrecio(rs.getFloat("m.precio"));
			Usuario u= new Usuario();
			u.setId(rs.getInt("u.id"));
			u.setNombre(rs.getString("u.nombre"));
			m.setUsuario(u);
		}
		return m;
		
	}
	public ArrayList<Material> getAllByUsuario(Usuario usuario, String searchText) {
		ArrayList<Material> lista = new ArrayList<Material>();
		String sql = "SELECT m.id, m.nombre, m.precio, u.nombre, u.id FROM nidea.material as m INNER JOIN nidea.usuario as u ON m.id_usuario=u.id WHERE m.nombre LIKE '%' ? '%' AND m.id_usuario= ? ORDER BY m.id DESC LIMIT 100;";

		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {
			pst.setString(1, searchText);
			pst.setInt(2, usuario.getId());
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
