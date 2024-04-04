import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	static int[][] matrix;
	static int[][] arr;
	static boolean flag = false;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		matrix = new int[N + 1][N + 1];
		arr = new int[4][20];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i + 1][j + 1] = Integer.parseInt(input[j]);
			}
		}
		
		for(int i = 2; i <= 3; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < 20; j++) {
				arr[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		boolean[] visited = new boolean[N + 1];
		dfs(visited, 0, 0, 0, 0, 0, 1, 2, 0);
		
		if(flag) {
			System.out.println("1");
		}else {
			System.out.println("0");
		}
	}
	
	static void dfs(boolean[] visited, int win, int win_gyeong, int win_minho, int gyeong, int minho, int winner, int next, int depth) {
		if(win == K) {
			if(win_gyeong < K && win_minho < K) flag = true;
			return;
		}
		
		if(flag) {
			return;
		}
		
		if(depth == 3 * (K - 1) + 1) {
			return;
		}
		if(winner == 1 || next == 1) {
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					visited[i] = true;
					if(next == 2) {
						int result = matrix[i][arr[next][gyeong]];
						if(result == 2) {
							dfs(visited, win + 1, win_gyeong, win_minho, gyeong + 1, minho, 1, 3, depth + 1);
						}else {
							dfs(visited, win,  win_gyeong + 1, win_minho, gyeong + 1, minho, 2, 3, depth + 1);
						}
					}else if(next == 3){
						int result = matrix[i][arr[next][minho]];
						if(result == 2) {
							dfs(visited, win + 1, win_gyeong, win_minho,  gyeong, minho + 1, 1, 2, depth + 1);
						}else {
							dfs(visited, win, win_gyeong, win_minho + 1,  gyeong, minho + 1, 3, 2, depth + 1);
						}
					}else {
						if(winner == 2) {
							int result = matrix[i][arr[winner][gyeong]];
							if(result == 2) {
								dfs(visited, win + 1, win_gyeong, win_minho,  gyeong + 1, minho, 1, 3, depth + 1);
							}else {
								dfs(visited, win, win_gyeong + 1, win_minho,  gyeong + 1, minho, 2, 3, depth + 1);
							}
						}else {
							int result = matrix[i][arr[winner][minho]];
							if(result == 2) {
								dfs(visited, win + 1, win_gyeong, win_minho,  gyeong, minho + 1, 1, 2, depth + 1);
							}else {
								dfs(visited, win, win_gyeong, win_minho + 1,  gyeong, minho + 1, 3, 2, depth + 1);
							}
						}
						
					}
					visited[i] = false;
				}
			}
		}else {
			if(winner == 2) {
				int result = matrix[arr[winner][gyeong]][arr[next][minho]];
				if(result == 2) {
					dfs(visited, win, win_gyeong + 1, win_minho,  gyeong + 1, minho + 1, 2, 1, depth + 1);
				}else {
					dfs(visited, win, win_gyeong, win_minho + 1,  gyeong + 1, minho + 1, 3, 1, depth + 1);
				}
			}else {
				int result = matrix[arr[winner][minho]][arr[next][gyeong]];
				if(result == 0) {
					dfs(visited, win, win_gyeong + 1, win_minho,  gyeong + 1, minho + 1, 2, 1, depth + 1);
				}else {
					dfs(visited, win, win_gyeong, win_minho + 1,  gyeong + 1, minho + 1, 3, 1, depth + 1);
				}
			}
		}
		
	}

}
