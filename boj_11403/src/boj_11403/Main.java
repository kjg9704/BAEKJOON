package boj_11403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static void print(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][N];
		int[][] answer = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j = 0; j < N; j++) {
				int edge = Integer.parseInt(input[j]);
				matrix[i][j] = edge;
			}
		}
		
		for(int i = 0; i < N; i++) {
			Stack<Integer> stack = new Stack<>();
			boolean[] visited = new boolean[N];
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == 1 && !visited[j]) {
					stack.push(j);
					while(!stack.isEmpty()) {
						int now = stack.pop();
						answer[i][now] = 1;
						visited[now] = true;
						for(int z = 0; z < N; z++) {
							if(matrix[now][z] == 1 && !visited[z]) {
								stack.push(z);
							}
						}
					}
				}
			}
		}
		
		print(answer);
		
	}

}
