package org.app.console;

import java.io.File;



/**
 * 
 * Command line 
 * 
 * java -jar words.jar cw=100 ca=5 r=ru-en p=words.txt
 * 
 * cw // sum of words in tests
 * ca // sum of answers in question 
 * r  //ru-en translate from english to russian, en-ru translate from russian to english
 * p  //path to file with words  
 * 
 * @author Ogarkov.Sergey
 *
 */
public class Main {
	public static void main(String[] args) {
		//for debug
		String[] arg = new String[4];
		arg[0] = "cw=15";
		arg[1] = "ca=5";
		arg[2] = "p=C:\\temp\\words1.txt";
		arg[3] = "r=ru-en";
		

		//TODO Чтение нескольких файлов из директории
		//TODO Сделать чтение слов из http://www.alleng.ru/mybook/7phv170/TOP170_alph.htm
		//TODO Сделать чтение их xml <words><word><original>word</original><translate>Слово</translate><transcript></transcript></word></words>
		//TODO Сделать чтение из БД
		//TODO Занести по maven
		//TODO Логирование через Loggin
		//TODO Выбор источкина слов (выбор всех источников сразу)
		//TODO подумать над формирование xml готовых тестов для проигрования(что то смутное)
		//TODO Веб версия по такому же принципу
		//TODO плагины расширения (что то смутное), сделать  как библиотеку с плагинами
		Run run = new Run(arg);		
			run.lunch();
	}
}
