package org.app.model;

import java.util.Arrays;
import java.util.List;
/**
 * it's word
 * 
 * @author Ogarkov.Sergey
 *
 */
public class Word {

	public String original;	
	public List<String>  translation;
	public Integer yourAnswers;
	
	public Word(String original, List<String> translation) {
		this.original = original;
		this.translation = translation;
	}

	@Override
	public String toString() {
		return "Word [original=" + original + ", translation=" + Arrays.toString(translation.toArray())
				+ "]";
	}
	
	public String getTranslationAsString() {
		StringBuffer sb = new StringBuffer();
		for(String s : translation) {
			sb.append(s);
			sb.append(" ");
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;		
		return prime * result+original.trim().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Word other = (Word) obj;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		
		return true;
	}
	
	
	
	
}
