package boj_1911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static class Water implements Comparable<Water>{
		int start;
		int end;
		
		public Water(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Water o) {
			return this.start - o.start;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		
		int N = Integer.parseInt(input[0]);
		int L = Integer.parseInt(input[1]);
		Water[] arr = new Water[N];
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			arr[i] = new Water(start, end - 1);
		}
		
		Arrays.sort(arr);
		int result = 0;
		
		int index = arr[0].start;
		
		for(int i = 0; i < N; i++) {
			Water now = arr[i];
			
			if(index < now.start) {
				index = now.start;
			}
			
			while(index <= now.end) {
				index += L;
				result++;
			}
			
			
		}
		
		/*
		for(int i = 0; i < N; i++) {
			int nextIdx = i + 1;
			
			Water now = arr[i];
			int start = now.start;
			int end = now.end;
			while(nextIdx < N && arr[nextIdx].start - end < L) {
				end = arr[nextIdx].end;
				
				nextIdx++;
				i++;
				if(nextIdx < N) {
					now = arr[nextIdx];
				}else {
					break;
				}
				
			}
			
			if((end - start + 1) % 3 == 0) {
				result += (end - start + 1) / 3;
			}else {
				result += (end - start + 1) / 3 + 1;
			}
			
		}
		
		*/
		System.out.println(result);
		
	}

}
