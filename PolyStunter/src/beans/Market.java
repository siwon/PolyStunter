/**
 * 
 */
package beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.MarketDAO;


/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Market {

	private List<Product> products;

	private static Market market = null;

	private Market() {
		this.products = new ArrayList<Product>();
		MarketDAO.getInstance().refresh(this);
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

	public List<Product> getProductsOfSeller(int idSeller) {
		List<Product> p = new ArrayList<Product>();

		for (Product product : products) {
			if(product.getIdSeller() == idSeller) {
				p.add(product);
			}
		}
		return p;
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
