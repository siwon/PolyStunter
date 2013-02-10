/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

import bdd.ConnectionBdd;
import beans.Market;
import beans.Order;

/**
 * @author Alexandre Bisiaux
 *
 */
public class DeliveryDAO {

	private static DeliveryDAO deliveryDAO = null;
	
	
	private DeliveryDAO() {
		
	}
	
	public static DeliveryDAO getInstance() {
		if(deliveryDAO == null)
			deliveryDAO = new DeliveryDAO();
		return deliveryDAO;
	}
	
	public List<Order> getOrders() {
		java.sql.PreparedStatement preparedStatement;
		Order order = null;
		int id, idCustomer;
		Timestamp t;
		double cost;
		String forwardingAddress;
		List<Order> l = null;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM ORDERS WHERE statusOrder != 'DELIVERED'");
			ResultSet result = null;
			result = preparedStatement.executeQuery();
			l = new ArrayList<Order>();
			while(result.next()) {
				id = result.getInt("idOrder");
				idCustomer = result.getInt("idCustomer");
				forwardingAddress = result.getString("forwardingAddressOrder");
				t = result.getTimestamp("deliveryDateOrder");
				cost = result.getDouble("costOrder");
				order = new Order(id, idCustomer, forwardingAddress, t, cost);
				preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM ORDEREDPRODUCTS WHERE idOrder = ?");
				preparedStatement.setInt(1, id);
				ResultSet rs2 = preparedStatement.executeQuery();
				while(rs2.next()) {
					order.add(Market.getInstance().getProduct(rs2.getInt("idProduct")), rs2.getInt("quantityOrdered"));
				}
				l.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
}
