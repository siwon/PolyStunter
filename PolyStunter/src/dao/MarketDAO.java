/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import bdd.ConnectionBdd;
import beans.Location;
import beans.Market;
import beans.Product;

/**
 * @author Alexandre Bisiaux
 *
 */
public class MarketDAO {
	
	private static MarketDAO marketDAO = null;
	
	private MarketDAO() {
		
	}
	
	public static MarketDAO getInstance() {
		if(marketDAO == null){
			marketDAO = new MarketDAO();
		}
		return marketDAO;
	}

	public void refresh(Market market) {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM PRODUCT");
			ResultSet result = null;
			result = preparedStatement.executeQuery();
			market.getProducts().clear();
			while(result.next()) {
				market.getProducts().add(new Product(result.getInt("idProduct"),result.getInt("idSeller"),result.getDouble("priceProduct"),
						result.getString("nameProduct"),result.getString("referenceProduct"),
						result.getInt("quantityProduct"),result.getString("informationProduct"),
						new Location(result.getString("locationProduct")),result.getString("photoProduct")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}