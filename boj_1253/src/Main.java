import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		int cnt = 0;
		
		Arrays.sort(arr);

		
		for(int i = 0; i < N; i++) {
			int target = arr[i];
			
			boolean flag = false;
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				if(flag) {
					break;
				}
				int find = target - arr[j];
				
				int start = 0;
				int end = N - 1;
				
				
				while(start <= end) {
					while(start == i || start == j) {
						start++;
					}
					while(end == i || end == j) {
						end--;
					}

					int mid = (start + end) / 2;

					if(arr[mid] > find) {
						end = mid - 1;
						
					}else if(arr[mid] < find) {
						start = mid + 1;
						
					}else {
						int tmp = mid;
						while(tmp == i || tmp == j) {
							tmp++;
						}
						if(arr[tmp] == find) {
							cnt++;
							flag = true;
							break;
						}
						tmp = mid;
						while(tmp == i || tmp == j) {
							tmp --;
						}
						if(arr[tmp] == find) {
							cnt++;
							flag = true;
						}
						break;
					}
				}
			}
			
		}
		
		
//투포인터		
/*
		
		
		
		
		
		for(int i = arr.length - 1; i >= 0; i--) {
			int target = arr[i];
			
			int left = 0;
			int right = arr.length - 1;
			
			while(true) {
				
				if(left == i) left++;
				if(right == i) right--;
				
				if(left == right || left > right) {
					break;
				}
				
				if(arr[left] + arr[right] > target) {
					right--;
				}else if(arr[left] + arr[right] < target) {
					left++;
				}else {
					cnt++;
					break;
				}
				
				
			}
		}
*/
		System.out.println(cnt);
	}

}
