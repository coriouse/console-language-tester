package org.app.collection.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

import org.app.collection.LessonsCollection;
import org.app.collection.Tester;
import org.app.logger.CustomLogger;
import org.app.module.Answer;
import org.app.module.Word;
import org.app.source.Source;
/**
 * Implementation if the engine, implementation can be a lot
 * @author Ogarkov.Sergey
 *
 */
public class TesterImpl implements Tester {
	
	private  Integer count = 15;
	private  Integer countAnswers = 3; 
	private final static LessonsCollection lessonsCollection = new LessonsCollection(); 

	@Override
	public void parse(Source source) {
		Collections.shuffle(source.getWords());
		List<Word> words = new ArrayList<>(source.getWords().subList(0, this.count));
		Collections.shuffle(words);
		for(Word word : words) {
			lessonsCollection.put(word, getAnswer(word, source.getWords()));
		}
	}

	private List<Answer> getAnswer(Word word, List<Word> words) {
		List<Word> answersWords = new ArrayList<>();
		answersWords.addAll(words);
		answersWords.remove(word);
		Collections.shuffle(answersWords);
		List<Answer> answers = new ArrayList<>();
		//right answer
		Answer answer = new Answer(word);
		answer.isRight();
		answers.add(answer);
		//wrong answer
		List<Word> wrongAnswes = new ArrayList<>(answersWords.subList(0, this.countAnswers-1));
		for(Word w : wrongAnswes) {
			answers.add(new Answer(w));
		}
		Collections.shuffle(answers);
		int count = 1;
		for(Answer number : answers) {
			number.number = count++;
		}
		return answers;
	}
	
	@Override
	public LessonsCollection getCollections() {
		return lessonsCollection;
	}

	@Override
	public void setCountWords(Integer count) {
		if(count != null) {
			this.count = count;
		}	
	}
	
	@Override
	public void result() {
		int wrongCount = 0;
		int rightCount = 0;
		for( Entry<Word, List<Answer>> r :  lessonsCollection.getLessonsCollection().entrySet()) {		
			for(Answer a : r.getValue()) {
				if(a.number == r.getKey().yourAnswers) {
					if(a.isRight == true) {
						rightCount++;
						CustomLogger.log("Правильно!  Вопрос:"+ a.word.original +", Ответ: "+ a.word.translation);
					} else {
						wrongCount++;
						CustomLogger.log("Не правильно!  Вопрос:"+ r.getKey().original +", Не верный ответ: "+ a.word.translation+", Верный ответ:"+getWriteAnswer(r.getValue()));
					}
				}
			}
		}
		System.out.println("Правильных ответов:"+rightCount);
		System.out.println("Не правильных ответов:"+wrongCount);
		System.out.println("Ответы лежат в файле result.txt");
	}
	
	private String getWriteAnswer(List<Answer> asnwers) {
		 for(Answer a : asnwers) {
			 if(a.isRight == true) {
				 return a.word.getTranslationAsString();
			 }
		 }
		 return null;
	}

	@Override
	public void setCountAnswers(Integer countAnswers) {
		if(countAnswers != null) {
			this.countAnswers = countAnswers;
		}	
		
	}
	
	

}
