package org.app.source.impl;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.app.config.InitApp;
import org.app.module.Word;
import org.app.source.Files;

/**
 * File source implementation
 * 
 * @author Ogarkov.Sergey
 *
 */
public class SourceFile implements Files {
	
	private final static  Set<Word> list = new HashSet<>();
	
	private final static String SEPARATOR = "[ ]";
	
	@Override
	public List<Word> getWords() {
		List<Word> words = new ArrayList(list);
		Collections.shuffle(words);
		return words;
	}
	
	@Override
	public void parse(File file) {
		try {
			List<String> lines = java.nio.file.Files.readAllLines(file.toPath(), Charset.defaultCharset());
			for(String line : lines) {
				addWord(takeWord(line));
			}
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * add word to collection
	 * 
	 * @param word
	 */
	public void addWord(Word word) {
		if(word == null) {			
			throw new RuntimeException("Word can't to be null");
		}
		list.add(word);
	}
	
	/**
	 * take a part word from file(from line)
	 * @param text
	 * @return
	 */
	private Word takeWord(String text) {		
		String[] words = text.split(SEPARATOR);
		StringBuffer original = new StringBuffer();
		List<String> translate = new ArrayList<>();
		for(String s : words) {
			if(isOriginal(s)) {
				original.append(s.trim());
				original.append(" ");
			} else {
				translate.add(s);
			}
		}
		return new Word(original.toString(),  translate);
	}
	
	/**
	 * check if word id english(for example)
	 * 
	 * @param word
	 * @return
	 */
	//TODO it's not universal
	private boolean isOriginal(String word) {
		return word.matches(InitApp.getProperties("cutout"));
	}

	@Override
	public Integer getSize() {		
		return list.size();
	}
}
