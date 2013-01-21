package beans;

import java.sql.*;

import bdd.ConnectionBdd;

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

	public int getNbProductsInBasket() {
		Statement s = null;
		ResultSet result = null;
		try {
			s = ConnectionBdd.getInstance().getConnection().createStatement();
			result = s.executeQuery("SELECT COUNT(*) FROM PRODUCTSINBASKET WHERE idBasket="+this.id);
			s.close();
			if(result.next())
				return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
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
	
	public boolean exist() {
		Statement s = null;
		ResultSet result = null;
		try {
			s = ConnectionBdd.getInstance().getConnection().createStatement();
			result = s.executeQuery("SELECT * FROM USER WHERE loginUser LIKE '"+this.login+"'");
			if(result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}
	
	public static String getLoginFromId(int id) {
		Statement s = null;
		ResultSet result = null;
		try {
			s = ConnectionBdd.getInstance().getConnection().createStatement();
			result = s.executeQuery("SELECT loginUser FROM USER WHERE idUser="+id);
			if(result.next())
				return result.getString("loginUser");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
