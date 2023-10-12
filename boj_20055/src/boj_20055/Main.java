package boj_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[] durable = new int[N * 2];
		boolean[] robots = new boolean[N];
		int stage = 0;
		int count = 0;
		input = br.readLine().split(" ");
		
		for(int i = 0; i < N * 2; i++) {
			int dur = Integer.parseInt(input[i]);
			durable[i] = dur;
			if(dur == 0) {
				count++;
			}
		}
		
		while(true) {
			stage++;
			int temp = durable[N * 2 - 1];
			for(int i = N * 2 - 1; i > 0; i--) {
				if(i < N && robots[i - 1]) {
					robots[i] = true;
					robots[i - 1] = false;
				}
				durable[i] = durable[i - 1];
			}
			durable[0] = temp;
			robots[N - 1] = false;
			
			for(int i = N - 1; i > 0; i--) {
				if(robots[i - 1] && !robots[i] && durable[i] > 0) {
					robots[i] = true;
					robots[i - 1] = false;
					durable[i]--;
					if(durable[i] == 0) {
						count++;
					}
				}
			}
			robots[N - 1] = false;
			if(durable[0] > 0 && !robots[0]) {
				robots[0] = true;
				durable[0]--;
				if(durable[0] == 0) {
					count++;
				}
			}
			
			if(count >= K) {
				break;
			}
			
		}
		
		System.out.println(stage);
		
		
		
		
	}

}
