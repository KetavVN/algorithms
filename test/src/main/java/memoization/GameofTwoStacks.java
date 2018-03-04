package memoization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//https://www.hackerrank.com/challenges/game-of-two-stacks/problem
public class GameofTwoStacks {

	private static class Point {
		int x, y, width;
		public Point(int x, int y, int w) {
			this.x = x;
			this.y = y;
			width = w;
		}
		@Override
		public int hashCode() {
			return x * width + y;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			return hashCode() == other.hashCode();
		}
		@Override
		public String toString() {
			return String.format("Point [x=%s, y=%s, width=%s]", x, y, width);
		}
	}

	private static int table[][];
	private static int [] a,b;

	//full Impl - recursive + no memoization
	public static int getMaxRemovedItems1(int [] a, int [] b, int x) {
		int aInd = 0, bInd = 0;
		return getMaxRemovedItems1(a, b, aInd, bInd, x, 0, 0);
	}

	public static int getMaxRemovedItems1(int [] a, int [] b, int aInd, int bInd, int x, int total, int items) {
		if(total > x) {
			return items-1;
		} else {
			int aRemoved = items;
			if(aInd < a.length) {
				aRemoved = getMaxRemovedItems1(a, b, aInd+1, bInd, x, total+a[aInd], aRemoved+1);
			}
			int bRemoved = items;
			if(bInd < b.length) {
				bRemoved = getMaxRemovedItems1(a, b, aInd, bInd+1, x, total+b[bInd], bRemoved+1);
			}
			return aRemoved > bRemoved ? aRemoved : bRemoved;
		}
	}

	//full Impl - recursive + memoization - 2d table - each method call stack uses too much space and therefore ends up using too much heap space!! - try using global/class level variable
	public static int getMaxRemovedItems2(int [] a, int [] b, int x) {
		int aInd = 0, bInd = 0;
		int table[][] = new int [a.length][b.length];
		return getMaxRemovedItems2(a, b, table, aInd, bInd, x, 0, 0);
	}

	public static int getMaxRemovedItems2(int [] a, int [] b, int [][] table, int aInd, 
			int bInd, int x, int total, int items) {
		if(total > x) {
			return items;
		} else if (aInd < a.length && bInd < b.length && table[aInd][bInd] != 0) {
			return table[aInd][bInd];
		} else {
			int aRemoved = items;
			if(aInd < a.length && (total+a[aInd])<= x) {
				aRemoved = getMaxRemovedItems2(a, b, table, aInd+1, bInd, x, total+a[aInd], items+1); 
			}
			int bRemoved = items;
			if(bInd < b.length && (total+b[bInd])<=x) {
				bRemoved = getMaxRemovedItems2(a, b, table, aInd, bInd+1, x, total+b[bInd], items+1);
			}
			if(aInd<a.length && bInd<b.length) {
				table[aInd][bInd] = aRemoved > bRemoved ? aRemoved : bRemoved;
			}
			return aRemoved > bRemoved ? aRemoved : bRemoved;
		}
	}

