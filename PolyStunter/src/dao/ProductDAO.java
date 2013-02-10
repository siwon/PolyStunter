/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bdd.ConnectionBdd;

/** Accès aux données de type Product
 * Patron singleton
 * @author "Alexandre Bisiaux"
 *
 */
public class ProductDAO {
	private static ProductDAO productDAO = null;

	private ProductDAO() {

	}

	public static ProductDAO getInstance() {
		if(productDAO == null) {
			productDAO = new ProductDAO();
		}

		return productDAO;
	}

	/**
	 * Ajoute un produit à la BDD
	 * @param idSeller Identifiant du vendeur
	 * @param price Prix du produit
	 * @param name Nom du produit
	 * @param reference Référence du produit
	 * @param quantity Quantité du produit
	 * @param information Information sur le produit
	 * @param warehouse Dépôt 
	 * @param photo Photo du produit
	 */
	public int addProduct(int idSeller, double price, String name, String reference, int quantity, String information, int warehouse, String photo) {
		int success = 0;
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO PRODUCT VALUES(null,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, idSeller);
			preparedStatement.setDouble(2, price);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, reference);
			preparedStatement.setInt(5, quantity);
			preparedStatement.setString(6, information);
			preparedStatement.setInt(7, warehouse);
			preparedStatement.setString(8, photo);
			success = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Supprime le produit identifié par id
	 * @param id Identification du produit à supprimer
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 */
	public int removeProduct(int id) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("DELETE FROM PRODUCT WHERE idProduct = ?");
			preparedStatement.setInt(1, id);
			success = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Test si un produit est commandé avant de le supprimer
	 * @param id Identifiant du produit concerné
	 * @return Vrai si le produit n'est pas commandé, faux sinon
	 */
	public boolean isOrdered(int id) {
		java.sql.PreparedStatement preparedStatement;
		boolean ordered = false;
		ResultSet result;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM ORDEREDPRODUCTS WHERE idProduct = ?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if(result.next())
				ordered = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordered;
	}

	/**
	 * Mise à jour des informations d'un produit
	 * @param id Identifiant du produit à modifier
	 * @param name Nouveau nom
	 * @param reference Nouvelle reference
	 * @param information Nouvelle info
	 * @param price Nouveau prix
	 * @param quantity Nouvelle quantité
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 */
	public int updateProduct(int id, String name, String reference,
			String information, double price, int quantity, int warehouse) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("UPDATE PRODUCT SET nameProduct = ?, referenceProduct=?, informationProduct=?, " +
					"priceProduct = ?, quantityProduct = ?, idWarehouse = ? WHERE idProduct = ?");
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, reference);
			preparedStatement.setString(3, information);
			preparedStatement.setDouble(4, price);
			preparedStatement.setInt(5, quantity);
			preparedStatement.setInt(6, warehouse);
			preparedStatement.setInt(7, id);
			success = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	/**
	 * Vérifie si une référence n'existe pas déjà chez ce même vendeur
	 * @param reference Référence à vérifier
	 * @param idSeller Identifiant du vendeur
	 * @return Vrai si la référence n'existe pas, faux sinon
	 */
	public boolean checkReference(String reference, int idSeller) {
		java.sql.PreparedStatement preparedStatement;
		boolean exist = false;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT referenceProduct FROM PRODUCT WHERE idSeller = ?");
			preparedStatement.setInt(1, idSeller);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next() && !exist) {
				if(rs.getString("referenceProduct").equals(reference))
					exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return !exist;
	}
	
	/**
	 * Achat d'un produit
	 * @param id Identifiant du produit
	 * @param quantity Quantité commandée
	 */
	public void setQuantity(int id, int quantity) {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT quantityProduct FROM PRODUCT WHERE idProduct = ?");
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int q = rs.getInt(1);
			
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("UPDATE PRODUCT SET quantityProduct = ? WHERE idProduct = ?");
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, q - quantity);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
