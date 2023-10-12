
public class Main {

	public static void main(String[] args) {
		int[] arr = new int[10];
		int[] result = new int[10];
		int[] count_arr = new int[101];
		
		for(int i = 0; i < 10; i++) {
			int num = (int) (Math.random() * 100);
			arr[i] = num;
		}

		//int[] arr = {16, 37, 88, 98, 25, 76, 44, 63, 92, 8};

		
		
		print(arr);
		
		for(int i = 0; i < 10; i++) {
			count_arr[arr[i]]++;
		}
		
		for(int i = 1; i < count_arr.length; i++) {
			count_arr[i] += count_arr[i - 1];
		}
		
		for(int i = arr.length - 1; i >= 0; i--) {
			int num = arr[i];
			count_arr[num]--;
			result[count_arr[num]] = num;
		}
		
		print(result);
	}
	
	public static void Counting_Sort(int[] arr) {
		
	}
	static void print(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
