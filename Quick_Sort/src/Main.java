
public class Main {

	public static void main(String[] args) {
		
		int[] arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		
		//int[] arr = {16, 37, 88, 98, 25, 76, 44, 63, 92, 8};
		print(arr);
		
		QuickSort(arr, 0, arr.length - 1);
		
		print(arr);
		
	}
	
	static void QuickSort(int[] arr, int left, int right) {
		int pos = partition(arr, left, right);
		print(arr);
		if(left < pos - 1) {
			QuickSort(arr, left, pos - 1);
			
		}
		if(pos < right) {
			QuickSort(arr, pos, right);
		}
	}
	
	static int partition(int[] arr, int left, int right) {
		int pivot = arr[(left + right) / 2];
		
		while(left < right) {
			while(arr[left] < pivot) {
				left++;
			}
			while(arr[right] > pivot) {
				right--;
			}
			
			if(left <= right) {
				swap(arr, left, right);
				right--;
				left++;
			}
		}
		
		return left;
		
		
	}
	
	static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}
	
	static void print(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
