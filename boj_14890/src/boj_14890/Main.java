package boj_14890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int L = Integer.parseInt(input[1]);
		int[][] matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		int result = 0;
		for(int i = 0; i < N; i++) {
			int now = 0;
			boolean[] ways = new boolean[N];
			while(now < N) {
				if(now == N - 1) {
					System.out.println(i + " " + now);
					result++;
					break;
				}
				
				int next = matrix[i][now + 1];
				if(Math.abs(matrix[i][now] - next) > 1) {
					break;
				}else if(matrix[i][now] == next) {
					now = now + 1;
				}else if(matrix[i][now] > next){
					boolean check = false;
					for(int j = 1; j <= L; j++) {
						if(now + j >= N) {
							break;
						}
						if(matrix[i][now + j] != next) {
							break;
						}
						if(ways[now + j]) {
							break;
						}
						
						if(j == L) {
							check = true;
						}
					}
					
					if(check) {
						for(int j = 1; j <= L; j++) {
							ways[now + j] = true;
						}
						now += L;
					}else {
						break;
					}
				}else {
					if(now >= L - 1) {
						boolean way_check = false;
						for(int j = 0; j < L; j++) {
							if(ways[now - j]) {
								way_check = true;
							}
						}
						if(!way_check) {
							for(int j = 0; j < L; j++) {
								ways[now - j] = true;
							}
							now += 1;
						}else {
							break;
						}
						
					}else {
						break;
					}
				}
//				System.out.println(i + " " + now);
			}
		}
		System.out.println(result);
		
		for(int i = 0; i < N; i++) {
			int now = 0;
			boolean[] ways = new boolean[N];
			while(now < N) {
				if(now == N - 1) {
					System.out.println(now + " " + i);
					result++;
					break;
				}
				
				int next = matrix[now + 1][i];
				if(Math.abs(matrix[now][i] - next) > 1) {
					break;
				}else if(matrix[now][i] == next) {
					now = now + 1;
				}else if(matrix[now][i] > next){
					boolean check = false;
					for(int j = 1; j <= L; j++) {
						if(now + j >= N) {
							break;
						}
						if(matrix[now + j][i] != next) {
							break;
						}
						if(ways[now + j]) {
							break;
						}
						
						if(j == L) {
							check = true;
						}
					}
					
					if(check) {
						for(int j = 1; j <= L; j++) {
							ways[now + j] = true;
						}
						now += L;
					}else {
						break;
					}
				}else {
					if(now >= L - 1) {
						boolean way_check = false;
						for(int j = 0; j < L; j++) {
							if(ways[now - j]) {
								way_check = true;
								break;
							}
						}
						if(!way_check) {
							for(int j = 0; j < L; j++) {
								ways[now - j] = true;
							}
							now += 1;
						}else {
							break;
						}
					}else {
						break;
					}
				}
//				System.out.println(i + " " + now);

			}
		}
		
		System.out.println(result);
	}
	

}
