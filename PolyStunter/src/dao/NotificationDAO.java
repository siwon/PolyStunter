/**
 * 
 */
package dao;

import bdd.ConnectionBdd;
import beans.Notification;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
/**
 * Accès aux données de type Notification
 * Patron singleton
 * 
 * @author Alexandre Bisiaux
 *
 */
public class NotificationDAO {

	private static NotificationDAO notificationDAO = null;
	private List<Notification> notifications;
	
	private NotificationDAO() {
		notifications = new ArrayList<Notification>();
	}
	
	public static NotificationDAO getInstance() {
		if(notificationDAO == null)
			notificationDAO = new NotificationDAO();
		return notificationDAO;
	}
	
	/**
	 * Charge les notifications
	 */
	public void load() {
		java.sql.PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("SELECT * FROM NOTIFICATION");
			ResultSet result = null;
			result = preparedStatement.executeQuery();
			notifications.clear();
			while(result.next()) {
				notifications.add(new Notification(result.getInt("idNotification"), result.getInt("idSender"), result.getString("typeNotification"), result.getString("informationNotification"), result.getDouble("latitudeNotification"), result.getDouble("longitudeNotification")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ajoute une notification dans la BDD
	 * 
	 * @param idSender Identifiant de l'émetteur
	 * @param type Type de la notification
	 * @param information Information additionnelle
	 * @param latitude Latitude de l'emplacement de la notification
	 * @param longitude Longitude de l'emplacement de la notification
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 * 
	 */
	public int add(int idSender, String type, String information, double latitude, double longitude) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("INSERT INTO NOTIFICATION VALUES (null,?,?,?,?,?)");
			preparedStatement.setInt(1, idSender);
			preparedStatement.setString(2, type);
			preparedStatement.setString(3, information);
			preparedStatement.setDouble(4, latitude);
			preparedStatement.setDouble(5, longitude);
			success = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.load();
		return success;
	}
	
	/**
	 * Retourne les notifications d'un utilisateur défini par son identifiant idSender
	 * @param idSender Identifiant de l'émetteur
	 * @return La liste des notifications
	 */
	public List<Notification> findBySender(int idSender) {
		List<Notification> list = new ArrayList<Notification>();
		for (Notification n : notifications) {
			if(n.getIdSender() == idSender)
				list.add(n);
		}
		return list;
	}
	
	/**
	 * Supprime la notification identifiée par id
	 * @param id Identification de la notification à supprimer
	 * @return 1 si l'opération s'est bien déroulée, 0 sinon
	 */
	public int remove(int id) {
		java.sql.PreparedStatement preparedStatement;
		int success = 0;
		try {
			preparedStatement = ConnectionBdd.getInstance().getConnection().prepareStatement("DELETE FROM NOTIFICATION WHERE idNotification = ?");
			preparedStatement.setInt(1, id);
			success = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.load();
		return success;
	}

	public List<Notification> getNotifications() {
		this.load();
		return notifications;
	}
	
}
