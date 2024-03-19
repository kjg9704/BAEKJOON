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
		
		Arrays.sort(arr);
		long min = Long.MAX_VALUE;
		int[] result = new int[3];
		
		for(int i = 0; i < N; i++) {
			long base = arr[i];
			
			int left = 0;
			int right = N - 1;
			
			while(left < right) {
				if(left == i) left++;
				if(right == i) right--;
				
				if(left == right || left > right) break;
				long now = base + arr[left] + arr[right];
				if(Math.abs(now) < min){
					min = Math.abs(now);
					result[0] = arr[i];
					result[1] = arr[left];
					result[2] = arr[right];
				}
				
				if(now == 0) {
					break;
				}else if(now > 0) {
					right--;
				}else {
					left++;
				}

			}
		}
		
		System.out.print(result[0] + " " + result[1] + " " + result[2]);
		
	}

}
