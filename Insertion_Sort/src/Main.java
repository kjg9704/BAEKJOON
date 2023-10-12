
public class Main {

	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 100);
		}

		print(arr);
		InsertionSort(arr);
		print(arr);
	}
	
	static void InsertionSort(int[] arr) {
		
		for(int i = 1; i < arr.length; i++) {
			int key = arr[i];
			int j;
			for(j = i - 1; j >= 0; j--) {
				if(arr[j] > key) {
					arr[j + 1] = arr[j];
				}else if(arr[j] <= key) {
					break;
				}
			}
			arr[j + 1] = key;
		}
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
