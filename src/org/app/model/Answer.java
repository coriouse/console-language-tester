package org.app.model;

/**
 * Answer on question is 
 * 
 * @author Ogarkov.Sergey
 *
 */
public class Answer {
	
	public Word word;
	
	public boolean isRight = false;
	
	public Integer number;
	
	public Answer(Word word) {
		this.word = word;
	}
	
	public void isRight() {
		isRight = true;
	}
	
	public void isWrong() {
		isRight = false;
	}
}
