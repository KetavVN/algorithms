package string;

import java.util.HashMap;
import java.util.Map;

//https://www.hackerrank.com/challenges/bear-and-steady-gene/problem
public class GeneSubSetReplacement {
	
	static int steadyGene2(String gene) {
        
        if(gene == null || gene.length()==0) {
            return 0;
        }
        
        int n = gene.length();
        char [] expected = new char [] {'A', 'C', 'G', 'T'};
        int count = n/4;
        Map<Character, Integer> charCount = new HashMap<>();
        for(int i=0;i<expected.length; i++)
            charCount.put(expected[i], 0);
        
        boolean [] error = new boolean [gene.length()];
        for(int i=0;i<n; i++) {
            int c = charCount.get(gene.charAt(i));
            if(c > (count-1)) {
                error[i] = true;
            }
            charCount.put(gene.charAt(i), c+1);
        }
        
        Integer start = null, end = null;
        for(int i=0; i<n; i++) {
            if(error[i]) { 
                if(start == null) {
                    start = i;
                }
                end = i;
            } 
        }
        
        if(start != null && end!=null) {
            //System.out.println(charCount);
            //System.out.println(Arrays.toString(error));
            return end-start+1;
        }
        
        return 0;
    }

	public static void main(String[] args) {
	    String gene = "AGGAAACCCGTT";
	    int result = steadyGene2(gene);
	    System.out.println(result);
	}
}

/*
A gene is represented as a string of length (where is divisible by ), composed of the letters , , , and . It is considered to be steady if each of the four letters occurs exactly times. For example, and are both steady genes.

Bear Limak is a famous biotechnology scientist who specializes in modifying bear DNA to make it steady. Right now, he is examining a gene represented as a string . It is not necessarily steady. Fortunately, Limak can choose one (maybe empty) substring of and replace it with any string of the same length.

Modifying a large substring of bear genes can be dangerous. Given a string , can you help Limak find the length of the smallest possible substring that he can replace to make a steady gene?

Note: A substring of a string is a subsequence made up of zero or more consecutive characters of .

Input Format

The first line contains an interger divisible by , denoting the length of a string .
The second line contains a string of length . Each character is one of the four: , , , .

Constraints

    is divisible by

Subtask

    in tests worth points.

Output Format

On a new line, print the minimum length of the substring replaced to make stable.

Sample Input

8  
GAAATAAA

Sample Output

5

Explanation

One optimal solution is to replace a substring with , resulting in . The replaced substring has length , so we print on a new line.
 */
