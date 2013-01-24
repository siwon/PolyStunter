package forms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorMessage;


import bdd.ConnectionBdd;
import beans.User;

/**
 * 
 * @author "Alexandre Bisiaux"
 *
 */
public final class ConnectForm {

	public static ErrorMessage errors = new ErrorMessage();

	public User connectUser(HttpServletRequest request) {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		ResultSet result = null;
		User user = null;

		try {
			java.sql.PreparedStatement preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM USER WHERE loginUser=BINARY? and passwordUser=? ;");
			preparedStatement.setString(1, login);
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
	        md.update(password.getBytes());
	        byte byteData[] = md.digest();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
			preparedStatement.setString(2, sb.toString());

			result = preparedStatement.executeQuery();

			if(result.next()) {
				user = new User();
				user.setId(result.getInt("idUser"));
				user.setLogin(result.getString("loginUser"));
				user.setPassword(result.getString("passwordUser"));
				user.setStatus(result.getString("statusUser"));
				user.setMail(result.getString("mailUser"));
			} else {
				errors.add("Connexion impossible : Identifiants incorrects.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}	
}