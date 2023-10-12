
public class Main {

	public static void main(String[] args) {
		int[] arr = new int[10];
		for(int i = 0; i < 10; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		
		print(arr);
		
		Merge_Sort(arr, 0 , arr.length - 1);
		
		print(arr);
	}
	
	static void Merge_Sort(int[] arr, int left, int right) {
		int pos = (left + right) / 2;
		
		if(right - left >= 1) {
			Merge_Sort(arr, left, pos);
//			print(arr);
			Merge_Sort(arr, pos + 1, right);
//			print(arr);

			int right_start = pos + 1;
			int left_start = left;
			int index = 0;
			

			int[] temp = new int[right - left + 1];
			while(left_start <= pos && right_start <= right) {
				
				if(arr[left_start] > arr[right_start]) {
					temp[index] = arr[right_start];
					right_start++;
					index++;
				}else {
					temp[index] = arr[left_start];
					left_start++;
					index++;
				}
				
			}
			
			while(left_start <= pos) {
				temp[index] = arr[left_start];
				left_start++;
				index++;
			}
			
			while(right_start <= right) {
				temp[index] = arr[right_start];
				right_start++;
				index++;
			}
			
			for(int i = 0; i < temp.length; i++) {
				arr[left + i] = temp[i];
			}

			
		}
		
		print(arr);
		
	}
	
	static void print(int[] arr) {
		for(int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
