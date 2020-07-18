package bitwise;

public class FlipNumber {

	public static void main(String ... args) {
		
		System.out.println(String.format("~0 = %s", Integer.toBinaryString(~0)));
		System.out.println(String.format("~0-1 = %s", Integer.toBinaryString(~0-1)));
		System.out.println(String.format("~0+1 = %s", Integer.toBinaryString(~0+1)));
		System.out.println(String.format("1<<31 = %d = %s", 1<<31, Integer.toBinaryString(1<<31)));
		System.out.println(String.format("1<<31 = %d = %s", (1<<31)-1, Integer.toBinaryString((1<<31)-1)));
		System.out.println(String.format("1<<31 = %d = %s", -1<<31, Integer.toBinaryString(-1<<31)));
		System.out.println(String.format("1<<32 = %s", Integer.toBinaryString(1<<32)));
		System.out.println(String.format("~-1 = %s", Integer.toBinaryString(~-1)));
		System.out.println(String.format("-~1 = %s", Integer.toBinaryString(-~1)));
		
		/*int k = 15;
		
		System.out.println(String.format("k ^   k = %d", k ^  k));
		System.out.println(String.format("k ^  -k = %d", k ^ -k));
		System.out.println(String.format("k ^  ~k = %d", k ^ ~k));
		System.out.println(String.format("k ^ ~-k = %d", k ^~-k));
		System.out.println(String.format("k ^ -~k = %d", k ^-~k));
		System.out.println(String.format("k ^   0 = %d", k ^  0));
		System.out.println(String.format("k ^  ~0 = %d", k ^ ~0));
		System.out.println(String.format("k ^   1 = %d", k ^  1));
		System.out.println(String.format("k ^  -1 = %d", k ^ -1));
		
		System.out.println(String.format("k &  ~k = %d", k & ~k));
		System.out.println(String.format("k &   k = %d", k &  k));
		System.out.println(String.format("k |  ~k = %d", k | ~k));
		System.out.println(String.format("k |   k = %d", k |  k));*/
	}
	
}
