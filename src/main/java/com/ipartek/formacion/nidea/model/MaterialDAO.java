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
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			
			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM spoty.material WHERE nombre LIKE '%' ? '%' ORDER BY id DESC LIMIT 500;";

			pst = con.prepareStatement(sql);
			pst.setString(1, searchText);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return lista;
	}
	public Material getById(int id) {

		ArrayList<Material> lista = new ArrayList<Material>();
		Material material=new Material();
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			con = ConnectionManager.getConnection();
			String sql = "SELECT id, nombre, precio FROM spoty.material WHERE id= ? ;";

			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			Material m = null;
			while (rs.next()) {
				m = new Material();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("nombre"));
				m.setPrecio(rs.getFloat("precio"));
				lista.add(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(!lista.isEmpty())material=lista.get(0);

		return material;
	}
	public void crear(Material material) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			
			con = ConnectionManager.getConnection();
			String sql = "INSERT INTO spoty.material (nombre,precio) VALUES ( ? , ? );";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.setString(1, material.getNombre());
			pst.setFloat(2, material.getPrecio());
			pst.execute();

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void modificarMaterial(String nombre, float precio, int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			
			con = ConnectionManager.getConnection();
			String sql = "UPDATE spoty.material SET nombre= ? ,precio= ? WHERE id= ? ;";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setFloat(2, precio);
			pst.setInt(3, id);
			pst.executeUpdate();

			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void borrar(int id) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {

			
			con = ConnectionManager.getConnection();
			String sql = "DELETE FROM spoty.material WHERE id= ? ;";
			System.out.println(sql);
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.execute();

			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
