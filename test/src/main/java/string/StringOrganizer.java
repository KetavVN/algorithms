package string;

/*
of 0 votes
6
Answers

Given a string, check if it is can be reorganized such that the same char is not next to each other, If possible, output a possible result
example
input: google
one possible output: gogole
*/

public class StringOrganizer {

	public String organize(String str) {
		
		if(str == null || str.length() == 0) {
			return null;
		}
		
		char [] input = str.toCharArray();
		int i = 1;
		int replacements = 0;
		
		while(i < input.length && replacements < input.length) {
		
			if(input[i-1] == input[i]) {
				
				char swap = input[i];
				int j = i;
				
				while(j+1 < input.length) {
					input[j] = input[j+1];
					j++;
					if(input[j] != swap) {
						input[j] = swap;
						break;
					}
				}
				
				replacements++;
				
			} else {
				i++;
			}
		}
		
		if(i == input.length && replacements < input.length) {
			return new String(input);
		}
		
		return null;
		
	}

	public static void main (String ...args) {
		StringOrganizer obj = new StringOrganizer();
		System.out.println(String.format("000000=%s", obj.organize("000000")));
		System.out.println(String.format("google=%s", obj.organize("google")));
	}


}
