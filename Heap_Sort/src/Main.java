
public class Main {

	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 100);
		}

		print(arr);
		heapSort(arr);
		print(arr);

	}
	
	static void print(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	static void heapSort(int[] arr) {
		
		int start;
		if((arr.length -1) % 2 == 0) {
			start = (arr.length - 1) / 2 - 1;
		}else {
			start = (arr.length - 1) / 2;
		}
		
		for(int i = start; i >= 0; i--) {
			makeHeap(arr, i, arr.length - 1);
		}
		
		for(int i = arr.length - 1; i >= 0; i--) {
			swap(arr, i, 0);
			makeHeap(arr, 0, i - 1);
		}
		
	}
	
	static void makeHeap(int[] arr, int parent, int last) {
		int left = parent * 2 + 1;
		int right = parent * 2 + 2;
		int large = parent;
		
		if(left <= last && arr[left] > arr[large]) {
			large = left;
		}
		
		if(right <= last && arr[right] > arr[large]) {
			large = right;
		}
		
		if(large != parent) {
			swap(arr, large, parent);
			makeHeap(arr, large, last);
		}
		
	}
	
	static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

}
