/**
 * 
 */
package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bdd.ConnectionBdd;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Market {

	private List<Product> products;

	private static Market market = null;
	
	private Market() {
		this.products = new ArrayList<Product>();
		this.refresh();
	}
	
	public static Market getInstance() {
		if(Market.market == null)
			return new Market();
		else
			return Market.market;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void refresh() {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCT");
			ResultSet result = null;
			result = preparedStatement.executeQuery();
			products.clear();
			if(result.next()) {
				products.add(new Product(result.getInt("idProduct"),result.getInt("idSeller"),result.getDouble("priceProduct"),
						result.getString("nameProduct"),result.getString("referenceProduct"),
						result.getInt("quantityProduct"),result.getString("informationProduct"),
						new Location(result.getString("locationProduct")),result.getString("photoProduct")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Product getProduct(int id) {
		boolean find = false;
		Product p = null;
		Iterator<Product> it = products.iterator();
		while(it.hasNext() && !find) {
			p = it.next();
			if(p.getId() == id)
				find = true;
		}
		return p;
	}
}
