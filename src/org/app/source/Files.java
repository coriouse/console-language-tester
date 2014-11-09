package org.app.source;

import java.io.File;
/**
 * Inerface for file implementation
 * 
 * @author Ogarkov.Sergey
 *
 */
public interface Files extends Source {
	
	/**
	 * Method parses txt file 
	 * 
	 * file format is 
	 * 	 hello привет 
	 * 	 something  что то
	 * 	 it's apple это €блоко 	
	 * 
	 * @param file
	 */
	public void parse(File file);
}
