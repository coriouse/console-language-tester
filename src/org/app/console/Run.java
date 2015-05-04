package org.app.console;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import org.app.collection.LessonsCollection;
import org.app.collection.Tester;
import org.app.collection.impl.TesterImpl;
import org.app.config.InitApp;
import org.app.console.params.ParamBuilder;
import org.app.i18n.Message;
import org.app.i18n.Message.MessageType;
import org.app.module.Answer;
import org.app.module.Word;
import org.app.source.Files;
import org.app.source.impl.SourceFile;



public class Run {
 
	private LessonsCollection lessons;
	private ParamBuilder console; //params checker
	private Message message;
	private Tester words;
	
	public Run(String[] args) {
		console = new ParamBuilder(args);
		message = new Message();
		
		
		Files source = new SourceFile();
			source.parse(new File((String) console.getValue("p")));
		
		words  = new TesterImpl();
			words.setCountWords((Integer) console.getValue("cw"));
			words.setCountAnswers((Integer) console.getValue("ca"));
			
		words.parse(source);
		lessons = words.getCollections();
	}
		
	public void lunch() {		
		message.message("message.count.word.in.the.list|"+lessons.getLessonsCollection().size(), MessageType.COMPLEX);
		message.message("message.sum.all|"+(console.getValue("cw") != null ? console.getValue("cw") : InitApp.getProperties("cw")), MessageType.COMPLEX);
		message.message("message.sum.answers|"+(console.getValue("ca") != null ? console.getValue("ca") : InitApp.getProperties("ca")), MessageType.COMPLEX);
		message.message("message.select.answer.number", MessageType.LINE);
		message.message("message.enter.for.start", MessageType.LINE);
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		int sum = 0;
		for(Entry<Word, List<Answer>> r :  lessons.getLessonsCollection().entrySet()) {
			++sum;
			message.message("message.dash|message.begin.of.the.question|"+sum+"/"+console.getValue("cw")+"|message.dash", MessageType.COMPLEX);
			message.message("message.word|"+getWord(r), MessageType.COMPLEX);
			message.message(MessageType.EMPTY);
			message.message("message.aswer", MessageType.LINE);
			for(Answer a : r.getValue()) {
				message.message(a.number+")|"+getWord(a), MessageType.COMPLEX);
			}
			message.message("message.dash|message.end.of.the.question|message.dash", MessageType.COMPLEX);
			message.message("message.enter.asnwer", MessageType.LINE);
			try{
				r.getKey().yourAnswers = scanner.nextInt();
				rightAnswer(r);
			} catch(InputMismatchException e) {
				message.message("message.answer.must.be.number", MessageType.LINE);
				message.message("message.test.shold.start.again", MessageType.LINE);
				System.exit(1);
			}
			scanner.nextLine();
		}
		words.result();		
		message.message("message.test.compledted", MessageType.LINE);
	}
	
	private void rightAnswer(Entry<Word, List<Answer>> r) {
		String writeAnswer = null;
		boolean isWrong = true;
			for(Answer a : r.getValue()) {
				if(a.isRight == true) {
					message.message(a.number+")|"+getWord(a), MessageType.COMPLEX);
					writeAnswer = "OOOPS!!! Правильный ответ!   Ответ: "+ getWord(a);
				}
				if(a.number == r.getKey().yourAnswers) {
					if(a.isRight == true)
						isWrong = false;	
				} 
			}
		if(isWrong) {
			System.out.println(writeAnswer);
		}
	}
	
	private String getWord(Entry<Word, List<Answer>> r) {
		String word = null;
		if("ru-en".equals(console.getValue("r"))) {
			word = r.getKey().original;
		} else if("en-ru".equals(console.getValue("r"))) {
			word = r.getKey().getTranslationAsString();
		}
		return word;
	}
	
	private String getWord(Answer a) {
		String word = null;
		if("ru-en".equals(console.getValue("r"))) {
			word = a.word.getTranslationAsString();
		} else if("en-ru".equals(console.getValue("r"))) {
			word = a.word.original;
		}
		return word;
	}
	

}
