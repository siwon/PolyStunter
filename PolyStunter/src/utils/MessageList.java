/**
 * 
 */
package utils;

import java.util.ArrayList;

/**
 * @author Alexandre Bisiaux
 *
 */
public class MessageList extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (String m : this) {
			buffer.append(m + "\n");
		}
		return buffer.toString();
	}
}
