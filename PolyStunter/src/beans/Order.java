/**
 * 
 */
package beans;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author Alexandre Bisiaux
 *
 */
public class Order {
	private int id;
	private int idCustomer;
	private String forwardingAddress;
	private Timestamp date;
	private double cost;
	private String status;
	
	private Map<Product,Integer> productsOrdered;

	public Order(int id, int idCustomer, String forwardingAddress,
			Timestamp dateOrder, double costOrder, String status) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.forwardingAddress = forwardingAddress;
		this.date = dateOrder;
		this.cost = costOrder;
		productsOrdered = new HashMap<Product,Integer>();
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public String getForwardingAddress() {
		return forwardingAddress;
	}

	public Timestamp getDate() {
		return date;
	}

	public double getCost() {
		return cost;
	}

	public Map<Product, Integer> getProductsOrdered() {
		return productsOrdered;
	}
	
	public void add(Product p, int quantity) {
		productsOrdered.put(p, quantity);
	}

	public String getStatus() {
		return status;
	}
	
	public boolean isReady() {
		return status.equals("READY");
	}
	
	public boolean isDelivered() {
		return status.equals("DELIVERED");
	}
	
}
