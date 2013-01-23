/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;

import bdd.ConnectionBdd;
import beans.Basket;
import beans.Market;
import beans.Product;
/**
 * @author "Alexandre Bisiaux"
 *
 */
public class BasketDAO {

	private static BasketDAO basketDAO = null;

	private BasketDAO() {

	}

	public static BasketDAO getInstance() {
		if(basketDAO == null) {
			basketDAO = new BasketDAO();
		}
		return basketDAO;
	}

	public void loadBasket(Basket basket) {
		Market market = Market.getInstance();
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCTSINBASKET NATURAL JOIN PRODUCT WHERE idBasket=?;");
			preparedStatement.setInt(1, basket.getId());
			ResultSet result = null;
			result = preparedStatement.executeQuery();

			while(result.next()) {
				if(market.getProduct(result.getInt("idProduct")) != null) {
					if(result.getInt("quantityProduct") > 0) {
						basket.getProducts().put(market.getProduct(result.getInt("idProduct")),result.getInt("quantityToOrder"));
					} else {
						basket.getProducts().put(market.getProduct(result.getInt("idProduct")),0);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(Basket basket) {
		java.sql.PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("DELETE FROM PRODUCTSINBASKET WHERE idBasket=?");
			preparedStatement.setInt(1, basket.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for (Entry<Product,Integer> e : basket.getProducts().entrySet()) {
			try {
				preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO PRODUCTSINBASKET VALUES(?,?,?)");
				preparedStatement.setInt(1, basket.getId());
				preparedStatement.setInt(2, e.getKey().getId());
				preparedStatement.setInt(3, e.getValue());
				preparedStatement.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void addProduct(Basket basket, int id, Integer quantity) {
		Market market = Market.getInstance();
		if(basket.contains(id)){
			basket.getProducts().put(basket.getProduct(id), basket.getProducts().get(basket.getProduct(id))+quantity);
		} else {
			basket.getProducts().put(market.getProduct(id), quantity);
		}
	}

	public void removeProduct(Basket basket, Product p) {
		basket.getProducts().remove(p);
	}

	public void empty(Basket basket) {
		basket.getProducts().clear();
	}
}
