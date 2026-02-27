package com.zensar.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//Create a concrete implementation of AutoComplete such that we get the following answer when called.
//addWordToDictionary("pizza");
//addWordToDictionary("piz");
//addWordToDictionary("polar")
//List<String> ret = findWordsWithPrefix("pi")// returns ["piz","pizza"]                                                                                                                                         
interface AutoComplete{
	List<String> findWordsWithPrefix(String prefix);
	void addWordToDictionary(String word);
	List<String> list = new ArrayList<>();
}

public class Temp13 implements AutoComplete{

	public static void main(String[] args) {
		Temp13 obj = new Temp13();
		obj.addWordToDictionary("pizza");
		obj.addWordToDictionary("piz");
		obj.addWordToDictionary("polar");
		List<String> findWordsWithPrefix = obj.findWordsWithPrefix("piz");
		findWordsWithPrefix.stream().forEach(System.out::println);
	}

	@Override
	public List<String> findWordsWithPrefix(String prefix) {
		return list.stream().filter(str->str.startsWith(prefix)).collect(Collectors.toList());
	}

	@Override
	public void addWordToDictionary(String word) {
		list.add(word);
	}

}
