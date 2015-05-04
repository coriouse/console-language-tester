package org.app.config;

import java.io.IOException;
import java.util.Properties;
/**
 * Class init main config of app
 * 
 * @author Ogarkov.Sergey
 *
 */
public class InitApp {
	
	private static Properties properties = new Properties();
	
	static {
		init();
	}
	
	private static void init() {
		try {			
			properties.load(ClassLoader.getSystemResourceAsStream("org/app/properties/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperties(String key) {
		return properties.getProperty(key);
	}
	
	
	/*public static void main(String[] args) {
		InitApp.init();
	}*/
	

}
