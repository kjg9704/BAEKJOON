package boj_11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int INF = 214000000;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(matrix[i], INF);
		}
		for(int i = 1; i <= N; i++) {
			matrix[i][i] = 0;
		}
		
		for(int i = 0; i < M; i++) {
			String[] temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int cost = Integer.parseInt(temp[2]);
			if(cost < matrix[start][end]) {
				matrix[start][end] = cost;
			}
		}
		print(matrix);

		System.out.println();
		for(int i = 1; i <= N; i++) {
			//경유
			for(int j = 1; j <= N; j++) {
				//출발
				for(int z = 1; z <= N; z++) {
					//도착
					if(matrix[j][z] > matrix[j][i] + matrix[i][z]) {
						matrix[j][z] = matrix[j][i] + matrix[i][z];
					}
				}
			}
		}
		print(matrix);
	}
	
	static void print(int[][] matrix) {
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[i].length; j++) {
				if(matrix[i][j] == INF) {
					System.out.print("INF ");
				}else {
					System.out.print(matrix[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

}
