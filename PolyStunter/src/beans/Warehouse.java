/**
 * 
 */
package beans;

/**
 * @author Alexandre Bisiaux
 *
 */
public class Warehouse {

	private int id;
	private int idSeller;
	private String street;
	private int zipCode;
	private String city;
	
	
	public Warehouse(int id, int idSeller, String street, int zipCode,
			String city) {
		super();
		this.id = id;
		this.idSeller = idSeller;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
