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
	private Timestamp dateOrder;
	private double costOrder;
	
	private Map<Product,Integer> productsOrdered;

	public Order(int id, int idCustomer, String forwardingAddress,
			Timestamp dateOrder, double costOrder) {
		super();
		this.id = id;
		this.idCustomer = idCustomer;
		this.forwardingAddress = forwardingAddress;
		this.dateOrder = dateOrder;
		this.costOrder = costOrder;
		productsOrdered = new HashMap<Product,Integer>();
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

	public Timestamp getDateOrder() {
		return dateOrder;
	}

	public double getCostOrder() {
		return costOrder;
	}

	public Map<Product, Integer> getProductsOrdered() {
		return productsOrdered;
	}
	
	public void add(Product p, int quantity) {
		productsOrdered.put(p, quantity);
	}
	
}
