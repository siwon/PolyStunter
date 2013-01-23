/**
 * 
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdd.ConnectionBdd;
import beans.User;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class UserDAO {
	private static UserDAO userDAO = null;

	private UserDAO() {

	}

	public static UserDAO getInstance() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
	}

	public int getNbProductsInBasket(User user) {
		Statement s = null;
		ResultSet result = null;
		try {
			s = ConnectionBdd.getInstance().getConnection().createStatement();
			result = s.executeQuery("SELECT COUNT(*) FROM PRODUCTSINBASKET WHERE idBasket="+user.getId());
			s.close();
			if(result.next())
				return result.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;		
	}

	public boolean exist(User user) {
		Statement s = null;
		ResultSet result = null;
		try {
			s = ConnectionBdd.getInstance().getConnection().createStatement();
			result = s.executeQuery("SELECT * FROM USER WHERE loginUser LIKE '"+user.getLogin()+"'");
			if(result.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
