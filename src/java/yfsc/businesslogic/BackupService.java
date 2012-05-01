package yfsc.businesslogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class BackupService {

	private final Logger logger;
	private final String backupScriptPath;
	private final int scriptIgnoreLines;
	
	public BackupService(Logger logger, String backupScriptPath, int scriptIgnoreLines) {
		this.logger = logger;
		this.backupScriptPath = backupScriptPath;
		this.scriptIgnoreLines = scriptIgnoreLines;
	}
	
	
	public String getBackupScript() {
		
		logger.info("backing up database with BackupService...");		
		StringBuilder result = new StringBuilder();
		
		try {
			Process proc = Runtime.getRuntime().exec(backupScriptPath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proc.getOutputStream()));
			
			String line;
			for (int i = 0; i < scriptIgnoreLines; i++) {
				reader.readLine();
			}
			while((line = reader.readLine()) != null) {
				result.append(line).append("\n");
			}
			
			return result.toString();
		}
		catch (Exception e) {
			logger.warning("Error backing up database.");
			return null;
		}
	}
}