	//full Impl - recursive + memoization - with 2d table + all global variables - Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	public static int getMaxRemovedItems3(int x) {
		int aInd = 0, bInd = 0;
		table = new int [a.length][b.length];
		return getMaxRemovedItems3(aInd, bInd, x, 0, 0);
	}

	public static int getMaxRemovedItems3(int aInd, int bInd, int x, int total, int items) {
		if(total > x) {
			return items;
		} else if (aInd < a.length && bInd < b.length && table[aInd][bInd] != 0) {
			return table[aInd][bInd];
		} else {
			int aRemoved = items;
			if(aInd < a.length && (total+a[aInd])<= x) {
				aRemoved = getMaxRemovedItems3(aInd+1, bInd, x, total+a[aInd], items+1); 
			}
			int bRemoved = items;
			if(bInd < b.length && (total+b[bInd])<=x) {
				bRemoved = getMaxRemovedItems3(aInd, bInd+1, x, total+b[bInd], items+1);
			}
			if(aInd<a.length && bInd<b.length) {
				table[aInd][bInd] = aRemoved > bRemoved ? aRemoved : bRemoved;
			}
			return aRemoved > bRemoved ? aRemoved : bRemoved;
		}
	}

	//full Impl - recursive + memoization - with HashMap + all global variables - stack overflow!!
	public static int getMaxRemovedItems5(int x) {
		Map<Point, Integer> table = new HashMap<>();
		return getMaxRemovedItems5(0, 0, x, 0, 0, table);
	}

	public static int getMaxRemovedItems5(int aInd, int bInd, int x, int total, int items, Map<Point, Integer> table) {
		if(total > x) {
			return items;
		} else if (aInd < a.length && bInd < b.length && table.containsKey(new Point(aInd, bInd, b.length))) {
			return table.get(new Point(aInd, bInd, b.length));
		} else {
			int aRemoved = items;
			if(aInd < a.length && (total+a[aInd])<= x) {
				aRemoved = getMaxRemovedItems5(aInd+1, bInd, x, total+a[aInd], items+1, table); 
			}
			int bRemoved = items;
			if(bInd < b.length && (total+b[bInd])<=x) {
				bRemoved = getMaxRemovedItems5(aInd, bInd+1, x, total+b[bInd], items+1, table);
			}
			int val = aRemoved > bRemoved ? aRemoved : bRemoved;
			if(aInd<a.length && bInd<b.length) {
				table.put(new Point(aInd, bInd, b.length), val);
			}
			return val;
		}
	}

	//iterative solution - without memoization
	public static int getMaxRemovedItems4(int x) {

		int total = 0;
		int max = 0;
		int count = 0;

		for(int i=0; i<a.length && total<=x; i++) {
			total += a[i];
			int total1 = total;
			count = i+1;
			int j=0;
			for(; j<b.length && total1<=x; j++) {
				total1 += b[j];
				count++;
			}
			if(total1 >= x && (count-1) > max) {
				max = count-1;
			} else if (total1 < x && count > max) {
				max = count;
			}
		}

		total = 0;
		int count2 = 0;
		for(int i=0; i<b.length && total<=x; i++) {
			total += b[i];
			int total1 = total;
			count2 = i+1;
			int j=0;
			for(; j<a.length && total1<=x; j++) {
				total1 += a[j];
				count2++;
			}
			if(total1 >= x && (count2-1) > max) {
				max = count2-1;
			} else if (total1 < x && count2 > max) {
				max = count2;
			}
		}

		return max;
	}

	//iterative solution + with prefix sum - no memoization
	public static int getMaxRemovedItems6(int x) {

		int total = 0;
		int max = 0;

		int [] aPrefix = new int [a.length];
		int [] bPrefix = new int [b.length];

		for(int i=0; i<a.length; i++) {
			total += a[i];
			aPrefix[i] = total;
		}
		total = 0;
		for(int i=0; i<b.length; i++) {
			total += b[i];
			bPrefix[i] = total;
		}

		for(int i=0; i<a.length && aPrefix[i]<=x; i++) {
			int j=0;
			for(; j<b.length && (aPrefix[i]+bPrefix[j])<=x; j++);
			j = j > b.length-1 ? j-1 : j;
			if((aPrefix[i]+bPrefix[j]) > x && (i+j+1) > max) {
				max = i+j+1;
			} else if ((aPrefix[i]+bPrefix[j]) <= x && (i+j+2) > max) {
				max = i+j+2;
			}
		}

		for(int i=0; i<b.length && bPrefix[i]<=x; i++) {
			int j=0;
			for(; j<a.length && (bPrefix[i]+aPrefix[j])<=x; j++);
			j = j > a.length-1 ? j-1 : j;
			if((bPrefix[i]+aPrefix[j]) > x && (i+j+1) > max) {
				max = i+j+1;
			} else if ((bPrefix[i]+aPrefix[j]) <= x && (i+j+2) > max) {
				max = i+j+2;
			}
		}

		return max;
	}

	//iterative solution + with prefix sum + BST
	public static int getMaxRemovedItems7(int [] a, int [] b, int x) {

		int total = 0;
		int max = 0;

		ArrayList<Integer> aPrefix = new ArrayList<>(a.length);
		ArrayList<Integer> bPrefix = new ArrayList<>(b.length);

		for(int i=0; i<a.length && total <= x; i++) {
			total += a[i];
			aPrefix.add(total);
		}
		aPrefix.trimToSize();

		total = 0;
		for(int i=0; i<b.length && total <= x; i++) {
			total += b[i];
			bPrefix.add(total);
		}
		bPrefix.trimToSize();

		int i=0;
		while((i+1)<aPrefix.size() && aPrefix.get(i+1) == 0)
			i++;

		int jStart = 0;
		while((jStart+1)<bPrefix.size() && bPrefix.get(jStart+1) == 0)
			jStart++;

		for(; i<aPrefix.size() && aPrefix.get(i)<=x; i++) {
			int j = nearest(bPrefix, jStart, bPrefix.size()-1, x-aPrefix.get(i));
			if((aPrefix.get(i)+bPrefix.get(j)) > x && (i+j+1) > max) {
				max = i+j+1;
			} else if ((aPrefix.get(i)+bPrefix.get(j)) <= x && (i+j+2) > max) {
				max = i+j+2;
			}
		}

		if(max >= aPrefix.size() + bPrefix.size())
			return max;

		i=0;
		if((i+1)<bPrefix.size() && bPrefix.get(i+1) == 0)
			i++;

		jStart = 0;
		while((jStart+1)<aPrefix.size() && aPrefix.get(jStart+1) == 0)
			jStart++;

		for(; i<bPrefix.size() && bPrefix.get(i)<=x; i++) {
			int j = nearest(aPrefix, jStart, aPrefix.size()-1, x-bPrefix.get(i));
			if((bPrefix.get(i)+aPrefix.get(j)) > x && (i+j+1) > max) {
				max = i+j+1;
			} else if ((bPrefix.get(i)+aPrefix.get(j)) <= x && (i+j+2) > max) {
				max = i+j+2;
			}
		}

		return max;
	}

	private static int nearest(List<Integer> arr, int start, int end, int total) {
		if(start<end) {
			if(start == (end-1)) {
				return Math.abs(arr.get(start)-total) <= Math.abs(arr.get(end)-total) ? 
						start : end ;
			} else {
				int mid = start + (end-start)/2;
				if(arr.get(mid) == total) {
					while(mid+1 < arr.size() && arr.get(mid) == arr.get(mid+1))
						mid++;
					return mid;
				} else if (arr.get(mid) > total) {
					while(mid-1 >= 0 && arr.get(mid) == arr.get(mid-1))
						mid--;
					return nearest(arr, start, mid, total);
				} else {
					while(mid+1 < arr.size() && arr.get(mid) == arr.get(mid+1))
						mid++;
					return nearest(arr, mid, end, total);
				}
			}
		}
		return start;
	}	

	//greedy - does work on some cases only
	/*
        fails on following case
        1
        14 4 10
        0 0 0 0 10 0 0 0 0 0 0 0 0 0
        2 0 6 2
	 */
	public static int getMaxRemovedItems(int [] a, int [] b, int x) {
		//npe check
		int itemsRemoved = 0;
		long total = 0;
		int aInd = 0, bInd = 0;

		while(total <= x && aInd<a.length && bInd <b.length) {
			if(a[aInd] < b[bInd]) {
				total += a[aInd];
				aInd++;
			} else {
				total += b[bInd];
				bInd++;
			}
			itemsRemoved ++;
		}
		while(aInd<a.length && total<=x) {
			total += a[aInd];
			aInd ++;
			itemsRemoved++;
		}
		while(bInd<b.length && total<=x) {
			total += b[bInd];
			bInd ++;
			itemsRemoved++;
		}

		return total > x ? itemsRemoved-1 : itemsRemoved;
	}

	public static void main(String[] args) {
		//use fast scanner - https://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
		Scanner in = new Scanner(System.in);
		int g = in.nextInt();
		for(int a0 = 0; a0 < g; a0++){
			int n = in.nextInt();
			int m = in.nextInt();
			int x = in.nextInt();
			a = new int[n];
			for(int a_i=0; a_i < n; a_i++){
				a[a_i] = in.nextInt();
			}
			b = new int[m];
			for(int b_i=0; b_i < m; b_i++){
				b[b_i] = in.nextInt();
			}
			System.out.println(getMaxRemovedItems7(a, b, x));
		}
		System.out.println();
		in.close();
		System.out.println();
	}

}

