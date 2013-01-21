/**
 * 
 */
package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author "Alexandre Bisiaux"
 *
 */
public class Location {

	private double latitude;
	private double longitude;

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

	public Location(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(String str) { // On récupère l'objet location de la BDD sous la forme POINT(x y)
		Pattern p = Pattern.compile("[+|-]?(([0-9]+.[0-9]+)|([0-9]+))");
		Matcher m = p.matcher(str);
		List<Double> list = new ArrayList<Double>();
		while(m.find()) {
			list.add(Double.parseDouble(m.group()));
		}
		this.latitude = list.get(0);
		this.longitude = list.get(1);
	}

	@Override
	public String toString() {
		return "("+this.latitude+","+this.longitude+")";
	}
	
	
}
