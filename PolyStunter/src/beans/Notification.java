package beans;

import java.sql.Timestamp;

/**
 * 
 * @author Alexandre Bisiaux
 *
 */
public class Notification {

	private int id;
	private int idSender;
	private String type;
	private String information;
	private double latitude;
	private double longitude;
	private Timestamp date;
	
	public Notification(int id, int idSender, String type, String information,
			double latitude, double longitude, Timestamp date) {
		super();
		this.id = id;
		this.idSender = idSender;
		this.type = type;
		this.information = information;
		this.latitude = latitude;
		this.longitude = longitude;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSender() {
		return idSender;
	}

	public void setIdSender(int idSender) {
		this.idSender = idSender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
