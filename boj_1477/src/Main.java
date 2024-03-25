import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int L = Integer.parseInt(input[2]);
		
		input = br.readLine().split(" ");
		
		int[] arr = new int[N + 2];
		
		arr[0] = 0;
		arr[N + 1] = L;
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(input[i - 1]);
		}
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = L;
		
		int mid = 0;
		while(left <= right) {
			
			mid = (left + right) / 2;
			
			int cnt = 0;
			for(int i = 1; i <= N + 1; i++) {
				int dist = arr[i] - arr[i - 1] - 1;
				cnt += dist / mid;
			}
			
			if(cnt > M) {
				left = mid + 1;
			}else {
				right = mid - 1;
			}
			
			
		}
		
		System.out.println(left);
		
	}

}
