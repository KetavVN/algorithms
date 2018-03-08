package string;

//https://leetcode.com/problems/longest-palindromic-substring/description/
public class LongestPalindromeSubstring {

	public String longestPalindrome(String s) {

		char [] arr = s.toCharArray();
		int [] iArr1, iArr2;
		int maxLen = 0, len1=0, len2=0;
		String ret = null;

		for(int i=0; i<arr.length; i++) {

			iArr1 = getLen(arr, i, i);
			len1 = iArr1[1]-iArr1[0] + 1;

			len2=0;
			iArr2 = new int[2];
			if(i+1<arr.length) {
				iArr2 = getLen(arr, i, i+1);
				len2 = iArr2[1]-iArr2[0]+1;
			}

			if(len1>=len2 && len1>maxLen) {
				maxLen = len1;
				ret = s.substring(iArr1[0], iArr1[1]+1);
			} else if(len1<len2 && len2>maxLen) {
				maxLen = len2;
				ret = s.substring(iArr2[0], iArr2[1]+1);
			}
		}

		return ret;
	}

	private int [] getLen(char [] arr, int start, int end) {
		int [] ret = new int [2];
		while(start>=0 && end<arr.length && arr[start] == arr[end]) {
			start--;
			end++;
		}
		ret[0] = start+1;
		ret[1] = end-1;
		return ret;
	}

	public String longestPalindrome2(String s) {
		//input validation

		char [] arr = s.toCharArray();
		String ans = null;
		int len = 0, maxLen = 0;

		for(int i=0; i<arr.length; i++) {
			for(int w=arr.length-i; (i+w) > i; w--) {
				boolean isValid = isPalindrome(arr, i, i+w-1);
				if(maxLen < w && isValid) {
					maxLen = w;
					ans = s.substring(i, i+w);
				}
			}
		}
		return ans;
	}

	private boolean isPalindrome(char [] arr, int start, int end) {
		while(start<=end) {
			if(arr[start] != arr[end]) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String ...args) {
		LongestPalindromeSubstring obj = new LongestPalindromeSubstring();
		boolean flag = true;
		
		String s = "ababc";
		String ret = obj.longestPalindrome(s);
		Set<String> expected = new HashSet<>();
		expected.add("aba");
		expected.add("bab");
		if(!expected.contains(ret)) {
			System.out.println(String.format("FAIL: actual=%s expected=%s", ret, expected));
			flag=false;
		}
		
		s = "a";
		ret = obj.longestPalindrome(s);
		expected = new HashSet<>();
		expected.add("a");
		if(!expected.contains(ret)) {
			System.out.println(String.format("FAIL: actual=%s expected=%s", ret, expected));
			flag=false;
		}
		
		s = "abbc";
		ret = obj.longestPalindrome(s);
		expected = new HashSet<>();
		expected.add("bb");
		if(!expected.contains(ret)) {
			System.out.println(String.format("FAIL: actual=%s expected=%s", ret, expected));
			false=false;
		}
	
		if(flag) {
			System.out.println("PASS!");
		}
	
	}

}
