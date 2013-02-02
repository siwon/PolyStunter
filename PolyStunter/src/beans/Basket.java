/**
 * 
 */
package beans;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Bean Basket
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

	/**
	 * Nombre d'article dans le panier
	 * @return Le nombre d'article dans le panier
	 */
	public int size() {
		int size = 0;
		for (int q: products.values()) {
			size += q;
		}
		return size;
	}

	/**
	 * Test si le panier est vide
	 * @return Vrai si le panier est vide, faux sinon
	 */
	public boolean isEmpty() {
		return products.isEmpty();
	}

	/**
	 * Obtenir tous les produits du panier
	 * @return Les produits du panier
	 */
	public Map<Product, Integer> getProducts() {
		return products;
	}

	/**
	 * Obtenir le coût total du panier
	 * @return Le coût du panier
	 */
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
	
	/**
	 * Test si le panier contient le produit identifié par id
	 * @param id Identifiant du produit
	 * @return Vrai s'il le contient, faux sinon
	 */
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
	
	/**
	 * Obtenir le produit identifié par id
	 * @param id
	 * @return null si le produit n'est pas présent dans le panier, le produit sinon
	 */
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
