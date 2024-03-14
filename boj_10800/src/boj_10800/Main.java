package boj_10800;

import java.io.*;
import java.util.*;

public class Main {

	static class Ball implements Comparable<Ball>{
		int num, color, size;
		
		public Ball(int num, int color, int size) {
			this.num = num;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size - o.size;

		}
		
	}
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		Ball[] balls = new Ball[N];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			int color = Integer.parseInt(input[0]);
			int size = Integer.parseInt(input[1]);
			
			balls[i] = new Ball(i + 1, color, size);
		}
		
		Arrays.sort(balls);
		
		int sum = 0;
		
		int[] score = new int[N + 1];
		int[] color_sum = new int[N + 1];

		int size_idx = 0;
		for(int i = 0; i < N; i++) {
			int color = balls[i].color;
			int size = balls[i].size;
			int num = balls[i].num;
			while(balls[size_idx].size < size) {
				sum += balls[size_idx].size;
				color_sum[balls[size_idx].color] += balls[size_idx].size;
				size_idx++;
			}
			int now_sum = sum - color_sum[color];
			score[num] = now_sum;
		}
		
		for(int i = 1; i <= N; i++) {
			int num = score[i];
			sb.append(num);
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
