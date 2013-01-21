/**
 * 
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;

import bdd.ConnectionBdd;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Basket {
	private int id;
	private Map<Product,Integer> products;
	private Market market;

	public Basket(int id) {
		this.id = id;
		this.products = new HashMap<Product,Integer>();
		market = Market.getInstance();
	}

	public void loadBasket() {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCTSINBASKET NATURAL JOIN PRODUCT WHERE idBasket=?;");
			preparedStatement.setInt(1, this.id);
			ResultSet result = null;
			result = preparedStatement.executeQuery();

			while(result.next()) {
				if(market.getProduct(result.getInt("idProduct")) != null) {
					if(result.getInt("quantityProduct") > 0) {
						products.put(market.getProduct(result.getInt("idProduct")),result.getInt("quantityToOrder"));
					} else {
						products.put(market.getProduct(result.getInt("idProduct")),0);
					}
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int size() { // En unité d'article
		int size = 0;
		for (int q: products.values()) {
			size += q;
		}
		return size;
	}

	public void save() {
		java.sql.PreparedStatement preparedStatement;

		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("DELETE FROM PRODUCTSINBASKET WHERE idBasket=?");
			preparedStatement.setInt(1, this.id);
			preparedStatement.executeUpdate();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		for (Entry<Product,Integer> e : this.products.entrySet()) {
			try {
				preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO PRODUCTSINBASKET VALUES(?,?,?)");
				preparedStatement.setInt(1, this.id);
				preparedStatement.setInt(2, e.getKey().getId());
				preparedStatement.setInt(3, e.getValue());
				preparedStatement.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void addProduct(int id, Integer quantity) {
		if(products.containsKey(market.getProduct(id))){
			products.put(market.getProduct(id), products.get(market.getProduct(id))+quantity);
		} else {
			products.put(market.getProduct(id), quantity);
		}
	}

	private void removeProduct(Product p) {
		products.remove(p);
	}

	public String toString() {
		return products.toString();
	}

	public boolean isEmpty() {
		return products.isEmpty();
	}

	public Map<Product, Integer> getProducts() {
		return products;
	}

	public double getCost() {
		double cost = 0;
		for (Entry<Product,Integer> e : products.entrySet()) {
			cost += e.getKey().getPrice() * e.getValue();
		}
		return cost;
	}

	public void empty() {
		this.products.clear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
