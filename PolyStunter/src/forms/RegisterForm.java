package forms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorMessage;

import dao.UserDAO;

import bdd.ConnectionBdd;
import beans.User;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class RegisterForm {

	public static ErrorMessage errors = new ErrorMessage();

	public boolean registerUser(HttpServletRequest request) {

		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String status = request.getParameter("status");
		String mail = request.getParameter("mail");
		boolean result = false;
		
		User user = new User(0, login, password, status ,mail);
		if(UserDAO.getInstance().exist(user))
		{
			errors.add("Inscription impossible : Pseudo déjà utilisé.");
			result = false;
		} else {
			try {
				java.sql.PreparedStatement preparedStatement = ConnectionBdd.getInstance().
						getConnection().prepareStatement
						("INSERT INTO USER VALUES (null,?,?,?,?);");
				MessageDigest md = MessageDigest.getInstance("SHA-256");
		        md.update(password.getBytes());
		        byte byteData[] = md.digest();
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < byteData.length; i++) {
		         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		        }
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, sb.toString());
				preparedStatement.setString(3, mail);
				preparedStatement.setString(4, status);
				
				if(preparedStatement.executeUpdate() == 1)
					result = true;
				else {
					errors.add("Inscription impossible : Retentez plus tard.");
					result = false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
