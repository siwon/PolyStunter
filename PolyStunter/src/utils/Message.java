/**
 * 
 */
package utils;

import java.util.List;

/**
 * @author Alexandre Bisiaux
 *
 */
public class Message {

	private List<String> errorMessages = new MessageList();
	private List<String> successMessages = new MessageList();
	
	public void addError(String message){
		this.errorMessages.add(message+ "\n");
	}
	
	public void addSuccess(String message){
		this.successMessages.add(message+ "\n");
	}
	
	public void clear() {
		this.errorMessages.clear();
		this.successMessages.clear();
	}
	
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public List<String> getSuccessMessages() {
		return successMessages;
	}

	public boolean isEmpty() {
		return this.errorMessages.isEmpty() || this.successMessages.isEmpty();
	}
	
}
