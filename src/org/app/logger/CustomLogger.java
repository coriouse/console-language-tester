package org.app.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
/**
 * Create log result
 * 
 * @author Ogarkov.Sergey
 *
 */
public class CustomLogger {
	
	private final static String LOG_FILE_PATH = "result.txt";  
	private static File file;
	private static FileWriter fileWriter;
	
	 static {
			
			try {
				init();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	private static void init() throws IOException {
		Files.deleteIfExists( new File(LOG_FILE_PATH).toPath());
		file = new File(LOG_FILE_PATH);
		fileWriter = new FileWriter(file,true);
	}
	
	private static void  open() throws IOException {
		file = new File(LOG_FILE_PATH);
		fileWriter = new FileWriter(file,true);	
	}
	
	public static boolean isFile() {
		return file.exists();
	}
	
	public static void log(String message) {
		try {
			 open();
			fileWriter.write(message+"\r\n");
			 close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void close() throws IOException {
		fileWriter.close();
	}
}
