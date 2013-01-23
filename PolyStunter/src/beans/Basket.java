/**
 * 
 */
package beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Basket {
	private int id;
	private Map<Product,Integer> products;

	public Basket(int id) {
		this.id = id;
		this.products = new HashMap<Product,Integer>();
	}

	public int size() { // En unité d'article
		int size = 0;
		for (int q: products.values()) {
			size += q;
		}
		return size;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Basket [products=" + products + "]";
	}
	
	public boolean contains(int id)
	{
		boolean find = false;
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext() && !find) {
			if(it.next().getId() == id)
				find = true;
		}
		return find;
	}
	
	public Product getProduct(int id) {
		boolean find = false;
		Product p = null;
		Iterator<Product> it = products.keySet().iterator();
		while(it.hasNext() && !find) {
			p = it.next();
			if(p.getId() == id)
				find = true;
		}
		return p;
	}
}
