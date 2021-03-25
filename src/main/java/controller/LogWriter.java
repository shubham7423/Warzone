package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The class that inherits Observer and whose function is to write the output to
 * the log file
 */
public class LogWriter implements Observer {

	FileWriter d_logFile;
	DateTimeFormatter d_dtf;
	LocalDateTime d_now;

	/**
	 * Constructor method for the class that attaches the object of LogEntryBuffer
	 * 
	 * @param p_logEntry object of LogEntryBuffer
	 */
	public LogWriter(LogEntryBuffer p_logEntry) {
		p_logEntry.attach(this);
		d_dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		d_now = LocalDateTime.now();
	}

	/**
	 * function to display the current timestamp and the state changes have taken
	 * places during execution
	 * 
	 * @param p_observable_state the object of Observable that contains the current
	 *                           state
	 */
	@Override
	public void update(Observable p_observable_state) {

		try {
			d_logFile = new FileWriter(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/log/logfile.log").toString(), true);
			d_logFile.append(d_dtf.format(d_now) + "> " + ((LogEntryBuffer) p_observable_state).getString() + "\n");
			d_logFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
