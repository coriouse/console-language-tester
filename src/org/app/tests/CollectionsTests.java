package org.app.tests;


import static org.junit.Assert.*;

import java.io.File;

import org.app.collection.LessonsCollection;
import org.app.collection.Tester;
import org.app.collection.impl.TesterImpl;
import org.app.module.Word;
import org.app.source.Files;
import org.app.source.impl.SourceFile;
import org.junit.Ignore;
import org.junit.Test;

public class CollectionsTests {

	private final static String WORS_TEST = "/word.txt";
	private File file; 
	public CollectionsTests() {
		file = new File(CollectionsTests.class.getResource(WORS_TEST).getFile());
	}
	
	@Test
	public void ParseFileTest() {
		Files source = new SourceFile();
		source.parse(file);		
		assertEquals(6, source.getWords().size());
		int count = 0;
		for(Word word : source.getWords()) {
			System.out.println(word);
			if("inferior".equals(word.original.trim())) {
				count++;	
			}
		}
		assertEquals(1, count);
		
		count = 0;
		for(Word word : source.getWords()) {
			if("enjoy hanging out with".equals(word.original.trim())) {
				count++;	
			}
		}
		assertEquals(1, count);
	}
	
	@Ignore
	public void collectionTest() {
		Files source = new SourceFile();
		source.parse(file);		
		Tester words = new TesterImpl();
		words.parse(source);
		LessonsCollection lessons = words.getCollections();
	}

}
