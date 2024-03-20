import java.io.*;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		
		long min = Long.MAX_VALUE;
		int[] result = new int[2];
		String[] input = br.readLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = N - 1;
		
		while(left < right) {
			long sum = arr[left] + arr[right];
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				result[0] = arr[left];
				result[1] = arr[right];
			}
			
			if(sum == 0) {
				break;
			}
			if(sum > 0) {
				right--;
			}else if(sum < 0) {
				left++;
			}
		}
		
		System.out.println(result[0] + " " + result[1]);
		
	}

}
