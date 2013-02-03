/**
 * 
 */
package dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import bdd.ConnectionBdd;
import beans.Warehouse;
/**
 * Accés aux données de type Warehouse
 * Patron singleton
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
	
	/**
	 * Stocke les dépôts en local
	 */
	private void load() {
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
			e.printStackTrace();
		}
	}
	
	/**
	 * Retourne tous les dépôts de la BDD
	 * @return Les dépôts de la BDD
	 */
	public List<Warehouse> getWarehouses() {
		warehouseDAO.load();
		return warehouses;
	}
	
	/**
	 * Retourne la liste des dépôt d'un commerçant
	 * @param idSeller Identifiant du commerçant
	 * @return Liste de ses dépôts
	 */
	public List<Warehouse> getWarehousesOfSeller(int idSeller) {
		this.load();
		List<Warehouse> l = new ArrayList<Warehouse>();
		for (Warehouse warehouse : warehouses) {
			if(warehouse.getIdSeller() == idSeller)
				l.add(warehouse);
		}
		return l;
	}
	
	/**
	 * Ajoute un dépôt à la BDD
	 * @param idSeller Identifiant du commerçant
	 * @param name Nom du dépôt
	 * @param street Rue du dépôt
	 * @param zipCode Code postal
	 * @param city Ville
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 */
	public int addWarehouse(int idSeller, String name, String street, int zipCode, String city) {
		int success = 0;
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO WAREHOUSE VALUES (null,?,?,?,?,?)");
			preparedStatement.setInt(1, idSeller);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, street);
			preparedStatement.setInt(4, zipCode);
			preparedStatement.setString(5, city);
			success = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.load();
		return success;
	}
	
	public String getWarehouseName(int id) {
		java.sql.PreparedStatement preparedStatement;
		ResultSet result;
		String name = "";
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT nameWarehouse FROM WAREHOUSE WHERE idWarehouse = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if(result.next())
				name = result.getString("nameWarehouse");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	public boolean isUsed(int id) {
		java.sql.PreparedStatement preparedStatement;
		ResultSet result;
		boolean isUsed = false;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCT WHERE idWarehouse = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if(result.next())
				isUsed = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUsed;
	}
	
	/**
	 * Supprime le dépôt identifié par id
	 * @param id Identification du dépôt à supprimer
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 */
	public int removeWarehouse(int id) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("DELETE FROM WAREHOUSE WHERE idWarehouse = ?");
			preparedStatement.setInt(1, id);
			success = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Obtenir le dépôt d'identifiant id
	 * @param id Identifiant du dépôt
	 * @return Le dépôt s'il existe, null sinon
	 */
	public Warehouse getWarehouseById(int id) {
		Warehouse w = null;
		boolean find = false;
		Iterator<Warehouse> it = warehouses.iterator();
		while(!find && it.hasNext()) {
			Warehouse temp = it.next();
			if(temp.getId() == id) {
				find = true;
				w = temp;
			}
		}
		return w;
	}

	/**
	 * Mettre à jour les informations d'un dépôt
	 * @param id Identifiant du dépôt à modifier
	 * @param name Nouveau nom pour le dépôt
	 * @param street Nouvelle rue pour le dépôt
	 * @param zipCode Nouveau code postal pour le dépôt
	 * @param city Nouvelle ville pour le dépôt
	 * @return 1 si la màj s'est bien déroulée, 0 sinon
	 */
	public int updateWarehouse(int id, String name, String street,	int zipCode, String city) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("UPDATE WAREHOUSE SET nameWarehouse = ? , streetWarehouse = ? , zipCodeWarehouse = ? , cityWarehouse = ? WHERE idWarehouse = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, street);
			preparedStatement.setInt(3, zipCode);
			preparedStatement.setString(4, city);
			preparedStatement.setInt(5, id);
			success = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.load();
		return success;
	}
}
