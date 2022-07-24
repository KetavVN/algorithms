package array;

/*
	best time n Log n
	avg time n Log n
	worst time = n Log n
	not inplace, stable
	faster caching performance due to contiguous arrays
*/
public class MergeSort {

	public void mergeSort(int [] arr) {
		if(arr == null || arr.length == 0) {
			return;
		}
		mergeSort(arr, new int[arr.length], 0, arr.length-1);
	}

	private void mergeSort(int [] arr, int [] temp, int startIndex, int endIndex) {
		if(startIndex < endIndex) {
			int midIndex = startIndex + (endIndex - startIndex)/2;
			mergeSort(arr, temp, startIndex, midIndex);
			mergeSort(arr, temp, midIndex+1, endIndex);
			merge(arr, temp, startIndex, midIndex, endIndex);
		}
	}

	private void merge(int [] arr, int [] temp, int startIndex, int midIndex, int endIndex) {
		int i = startIndex, j = midIndex+1;
		int k = startIndex;

		while(i<=midIndex && j<=endIndex)
			temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];

		while(i <= midIndex)
			temp[k++] = arr[i++];

		while(j <= endIndex)
			temp[k++] = arr[j++];

		for(i = startIndex; i <= endIndex; i++)
			arr[i] = temp[i];
	}

	public static void main(String ...args) {
		MergeSort obj = new MergeSort();
		int [] arr = new int [] {3,2,6,4,7,9,1,0,8,5};
		obj.mergeSort(arr);
		for(int a : arr)
			System.out.print(a+" ");
		System.out.println();
	}

}
