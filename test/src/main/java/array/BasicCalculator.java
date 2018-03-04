package array;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BasicCalculator {

	public static String arithmeticExpressions(int[] arr) {
		
        Stack<Character> opStack = new Stack<>();
        Queue<Character> opQueue = new LinkedList<>();
        BigInteger sum = new BigInteger(Long.toString(arr[0]));
        char [] operations = {'+', '-', '*'};
        boolean isDivisible = false;
        
        int m=10;
        while(((arr.length-1)%m)!=0 && m>1)
        	m--;
        int i;
        if(m>1) i = (arr.length-1)/m;
        else i = arr.length/2;
        
        int l=0;
        
        for(; i<arr.length && !isDivisible; i+=(l=(i+m < arr.length)?m:1)) {
        	isDivisible = findEquation(arr, i, operations, 1, sum, opStack, opQueue);
        }
        
        for(int j=arr.length-1; j>=(i-l); j--) {
			opQueue.add('*');
		}
        
        if(isDivisible) {
            StringBuilder ans = new StringBuilder().append(arr[0]);
            for(int k=1; k<arr.length; k++) {
            	if(!opStack.isEmpty()) {
            		ans.append(opStack.pop()).append(arr[k]);
            	} else {
            		ans.append(opQueue.poll()).append(arr[k]);
            	}
            }
            return ans.toString();
        }
        return null;
    }
	
	private static boolean findEquation(int [] arr, int size, char [] operations, int currentIndex, 
			BigInteger currentSum, Stack<Character> opStack, Queue<Character> opQueue) {
		if(currentIndex == (size+1)) {
			return currentSum.remainder(new BigInteger(Long.toString(101))).intValue() == 0;
		} else if (currentSum.remainder(new BigInteger(Long.toString(101))).intValue() == 0) {
			for(int i=size; i>=currentIndex; i--) {
				opQueue.add('*');
			}
			return true;
		} else {
			for(int i=0; i<operations.length; i++) {
				BigInteger newSum = new BigInteger(currentSum.toString());
				newSum = doOperation(operations[i], newSum, arr[currentIndex]);
				boolean isDivisible = findEquation(arr, size, operations, currentIndex+1, newSum, opStack, opQueue);
				if(isDivisible) {
					opStack.push(operations[i]);
					return isDivisible;
				}
			}
		}
		return false;
	}
	
	private static BigInteger doOperation(char operation, BigInteger op1, long op2) {
		if(operation == '+') {
			return op1.add(new BigInteger(Long.toString(op2)));
		} else if (operation == '-') {
			return op1.subtract(new BigInteger(Long.toString(op2)));
		} else {
			return op1.multiply(new BigInteger(Long.toString(op2)));
		}
	}
	
	public static void main(String args[]) {
		int [] arr = {22, 79, 21};//{100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
		String output = arithmeticExpressions(arr);
		System.out.println(output);
	}
	
}
/**
5-year-old Shinchan had just started learning mathematics. Meanwhile, one of his studious classmates, Kazama, had already written a basic calculator which supports only three operations on integers: multiplication , addition , and subtraction . Since he had just learned about these operations, he didn't know about operator precedence, and so, in his calculator, all operators had the same precedence and were left-associative.

As always, Shinchan started to irritate him with his silly questions. He gave Kazama a list of integers and asked him to insert one of the above operators between each pair of consecutive integers such that the result obtained after feeding the resulting expression in Kazama's calculator is divisible by . At his core, Shinchan is actually a good guy, so he only gave lists of integers for which an answer exists.

Can you help Kazama create the required expression? If multiple solutions exist, print any one of them.

Input Format

The first line contains a single integer denoting the number of elements in the list. The second line contains space-separated integers dneoting the elements of the list.

Constraints

    The length of the output expression should not exceed .

Output Format

Print a single line containing the required expressoin. You may insert spaces between operators and operands.

Note

    You are not allowed to permute the list.
    All operators have the same precedence and are left-associative, e.g., is interpreted as
    Unary plus and minus are not supported, e.g., statements like , , or are invalid.

Sample Input 0

3
22 79 21

Sample Output 0

22*79-21

Explanation 0

Solution 1: , where , so it is perfectly divisible by .
Solution 2: , which is also divisible by .

Sample Input 1

5
55 3 45 33 25

Sample Output 1

55+3-45*33-25

Explanation 1

which is divisible by 101.
 */
