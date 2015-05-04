package org.app.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.app.module.Answer;
import org.app.module.Word;

/**
 * Collections of the questions with answers
 * @author Ogarkov.Sergey
 *
 */
public class LessonsCollection {
	
	private Map<Word, List<Answer>> request = new HashMap<>();
	
	public void put(Word word, List<Answer> answer) {
		this.request.put(word, answer);
	}
	
	public Map<Word, List<Answer>> getLessonsCollection() {
		return request;
	}

}
