/**
 * 
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import bdd.ConnectionBdd;
import beans.Product;
import beans.Warehouse;
/**
 * @author Alexandre Bisiaux
 *
 */
public class WarehouseDAO {
	private static WarehouseDAO warehouseDAO = null;
	private List<Warehouse> warehouses;
	
	private WarehouseDAO() {
		this.warehouses = new ArrayList<Warehouse>();
	}
	
	public static WarehouseDAO getInstance() {
		if(warehouseDAO == null)
			warehouseDAO = new WarehouseDAO();
		return warehouseDAO;
	}
	
	private void loadWarehouses() {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM WAREHOUSE");
			ResultSet result = null;
			result = preparedStatement.executeQuery();
			warehouses.clear();
			while(result.next()) {
				warehouses.add(new Warehouse(result.getInt("idWarehouse"), result.getInt("idSeller"), result.getString("nameWarehouse"), result.getString("streetWarehouse"), result.getInt("zipCodeWarehouse"), result.getString("cityWarehouse")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Warehouse> getWarehouses() {
		warehouseDAO.loadWarehouses();
		return warehouses;
	}
	
	public List<Warehouse> getWarehousesOfSeller(int idSeller) {
		this.loadWarehouses();
		List<Warehouse> l = new ArrayList<Warehouse>();
		for (Warehouse warehouse : warehouses) {
			if(warehouse.getIdSeller() == idSeller)
				l.add(warehouse);
		}
		return l;
	}
	
	public void addWarehouse(int idSeller, String name, String street, int zipCode, String city) {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO WAREHOUSE VALUES (null,?,?,?,?,?)");
			preparedStatement.setInt(1, idSeller);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, street);
			preparedStatement.setInt(4, zipCode);
			preparedStatement.setString(5, city);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.loadWarehouses();
	}
}
