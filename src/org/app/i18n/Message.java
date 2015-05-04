package org.app.i18n;

import java.io.IOException;
import java.util.Properties;

import org.app.config.InitApp;
/**
 * Class i18n handler
 * 
 * @author Ogarkov.Sergey
 *
 */
public class Message {
	
	public static String DASH = "--------------------";
	
	public enum MessageType {
		COMPLEX, EMPTY, LINE, RETURN_LINE, DASH;
	}	
	
	private static Properties properties = new Properties();

	public Message() {
		selectLang();
	}

	private void selectLang() {
		try {			
			properties.load(ClassLoader.getSystemResourceAsStream("org/app/properties/message_"+InitApp.getProperties("language")+".properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void messageCollect(String message) {
		String[] words = message.split("[|]");
		for(String word : words) {
			System.out.print(messageByKey(word.trim())+" ");
		}
		System.out.println();
	}
	
	public void message(MessageType messageType) {
		message("", messageType);
	}
	
		
	public String message(String key, MessageType messageType) {
		switch(messageType) {
			case RETURN_LINE:
				return messageByKey(key);				
			case COMPLEX:
				messageCollect(key);
				break;
			case LINE:
				messageLine(key);
				break;
			case  DASH:
				dashLine();
				break;
			case EMPTY:
				emtyLine();
				break;
		}
		return null;
	}
	
	
	private String messageByKey(String key) {
		return properties.getProperty(key) == null ? key : properties.getProperty(key)+" ";
	}	
	
	private void messageLine(String key) {		
		System.out.println(messageByKey(key)); 
	}
	
	private void emtyLine() {
		System.out.println();
	}
	
	private void dashLine() {
		System.out.println(Message.DASH);
	}
	
	public static void main(String[] args) {
		Message m = new Message();
		m.message("message.dash|"+(2+5)+"/"+(10+10)+"|message.dash", MessageType.COMPLEX);
		m.message(MessageType.DASH);
		m.message(MessageType.EMPTY);
		m.message("message.dash", MessageType.LINE);
		System.out.println(m.message("message.dash", MessageType.RETURN_LINE));
	}
}
