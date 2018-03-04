package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
You are given with a large paragraph and N words.
You have to find a min length subparagraph of the paragraph which contain all those N words in any order. Here length of a paragraph is the count of words in the paragraph.
**/
public class BookStore {

	private Map<String, List<Integer>> readBook(String book, Set<String> keywords) {
		Map<String, List<Integer>> keywordMap = new HashMap<>(0);
		String [] words = book.split(" ");	//get all words
		int i = 0;
		for(String word : words) {
			if(!keywordMap.containsKey(word)) {
				keywordMap.put(word, new ArrayList<Integer>());
			}
			keywordMap.get(word).add(i);
			i++;
		}
		return keywordMap;
	}

	private int [] getMinMax(List<Integer> arr) {
		if(arr == null || arr.isEmpty()) {
			return new int [] {Integer.MIN_VALUE, Integer.MAX_VALUE};
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i =0; i< arr.size(); i++) {
			if(arr.get(i) < min) {
				min = arr.get(i);
			}
			if(arr.get(i) > max) {
				max = arr.get(i);
			}
		}
		return new int [] {min, max};
	}
	
	private int getNearestValue(int pivot, List<Integer> arr, int startIndex, int endIndex) {
		if(startIndex < endIndex) {
			if(startIndex == (endIndex-1)) {
				int distance1 = Math.abs(pivot - arr.get(startIndex)), 
					distance2 = Math.abs(pivot - arr.get(endIndex));
				return distance1 > distance2 ? arr.get(endIndex) : arr.get(startIndex);
			}
			int midIndex = startIndex + (endIndex - startIndex) / 2;
			if(arr.get(midIndex) == pivot) {
				return arr.get(midIndex);
			} else if (arr.get(midIndex) > pivot) {
				return getNearestValue(pivot, arr, startIndex, midIndex);
			} else {
				return getNearestValue(pivot, arr, midIndex+1, endIndex);
			}
		}
		return arr.get(startIndex); // change this
	}
	
	public String getMinSubParagraph(String book, Set<String> keywords) {
		
		//input check
		if(book == null || book.length() ==0
			|| keywords == null || keywords.isEmpty()) {
			return null;
		}
		
		//create keyword<->positionList Map
		Map<String, List<Integer>> keywordMap = readBook(book, keywords);
		if(keywordMap.size()==1) {
			return keywords.iterator().next();
		}
		
		List<List<Integer>> indexArr = new ArrayList<>();
		for(String kw : keywords) {
			indexArr.add(keywordMap.get(kw));
		}
		
		int minDistance = Integer.MAX_VALUE;
		int index1 = 0, index2 = 0;
		for(int i = 0; i < indexArr.get(0).size(); i++) {	//reference elements are taken from 0th array
			List<Integer> path = new ArrayList<>(indexArr.size());
			path.add(indexArr.get(0).get(i));
			for(int j = 1; j < indexArr.size(); j++) {
				path.add(getNearestValue(path.get(0), indexArr.get(j), 0, indexArr.get(j).size()-1));
			}
			int [] minMax = getMinMax(path);
			int distance = (minMax[1]-minMax[0]);
			if(distance < minDistance) {
				minDistance = distance;
				index1 = minMax[0];
				index2 = minMax[1];
			}
		}
		
		return createAnsSubString(book, index1, index2);
	}

	private String createAnsSubString(String book, int index1, int index2) {
		String [] strArr = book.split(" ");
		StringBuffer sb = new StringBuffer();
		for(int i=index1; i<=index2; i++)
			sb.append(strArr[i]).append(" ");
		return sb.toString().trim();
	}
	
	public static void main (String ...args) {
		BookStore obj = new BookStore();
		String book = "this is a book with some statements i know no one will read it but i am writing "
				+ "this anyway Since you are still reading this i will tell you its just a test book";
		Set<String> keywords = new HashSet<>();
		keywords.add("book");
		keywords.add("anyway");
		System.out.println(obj.getMinSubParagraph(book, keywords));
	}
	
}
