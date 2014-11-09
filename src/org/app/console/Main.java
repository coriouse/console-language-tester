package org.app.console;

import java.io.File;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import org.app.collection.LessonsCollection;
import org.app.collection.Tester;
import org.app.collection.impl.TesterImpl;
import org.app.logger.CustomLogger;
import org.app.model.Answer;
import org.app.model.Word;
import org.app.source.Files;
import org.app.source.impl.SourceFile;

public class Main {
	public static void main(String[] args) {
		
		if(args.length == 0 ) {
			System.out.println("java.exe -jar word.jar <путь до файла со словами> <количество слов в тесте> <количество ответов в вопросе>");
			System.exit(1);
		}
		
		String path = args[0];
		Integer countWords = Integer.valueOf(args[1]);		
		Integer countAnswers = Integer.valueOf(args[2]);
		
		
		//for debuging
		/*String path = "C:"+File.separator+"temp"+File.separator+"words1.txt";
		Integer countWords = 15;		
		Integer countAnswers = 5;*/
		
		//ru-en translate from english to russian, en-ru translate from russian to english 
		String reverse = "ru-en"; //en-ru
		
		
		File file = new File(path);
		Files source = new SourceFile();
		source.parse(file);		
		Tester words = new TesterImpl();		
		if(countWords != null) {
			words.setCountWords(countWords);
		}		
		if(countAnswers != null) {
			words.setCountAnswers(countAnswers);
		}
		words.parse(source);
		LessonsCollection lessons = words.getCollections();
		
		System.out.println("Количество слов в списке: "+source.getSize());
		System.out.println("Количество слов в тесте: "+(countWords != null ? countWords : 15));
		System.out.println("Количество вопросов: "+(countAnswers != null ? countAnswers : 3));
		
		System.out.println("Ответ выбираем цифрой");
		System.out.println("Для начала теста нажмите Enter");		 


		//TODO Чтение нескольких файлов из директории
		//TODO Сделать чтение слов из http://www.alleng.ru/mybook/7phv170/TOP170_alph.htm
		//TODO Сделать чтение их xml <words><word><original>word</original><translate>Слово</translate><transcript></transcript></word></words>
		//TODO Сделать чтение из БД
		//TODO Вынести в properti поиск латинских символов
		//TODO Занести по maven
		//TODO Логирование через Loggin
		//TODO Выбор источкина слов (выбор всех источников сразу)
		//TODO Подумать над много язычностью
		//TODO подумать над формирование xml готовых тестов для проигрования(что то смутное)
		//TODO Веб версия по такому же принципу
		//TODO плагины расширения (что то смутное), сделать  как библиотеку с плагинами 
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		int sum = 0;
		for(Entry<Word, List<Answer>> r :  lessons.getLessonsCollection().entrySet()) {
			++sum;
			System.out.println("--------------------Начало запроса-"+sum+"/"+countWords+"------------------------------------");
			String word = "Слово: ";
			if("ru-en".equals(reverse)) {
				word +=  r.getKey().original;
			} else if("en-ru".equals(reverse)) {
				word +=  r.getKey().getTranslationAsString();
			}
			System.out.println(word);
			
			System.out.println();
			System.out.println("Ответ:");
			for(Answer a : r.getValue()) {
				String answer = a.number+") ";
				if("ru-en".equals(reverse)) {
					answer += a.word.getTranslationAsString();
				} else if("en-ru".equals(reverse)) {
					answer += a.word.original;
				}
				System.out.println(answer);
			}
			System.out.println("-------------------Конец запроса------------------------------------");
			System.out.print("Введите ответ:");
			try{
				r.getKey().yourAnswers = scanner.nextInt();
				String writeAnswer = null;
				boolean isWrong = true;
					for(Answer a : r.getValue()) {
						if(a.isRight == true) {		
							String answer = a.number+") ";
							if("ru-en".equals(reverse)) {
								answer += a.word.getTranslationAsString();
							} else if("en-ru".equals(reverse)) {
								answer += a.word.original;
							}							
							writeAnswer = "OOOPS!!! Правильный ответ!   Ответ: "+ answer;
						}
						if(a.number == r.getKey().yourAnswers) {
							if(a.isRight == true)
								isWrong = false;	
						} 
					}
				if(isWrong) {
					System.out.println(writeAnswer);
				}
			} catch(InputMismatchException e) {
				System.out.println("Ответ должен быть цифрой:");
				System.out.println("Надо запустеть тест снова :(");
				System.exit(1);
			}
			scanner.nextLine();
		}
		words.result();
		System.out.println("Тест завершен");
	}
}