/**
 Alexa has two stacks of non-negative integers, stack and stack where index denotes the top of the stack. Alexa challenges Nick to play the following game:

    In each move, Nick can remove one integer from the top of either stack or stack .
    Nick keeps a running sum of the integers he removes from the two stacks.
    Nick is disqualified from the game if, at any point, his running sum becomes greater than some integer given at the beginning of the game.
    Nick's final score is the total number of integers he has removed from the two stacks.

Given A, B, and x for g games, find the maximum possible score Nick can achieve (i.e., the maximum number of integers he can remove without being disqualified) during each game and print it on a new line.

Input Format

The first line contains an integer, (the number of games). The subsequent lines describe each game in the following format:

    The first line contains three space-separated integers describing the respective values of (the number of integers in stack ), (the number of integers in stack ), and (the number that the sum of the integers removed from the two stacks cannot exceed).
    The second line contains space-separated integers describing the respective values of .
    The third line contains space-separated integers describing the respective values of .

Constraints

Subtasks

    for of the maximum score.

Output Format

For each of the games, print an integer on a new line denoting the maximum possible score Nick can achieve without being disqualified.

Sample Input 0

1
5 4 10
4 2 4 6 1
2 1 8 5

Sample Output 0

4

Explanation 0

The two stacks initially look like this:

image

The image below depicts the integers Nick should choose to remove from the stacks. We print as our answer, because that is the maximum number of integers that can be removed from the two stacks without the sum exceeding .

image

(There can be multiple ways to remove the integers from the stack, the image shows just one of them.)
**/