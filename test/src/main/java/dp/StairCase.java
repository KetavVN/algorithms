package dp;

public class StairCase {

	public long getWays(int i, long [] arr) {
		long a = 0;
		if(i==0) {
			return 0;
		}
		if(arr[i-1] != 0) { 
			return arr[i-1];
		}
		if(i>=1) {
			a += getWays(i-1, arr)+1;
		} if (i>=2) {
			a += getWays(i-2, arr)+1;
		} if(i >= 3) {
			a += getWays(i-3, arr)+1;
		}
		arr[i-1] = a;
		return a;
	}
	
	public long getWays(int i) {
		long a = 0;
		if(i<=0) {
			return 0;
		}
		if(i >= 1) {
			a += getWays(i-1) + 1;
		} if (i >= 2) {
			a += getWays(i-2) + 1;
		} if(i >= 3) {
			a += getWays(i-3) + 1;
		}
		return a;
	}
	
	public static void main(String ...args) {
		StairCase obj = new StairCase();
		long [] arr = new long[40];
		System.out.println(obj.getWays(37, arr));
		System.out.println(obj.getWays(37)); //this one didn't come back after 2mins
		arr = new long[0];
		System.out.println(obj.getWays(0, arr));
		System.out.println(obj.getWays(0));
	}

} 
