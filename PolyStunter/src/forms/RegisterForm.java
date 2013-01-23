package forms;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.UserDAO;

import bdd.ConnectionBdd;
import beans.User;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class RegisterForm {

	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return this.errors;
	}

	public boolean registerUser(HttpServletRequest request) {

		String login = getValue(request, "login");
		String password = getValue(request, "password");
		String status = getValue(request,"status");
		String mail = getValue(request,"mail");
		boolean result = false;
		
		User user = new User(0, login, password, status ,mail);
		if(UserDAO.getInstance().exist(user))
		{
			setErrors("Inscription impossible","Pseudo déjà utilisé");
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
					setErrors("Inscription impossible","Retentez plus tard");
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

	private void setErrors(String key, String message) {
		this.errors.put(key, message);
	}

	private static String getValue(HttpServletRequest request, String inputValue) {
		String valeur = request.getParameter(inputValue);
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}
}
