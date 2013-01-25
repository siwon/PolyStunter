/**
 * 
 */
package beans;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Product {

	private int id;
	private int idSeller;
	private double price;
	private String name;
	private String reference;
	private int quantity;
	private String information;
	private int warehouse;
	private String photo;
	
	public Product(int id, int idSeller, double price, String name, String reference, int quantity, String information,
			int warehouse, String photo) {
		super();
		this.id = id;
		this.idSeller = idSeller;
		this.name = name;
		this.price = price;
		this.reference = reference;
		this.quantity = quantity;
		this.information = information;
		this.warehouse = warehouse;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSeller() {
		return idSeller;
	}

	public void setIdSeller(int idSeller) {
		this.idSeller = idSeller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(int warehouse) {
		this.warehouse = warehouse;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
		
	public boolean inStock() {
		return this.getQuantity() > 0;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
