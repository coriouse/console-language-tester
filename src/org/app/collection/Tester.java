package org.app.collection;

import org.app.source.Source;
/**
 * Engine of the tests
 * 
 * @author Ogarkov.Sergey
 *
 */
public interface Tester {
	
	public void setCountWords(Integer countWords);
	
	public void setCountAnswers(Integer countAnswers);
	
	public void parse(Source source);
	
	public LessonsCollection getCollections();
	
	public void result();
}
