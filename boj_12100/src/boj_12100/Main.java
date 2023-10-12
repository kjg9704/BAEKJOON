package boj_12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int[][] matrix;
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		matrix = new int[N][N];

		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		dfs(matrix, 0);
		System.out.println(max);
	}
	
	static void dfs(int[][] matrix, int depth) {
		if(depth == 5) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(matrix[i][j] > max) {
						max = matrix[i][j];
					}
				}
			}
			return;
		}

		int[][] copy = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copy[i][j] = matrix[i][j];
			}
		}
		
		for(int z = 0; z < 4; z++) {
			switch(z) {
			case 0: //µ¿
				for(int i = 0; i < N; i++) {
					boolean[] changed = new boolean[N];
					for(int j = N - 1; j >= 0; j--) {
						if(matrix[i][j] != 0) {
							int index = j + 1;
							while(index < N) {
								if(matrix[i][index] == 0) {
									index++;
								}else {
									break;
								}
							}
							int tmp = matrix[i][j];
							matrix[i][j] = 0;
							if(index == N) {
								matrix[i][index - 1] = tmp;
							}else if(matrix[i][index] == tmp && !changed[index]){
								matrix[i][index] = matrix[i][index] * 2;
								changed[index] = true;
							}else {
								matrix[i][index - 1] = tmp;
							}
						}
						
					}
				}
				break;
			case 1: //³²
				for(int i = 0; i < N; i++) {
					boolean[] changed = new boolean[N];
					for(int j = N - 1; j >= 0; j--) {
						if(matrix[j][i] != 0) {
							int index = j + 1;
							while(index < N) {
								if(matrix[index][i] == 0) {
									index++;
								}else {
									break;
								}
							}
							int tmp = matrix[j][i];
							matrix[j][i] = 0;
							if(index == N) {
								matrix[index - 1][i] = tmp;
							}else if(matrix[index][i] == tmp && !changed[index]){
								matrix[index][i] = matrix[index][i] * 2;
								changed[index] = true;
							}else {
								matrix[index - 1][i] = tmp;
							}

						}
						
					}
				}
				break;
			case 2: //¼­
				for(int i = 0; i < N; i++) {
					boolean[] changed = new boolean[N];
					for(int j = 0; j < N; j++) {
						if(matrix[i][j] != 0) {
							int index = j - 1;
							while(index >= 0) {
								if(matrix[i][index] == 0) {
									index--;
								}else {
									break;
								}
							}
							int tmp = matrix[i][j];
							matrix[i][j] = 0;
							if(index == -1) {
								matrix[i][index + 1] = tmp;
							}else if(matrix[i][index] == tmp && !changed[index]){
								matrix[i][index] = matrix[i][index] * 2;
								changed[index] = true;
							}else {
								matrix[i][index + 1] = tmp;
							}
							
						}
						
					}
				}
				break;
			case 3: //ºÏ
				for(int i = 0; i < N; i++) {
					boolean[] changed = new boolean[N];
					for(int j = 0; j < N; j++) {
						if(matrix[j][i] != 0) {
							int index = j - 1;
							while(index >= 0) {
								if(matrix[index][i] == 0) {
									index--;
								}else {
									break;
								}
							}
							
							int tmp = matrix[j][i];
							matrix[j][i] = 0;
							if(index == -1) {
								matrix[index + 1][i] = tmp;
							}else if(matrix[index][i] == tmp && !changed[index]){
								matrix[index][i] = matrix[index][i] * 2;
								changed[index] = true;
							}else {
								matrix[index + 1][i] = tmp;
							}
							
						}
						
					}
				}
				break;
			}
			print(matrix, depth, z);
			dfs(matrix, depth + 1);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					matrix[i][j] = copy[i][j];
				}
			}
		}
		
		
		
	}
	
	static void print(int[][] matrix, int depth, int dir) {
		System.out.println("------" + depth + " // "+ dir + "---------");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	

}
