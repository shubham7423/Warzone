package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class LogWriter implements Observer {
	
	FileWriter d_logFile;
	
	public LogWriter(LogEntryBuffer p_logEntry) {
		p_logEntry.attach(this);
		try {
			d_logFile = new FileWriter(Paths.get(Paths.get("").toAbsolutePath().toString() + "/log/logfile.log").toString(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable p_observable_state) {
		try {
			d_logFile.write(((LogEntryBuffer)p_observable_state).getString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
