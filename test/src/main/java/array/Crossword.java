package array;

/*
A Crossword grid is provided to you, along with a set of words (or names of places) which need to be filled into the grid.
The cells in the grid are initially, either + signs or - signs.
Cells marked with a + have to be left as they are. Cells marked with a - need to be filled up with an appropriate character.

Input Format

The input contains lines, each with characters (which will be either + or - signs).
After this follows a set of words (typically nouns and names of places), separated by semi-colons (;).

Constraints

There will be no more than ten words. Words will only be composed of upper-case A-Z characters. There will be no punctuation (hyphen, dot, etc.) in the words.

Output Format

Position the words appropriately in the grid, and then display the grid as the output. So, your output will consist of lines with characters each.

Sample Input 0

+-++++++++
+-++++++++
+-++++++++
+-----++++
+-+++-++++
+-+++-++++
+++++-++++
++------++
+++++-++++
+++++-++++
LONDON;DELHI;ICELAND;ANKARA

Sample Output 0

+L++++++++
+O++++++++
+N++++++++
+DELHI++++
+O+++C++++
+N+++E++++
+++++L++++
++ANKARA++
+++++N++++
+++++D++++

Sample Input 1

+-++++++++
+-++++++++
+-------++
+-++++++++
+-++++++++
+------+++
+-+++-++++
+++++-++++
+++++-++++
++++++++++
AGRA;NORWAY;ENGLAND;GWALIOR

Sample Output 1

+E++++++++
+N++++++++
+GWALIOR++
+L++++++++
+A++++++++
+NORWAY+++
+D+++G++++
+++++R++++
+++++A++++
++++++++++

 */

public class Crossword {

	//1. find a word with given length - Map<Integer, List<String>> lengthWordMap
	//2. find a "word" where given letter is in given position Map<Character, Map<Integer, List<String>>> charStringMap
	
	/**
		boolean traverse(crossword, visited) {
			if(visited.size() == totalWords) {
				return true;
			} else {
				for() {
					for() {
						when '-' found, count neighbor with same sign
						lets say count (number of - signs) are c
						find all possible words with that length
						for(String word : possibleWords) {
							newCrossword[][] = copy(crossword);
							boolean usable = isWordUsableInGiven[I][J]Location(newCrossword);
							if(usable) {
								newVisitedWords = copy(visitedWords);
								newVisitedWords.add(word);
								writeWord(newCrossword[][]);
								if(isComplete(newCrossword[][], visited)) {
									return true;
								}
								boolean isComplete = traverse(newCrossWord, newVisitedWords);
								if(isComplete) {
									return true;
								}
							}
						}
					}
				}
				return false;
			}
			
		}
		
	 */
	
	
}
