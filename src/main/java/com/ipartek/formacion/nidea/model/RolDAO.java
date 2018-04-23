package com.ipartek.formacion.nidea.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.nidea.pojo.Material;
import com.ipartek.formacion.nidea.pojo.Rol;

public class RolDAO implements Persistible<Rol>{
	private static RolDAO rolDao;
	private RolDAO() {
	}
	private synchronized static void createInstance() {
		if(rolDao==null) {
			rolDao=new RolDAO();
		}
	}
	public static RolDAO getRolDAO() {
		if(rolDao==null) {
			createInstance();
		}
		return rolDao;
	}
	@Override
	public ArrayList<Rol> getAll() {
		return this.getAll("");
	}
	public ArrayList<Rol> getAll(String searchText) {

		ArrayList<Rol> lista = new ArrayList<Rol>();
		String sql = "SELECT r.id, r.nombre FROM nidea.rol as r WHERE nombre LIKE '%' ? '%' ORDER BY id DESC LIMIT 100;";

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
	@Override
	public Rol getById(int id) {
		ArrayList<Rol> lista = new ArrayList<Rol>();
		Rol rol=new Rol();
		String sql = "SELECT r.id,r.nombre FROM `nidea`.`rol` as r WHERE id= ? ;";
		

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
		if(!lista.isEmpty())rol=lista.get(0);

		return rol;
	}
	@Override
	public boolean crear(Rol p) {
		String sql = "INSERT INTO `nidea`.`rol` (nombre) VALUES (?);";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				
				) {
			System.out.println(sql);
			pst.setString(1, p.getNombre());
			pst.execute();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public void borrar(int id) {
		String sql = "DELETE FROM `nidea`.`rol` WHERE id= ? ;";
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
	public Rol mapper(ResultSet rs) throws SQLException {
		Rol r=null;
		if(rs!=null) {
			r= new Rol();
			r.setId(rs.getInt("r.id"));
			r.setNombre(rs.getString("r.nombre"));
		
		}
		return r;
	}
	public Rol getByNombre(String nombre) {

		ArrayList<Rol> lista = new ArrayList<Rol>();
		Rol rol=new Rol();
		String sql = "SELECT r.id, r.nombre, r.precio FROM nidea.rol as r WHERE nombre= ? ;";
		

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
		if(!lista.isEmpty())rol=lista.get(0);

		return rol;
	}
public void modificarRol(int id,String nombre) {
		
		String sql = "UPDATE `nidea`.`rol` SET nombre= ? WHERE id= ? ;";
		try (Connection con=ConnectionManager.getConnection();
				PreparedStatement pst =	con.prepareStatement(sql);
				) {

			
			pst.setString(1, nombre);
			
			pst.setInt(2, id);
			pst.executeUpdate();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}

}
