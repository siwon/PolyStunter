/**
 * 
 */
package dao;

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
	public static int addProduct(int idSeller, double price, String name, String reference, int quantity, String information, int warehouse, String photo) {
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
	public static int remove(int id) {
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
}
