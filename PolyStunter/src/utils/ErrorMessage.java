/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Bisiaux
 *
 */
public class ErrorMessage {

	private List<String> errors = new ArrayList<String>();
	
	public void add(String error){
		this.errors.add(error);
	}
	
	public void clear() {
		this.errors.clear();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (String e : errors) {
			buf.append(e);
			buf.append("\n");
		}
		return buf.toString();
	}
	
	public boolean isEmpty() {
		return this.errors.isEmpty();
	}
	
	
}
