package number;

public class MathPower {

	public int add(int a, int b) {

		if(a == 0) return b;
		if(b == 0) return a;

		int sum = a ^ b;
		int carry = (a & b)<<1;

		return add(sum, carry);

	}

	public int multiply(int x, int y) {

		int ans = 0;

		if(x == 0 || y == 0) return ans;

		int n = y<0 ? add(~y, 1) : y;

		for(int i=0; i<n; i++)
			ans = add(ans, x);

		return y<0 ? add(~ans, 1) : ans;

	}

	public int multiply2(int x, int y) {

		int ans = x;

		if(x == 0 || y == 0) return 0;

		int n = y<0 ? add(~y, 1) : y;
		
		while ((n > 1) /* || (n>>1 == 1 && n%2==1) */) {
			ans = ans<<1 ;
			n = n >> 1;
		}
		
		if (n==1) {
			ans = add (ans, x);
			n = n >> 1;
		}

		return y<0 ? add(~ans, 1) : ans;

	}
	
	public double pow(int x, int y) {

		int total = x;
		int pow = y < 0 ? add(~y, 1) : y;

		for(int i=1; i<pow; i++)
			total = multiply(total, x);

		return y<0 ? (1d/(double)total) : total;

	}

	public static void main(String [] args) {
		MathPower obj = new MathPower();
		//System.out.println((~-1)+1);
		//System.out.println(obj.add(15, -15));
		//System.out.println(obj.add(15, 15));
		System.out.println(obj.multiply(-15, 5));
		System.out.println(obj.multiply(15, -5));
		System.out.println(obj.multiply(-15, -4));
		System.out.println(obj.multiply(15, 15));
		//System.out.println(obj.pow(-2, -3));
		//System.out.println(obj.pow(-2, -4));
		//System.out.println(obj.pow(2, -4));
		//System.out.println(obj.pow(2, 4));
		//System.out.println(obj.pow(-2, 4));
	}

}
