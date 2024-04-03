import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] matrix;
	static boolean[] right_up;
	static boolean[] left_up;
	static int[] dx = {-1, 1, 1, -1};
	static int[] dy = {1, 1, -1, -1};
	
	static int result = 0;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		right_up = new boolean[2 * N - 1];
		left_up = new boolean[2 * N - 1];
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		dfs(0, 0);
		
		System.out.println(result);
	}
	
	static void dfs(int index, int depth) {
		if(index == 2 * N - 1) {
			result = Math.max(result, depth);
			return;
		}
		
		boolean flag = false;
		for(int i = 0; i < N - Math.abs(index - (N - 1)); i++) {
			int x = 0;
			int y = 0;
			
			if(N - 1 <= index) {
				x = N - 1 - i;
				y = index - (N - 1) + i;
			}else {
				x = index - i;
				y = i;
			}
			int right_idx = x + y;
			int left_idx = x - y + N - 1;
			if(matrix[x][y] == 1 && !left_up[left_idx] && !right_up[right_idx]) {
				flag = true;
				left_up[left_idx] = true;
				right_up[right_idx] = true;
				dfs(index + 1, depth + 1);
				right_up[right_idx] = false;
				left_up[left_idx] = false;
			}
		}
		if(!flag) {
			dfs(index + 1, depth);
		}
	}
	

}