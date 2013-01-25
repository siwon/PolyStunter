/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bdd.ConnectionBdd;
import beans.Product;
/**
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

	public void addProduct(Product p) {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO PRODUCT VALUES(null,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(2, p.getIdSeller());
			preparedStatement.setDouble(3, p.getPrice());
			preparedStatement.setString(4, p.getName());
			preparedStatement.setString(5, p.getReference());
			preparedStatement.setInt(6, p.getQuantity());
			preparedStatement.setString(7, p.getInformation());
			preparedStatement.setInt(8, p.getWarehouse());
			preparedStatement.setString(9, p.getPhoto());
			preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getLastId() {
		java.sql.PreparedStatement preparedStatement;
		int id = -1;
		ResultSet result;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT last_insert_id() FROM PRODUCT");
			result = preparedStatement.executeQuery();
			if(result.next())
				id = result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
