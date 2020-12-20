package string;

/**
 * Cracking the coding interview
 * Ch-1 Problem 6
 * @author ketav
 *
 */
public class CompressString {

	public String compress(String str) {
		
		if(str == null || str.isEmpty()) {
			return str;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<str.length(); i++) {
			sb.append(str.charAt(i));
			int j=i+1;
			for(; j<str.length() && str.charAt(i) == str.charAt(j); j++);
			if( (j-i) > 1)
				sb.append(j-i);
			i=j-1;
		}
		
		String ret = sb.toString();
		return ret.length() >= str.length() ? str : ret; 
	}
	
	public static void main(String [] args ) {
		CompressString cs = new CompressString();
		System.out.println(cs.compress("aaabcckkkk"));
		System.out.println(cs.compress("abcd"));
	}
	
}
