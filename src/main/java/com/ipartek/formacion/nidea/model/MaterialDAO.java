package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;

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
		String sql = "SELECT id, nombre, precio FROM spoty.material WHERE nombre LIKE '%' ? '%' ORDER BY id DESC LIMIT 100;";

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
		String sql = "SELECT id, nombre, precio FROM spoty.material WHERE id= ? ;";
		

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
		String sql = "SELECT id, nombre, precio FROM spoty.material WHERE nombre= ? ;";
		

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
				String sql = "INSERT INTO spoty.material (nombre,precio) VALUES (?, ?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, material.getNombre());
			pst.setFloat(2, material.getPrecio());
			pst.execute();
			return true;

			

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public void modificarMaterial(String nombre, float precio, int id) {
		
		String sql = "UPDATE spoty.material SET nombre= ? ,precio= ? WHERE id= ? ;";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.setInt(3, id);
			pst.executeUpdate();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}
	public void borrar(int id) {
		String sql = "DELETE FROM spoty.material WHERE id= ? ;";
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
			m.setId(rs.getInt("id"));
			m.setNombre(rs.getString("nombre"));
			m.setPrecio(rs.getFloat("precio"));
		}
		return m;
		
	}
	

}
