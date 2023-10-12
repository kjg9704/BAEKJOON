package boj_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int N;
	static int[] operators;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		operators = new int[4];
		String[] temp = br.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(temp[i]);
				
		}
		
		temp = br.readLine().split(" ");
		for(int i = 0; i < 4; i++) {
			int num = Integer.parseInt(temp[i]);
			operators[i] = num;
		}
		
		solution(nums, 1, nums[0]);
		System.out.println(max);
		System.out.println(min);
		
	}
	
	public static void solution(int[] nums, int start, int now) {
		if(start == N) {
			max = Math.max(max, now);
			min = Math.min(min, now);
			return;
		}
		for(int i = 0; i < 4; i++) {
			if(operators[i] > 0) {
				operators[i]--;
				switch(i) {
				case 0:
					solution(nums, start + 1, now + nums[start]);
					break;
				case 1:
					solution(nums, start + 1, now - nums[start]);
					break;
				case 2:
					solution(nums, start + 1, now * nums[start]);
					break;
				case 3:
					solution(nums, start + 1, now / nums[start]);
					break;
				}
				
				operators[i]++;
				
			}

		}
	}

}
