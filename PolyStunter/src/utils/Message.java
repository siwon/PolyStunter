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
public class Message {

	private List<String> messages = new ArrayList<String>();
	
	public void add(String message){
		this.messages.add(message+ "\n");
	}
	
	public void clear() {
		this.messages.clear();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (String e : messages) {
			buf.append(e);
			buf.append("\n");
		}
		return buf.toString();
	}
	
	public boolean isEmpty() {
		return this.messages.isEmpty();
	}
	
	
}
