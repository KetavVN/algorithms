package string;

/**
 * Inplace URL encode 
 * from : 'www.xyz.com/my space/  '
 * 	 to : 'www.xyz.com/my%20space/'
 * 
 * Assume input array has enough space at the end and original string length is provided 
 * 
 * Cracking the coding interview Ch1 Problem 3
 * 
 * @author ketav
 */
public class URLify {

	public void urlify(char [] url, int len) {
		if(url == null || url.length == 0) {
			return;
		}
		int i = len-1;
		int j = url.length-1;
		while(i<j) {
			if(url[i]!=' ') {
				url[j--] = url[i--];
			} else {
				url[j--] = '0';
				url[j--] = '2';
				url[j--] = '%';
			}
		}
	}
	
	public static void main(String [] args) {
		URLify obj = new URLify();
		char c [] = new char [] {'m','y',' ','s','p','a','c','e',' ',' '};
		obj.urlify(c, 8);
		System.out.println(c);
	}
	
}
