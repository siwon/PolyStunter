package beans;

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
	
	public Notification(int id, int idSender, String type, String information,
			double latitude, double longitude) {
		super();
		this.id = id;
		this.idSender = idSender;
		this.type = type;
		this.information = information;
		this.latitude = latitude;
		this.longitude = longitude;
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
	
}
