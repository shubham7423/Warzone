package controller;

public class LogEntryBuffer extends Observable {
	private String d_value;
	
	public String getString() {
		return d_value;
	}
	
	public void setString(String p_value) {
		d_value = p_value;
		notifyObservers(this);
	}
}
