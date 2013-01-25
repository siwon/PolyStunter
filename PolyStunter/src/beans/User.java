package beans;

/**
 * 
 * @author "Alexandre Bisiaux"
 *
 */
public class User {
	private int id;
	private String login, password, status, mail;
	private Basket basket;

	public User() {
		super();
	}

	public User(int id, String login, String password, String status, String mail) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.status = status;
		this.mail = mail;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isCustomer() {
		return this.status.equals("CUSTOMER");
	}

	public boolean isSeller() {
		return this.status.equals("SELLER");
	}

	public boolean isDeliveryMan() {
		return this.status.equals("DELIVERYMAN");
	}
	
	public boolean isAdmin() {
		return this.status.equals("ADMIN");
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
}
