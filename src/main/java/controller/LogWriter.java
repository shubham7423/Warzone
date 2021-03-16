package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter implements Observer {
	
	FileWriter d_logFile;
	DateTimeFormatter d_dtf;
	LocalDateTime d_now;
	
	public LogWriter(LogEntryBuffer p_logEntry) {
		p_logEntry.attach(this);
		d_dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        d_now = LocalDateTime.now();
//		try {
//			d_logFile = new FileWriter(Paths.get(Paths.get("").toAbsolutePath().toString() + "/log/logfile.log").toString(), true);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@Override
	public void update(Observable p_observable_state) {
		
		try {
			d_logFile = new FileWriter(Paths.get(Paths.get("").toAbsolutePath().toString() + "/log/logfile.log").toString(), true);
			d_logFile.append(d_dtf.format(d_now) + "> " + ((LogEntryBuffer)p_observable_state).getString() + "\n");
			d_logFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
