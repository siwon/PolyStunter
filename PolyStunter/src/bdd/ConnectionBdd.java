package bdd;

import java.sql.*;
import java.util.*;


/**
 * Classe BDD
 * Singleton
 * @author "Alexandre Bisiaux"
 *
 */
public class ConnectionBdd {

	private List<String> errors;
	private Connection connection = null;
	private static ConnectionBdd bdd = null;
	
	private ConnectionBdd() {
		errors = new ArrayList<String>();
		this.connect();
	}
	
	public static ConnectionBdd getInstance() {
		if(bdd == null)
			return new ConnectionBdd();
		else
			return bdd;
	}
	
	/**
	 * Connexion à la BDD
	 */
	public void connect()
	{
		/* Chargement du driver JDBC pour MySQL */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			errors.add("Erreur lors du chargement : le driver n'a pas été trouvé dans le classpath ! <br/>" + e.getMessage() );
		}

		/* Connexion à la BDD */

		/*String url = "jdbc:mysql://localhost:8889/polystunter";
		String login = "root";
		String password = "root";*/
		String url = "jdbc:mysql://localhost:3306/polystunter";
		String login = "root";
		String password = "";

		try {
			this.connection = DriverManager.getConnection(url, login, password);
		} catch ( SQLException e ) {
			errors.add( "Erreur lors de la connexion : <br/>" + e.getMessage() );
		}
	}
	
	/**
	 * Déconnexion de la BDD
	 */
	public void disconnect() {
		if (this.connection != null){
			try {
				this.connection.close();
			} catch (SQLException ignore) {
			}
		}
	}
	
	/**
	 * Renvoie les messages d'erreurs
	 * @return Les messages d'erreurs
	 */
	public List<String> getErrors() {
		return this.errors;
	}

	public Connection getConnection() {
		return connection;
	}
}
