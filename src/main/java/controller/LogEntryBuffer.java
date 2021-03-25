package controller;

/**
 * This is the class that inherits Observable class whose function is to obtain
 * the outputs that are obtained after executing commands
 */
public class LogEntryBuffer extends Observable {
	private String d_value;

	/**
	 * function to extract the string of output
	 * 
	 * @return output string of the executed command
	 */
	public String getString() {
		return d_value;
	}

	/**
	 * function to set the string of output to notify other observers of the change
	 * 
	 * @param p_value the string of output that is used to notify the observers
	 */
	public void setString(String p_value) {
		d_value = p_value;
		notifyObservers(this);
	}
}
