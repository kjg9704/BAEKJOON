package boj_13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		String[] input = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		
		input = br.readLine().split(" ");
		int B = Integer.parseInt(input[0]);
		int C = Integer.parseInt(input[1]);

		long result = 0;
		
		for(int i = 0; i < N; i++) {
			arr[i] -= B;
			result++;
		}
		
		for(int i = 0; i < N; i++) {
			if(arr[i] > 0) {
				if(arr[i] % C == 0) {
					result += arr[i] / C;
				}else {
					result += (arr[i]/ C) + 1;
				}
			}
		}
		
		System.out.println(result);

	}

}
