package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PasswordHacker {

	private static class MyStack extends ArrayList<String> {
		private static final long serialVersionUID = -4595536762720372408L;
		private List<Integer> lenArr = new ArrayList<>();
		public void push(int len, String item) {
			super.add(item);
			lenArr.add(len);
		}
		public String pop(int len) {
			String retStr = null;
			while(lenArr.get(super.size()-1) > len) {
				lenArr.remove(super.size()-1);
				retStr = super.remove(super.size()-1);
			}
			return retStr;
		}
		@Override
		public String toString() {
			return super.toString();
		}
		public MyStack getUniqueSet() {
			MyStack newObj = new MyStack();
			for(int i=0; (i+1)<super.size(); i++) {
				if(!super.get(i+1).contains(super.get(i))) {
					newObj.add(super.get(i));
				}
			}
			newObj.add(super.get(super.size()-1));
			return newObj;
		}
	}
	
	private static class Trie {
		private char val;
		private Map<Trie, Trie> children = new HashMap<>();
		boolean isWord;

		public Trie(char v, boolean isWord) {
			val = v;
			this.isWord = isWord;
		}

		@Override
		public boolean equals(Object o) {
			if(o instanceof Trie) {
				return val == ((Trie)o).val;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return (int) val-'0';
		}
		
		@Override
		public String toString() {
			return Character.toString(val);
		}

		public void setIsWord(boolean word) {
			isWord = word;
		}

		public static boolean isWord(String str, Trie root) {
			for(int i=0;i<str.length(); i++) {
				Trie node = new Trie(str.charAt(i), false);
				if(root.children.containsKey(node)) {
					root = root.children.get(node);
				} else {
					return false;
				}
			}
			return root.isWord;
		}

		public static boolean canWordBeformed(String str, Trie root) {
			for(int i=0;i<str.length(); i++) {
				Trie node = new Trie(str.charAt(i), false);
				if(root.children.containsKey(node)) {
					root = root.children.get(node);
				} else {
					return false;
				}
			}
			return true;
		}
	}

	public static Trie buildTrie(String [] passwords) {
		Trie root = new Trie('r', false);
		for(int i =0; i<passwords.length; i++) {
			Trie runner = root;
			for(int j=0; j<passwords[i].length(); j++) {
				Trie child = new Trie(passwords[i].charAt(j), j==(passwords[i].length()-1));
				if(!runner.children.containsKey(child)) {
					runner.children.put(child, child);
				} else {
					if(!runner.children.get(child).isWord) {
						runner.children.get(child).setIsWord(j==(passwords[i].length()-1));
					}
				}
				runner = runner.children.get(child);
			}
		}
		return root;
	}
	
	//original with max score - 21 passed 14 failed
	static String passwordCracker(String[] pass, String attempt) {
        if(pass == null || pass.length == 0 || attempt == null || attempt.length() == 0) {
            return "WRONG PASSWORD";
        }
		Trie root = buildTrie(pass);
		StringBuilder sb = new StringBuilder();
		StringBuilder retStr = new StringBuilder();
		for(int i=0; i<attempt.length(); i++) {
			sb.append(attempt.charAt(i));
			if(Trie.isWord(sb.toString(), root)) {
				retStr.append(sb).append(" ");
				sb = new StringBuilder();
			} else if (!Trie.canWordBeformed(sb.toString(), root)) {
				return "WRONG PASSWORD";
			}
		}
		return retStr.toString().substring(0, retStr.length()-1);
    }
	
	//lowest score -- all incorrect
	static String passwordCracker2(String[] pass, String attempt) {
		Trie root = buildTrie(pass);
		StringBuilder sb = new StringBuilder();
		StringBuilder retStr = new StringBuilder();
		Stack<Integer> backtrace = new Stack<>();
		int space = 0;
		for(int i=0; i<attempt.length(); i++) {
			sb.append(attempt.charAt(i));
			if(Trie.isWord(sb.toString(), root)) {
				//if(retStr.length() == 0) {
					retStr.append(sb).append(" ");
					System.out.println("main\t:"+ retStr+"|");
					space++;
				//}
				backtrace.add(i);
			} else if (!Trie.canWordBeformed(sb.toString(), root)) {
				if(backtrace.size() > 0) {
					int back=backtrace.pop();
					space--;
					System.out.println("before\t:"+ retStr+"|\ti="+i+"\tback="+back+"\tspace="+space+"\treduce="+(i-back-space)+"\tlength="+retStr.length()+"\tnew-length:"+(retStr.length()-(i-back)-space));
					retStr = new StringBuilder(retStr.substring(0, retStr.length()-(i-back)-space).trim()).append(" ");
					//space--;
					i = back;
					System.out.println("after\t:"+ retStr+"|");
					sb = new StringBuilder();
				} else {
					return "WRONG PASSWORD";
				}
			}
		}
		System.out.println(retStr+"|");
		return retStr.toString().substring(0, retStr.length()-1);
    }
	
	//most timed out - lowest incorrect
	static String passwordCracker3(String[] pass, String attempt) {
		Trie root = buildTrie(pass);
		StringBuilder sb = new StringBuilder();
		Stack<Integer> backtrace = new Stack<>();
		MyStack words = new MyStack();
		for(int i=0; i<attempt.length(); i++) {
			sb.append(attempt.charAt(i));
			if(Trie.isWord(sb.toString(), root)) {
				//words.pop(i);
				words.push(i, sb.toString());
				backtrace.add(i);
				System.out.println("main:"+words+"\tsb="+sb+"\tbacktrace"+backtrace+"\ti="+i);
			} else if (!Trie.canWordBeformed(sb.toString(), root)) {
				if(backtrace.size() > 0) {
					int back=backtrace.pop();
					System.out.println("before="+words+"\tsb="+sb.toString()+"\tbacktrace="+backtrace+"\ti="+i+"\tback="+back);
					words.pop(back);
					i = back;
					sb = new StringBuilder();
					System.out.println("after="+words+"\tsb="+sb.toString()+"\tbacktrace="+backtrace+"\ti="+i);
				} else {
					return "WRONG PASSWORD";
				}
			} else {
				System.out.println("i="+i+"\tsb="+sb);
			}
		}
		StringBuilder ret = new StringBuilder();
		MyStack obj = words.getUniqueSet();
		for(int i=0; i<obj.size(); i++) {
			ret.append(obj.get(i)).append((i!=(obj.size()-1)) ? " ":"");
		}
		return ret.toString();
    }
	
    public static void main(String ...args) {
		String [] pass = {"we", "web", "adaman", "barcod"};
		String attempt = "webarcodwebadamanweb"; //we barcod web adaman web
		System.out.println(passwordCracker(pass, attempt));

		/*String [] pass1 = {"hello", "planet"};
		attempt = "helloworld";
		System.out.println(passwordCracker(pass1, attempt));*/
    }

}

/*
There are N users registered on a website CuteKittens.com. Each of them have a unique password represented by pass[1], pass[2], ..., pass[N]. As this a very lovely site, many people want to access those awesomely cute pics of the kittens. But the adamant admin doesn't want this site to be available for general public. So only those people who have passwords can access it.

Yu being an awesome hacker finds a loophole in their password verification system. A string which is concatenation of one or more passwords, in any order, is also accepted by the password verification system. Any password can appear 0 or more times in that string. He has access to each of the N passwords, and also have a string loginAttempt, he has to tell whether this string be accepted by the password verification system of the website.

For example, if there are 3 users with password {"abra", "ka", "dabra"}, then some of the valid combinations are "abra" (pass[1]), "kaabra" (pass[2]+pass[1]), "kadabraka" (pass[2]+pass[3]+pass[2]), "kadabraabra" (pass[2]+pass[3]+pass[1]) and so on.

Input Format

First line contains an integer T, the total number of test cases. Then T test cases follow.
First line of each test case contains N, the number of users with passwords. Second line contains N space separated strings, pass[1] pass[2] ... pass[N], representing the passwords of each user. Third line contains a string, loginAttempt, for which Yu has to tell whether it will be accepted or not.

Constraints

    , where
    1 < length(loginAttempt) <= 2000
    loginAttempt and pass[i] contains only lowercase latin characters ('a'-'z').

Output Format

For each valid string, Yu has to print the actual order of passwords, separated by space, whose concatenation results into loginAttempt. If there are multiple solutions, print any of them. If loginAttempt can't be accepted by the password verification system, then print WRONG PASSWORD.

Sample Input 0

3
6
because can do must we what
wedowhatwemustbecausewecan
2
hello planet
helloworld
3
ab abcd cd
abcd

Sample Output 0

we do what we must because we can
WRONG PASSWORD
ab cd

Explanation 0

Sample Case #00: "wedowhatwemustbecausewecan" is the concatenation of passwords {"we", "do", "what", "we", "must", "because", "we", "can"}. That is

loginAttempt = pass[5] + pass[3] + pass[6] + pass[5] +  pass[4] + pass[1] + pass[5] + pass[2]

Note that any password can repeat any number of times.

Sample Case #01: We can't create string "helloworld" using the strings {"hello", "planet"}.

Sample Case #02: There are two ways to create loginAttempt ("abcd"). Both pass[2] = "abcd" and pass[1] + pass[3] = "ab cd" are valid answers.

Sample Input 1

3
4
ozkxyhkcst xvglh hpdnb zfzahm
zfzahm
4
gurwgrb maqz holpkhqx aowypvopu
gurwgrb
10
a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa
aaaaaaaaaab

Sample Output 1

zfzahm
gurwgrb
WRONG PASSWORD


*/
