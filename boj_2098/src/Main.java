import java.io.*;
import java.util.*;

public class Main {

	static int[][] matrix;
	static int min = Integer.MAX_VALUE;
	static int N;
	static int[][] dp;
	static int INF = 100000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		matrix = new int[N][N];
		dp = new int[1 << N][N];
		
		for(int i = 0; i < (1 << N); i++) {
			for(int j = 0; j < N; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		dfs(0, 1);
		System.out.println(dfs(0, 1));
		
	}
	
	static int dfs(int now, int visited_bit) {
		
		if(visited_bit == (1 << N) - 1) {
			if(matrix[now][0] > 0) {
				return matrix[now][0];
			}else {
				return INF;
			}
		}
		
		if(dp[visited_bit][now] != Integer.MAX_VALUE) {
			return dp[visited_bit][now];
		}
		int ret = INF;
		
		for(int i = 0; i < N; i++) {
			if(matrix[now][i] > 0) {
				int next_bit = 1 << i;
				if((visited_bit & next_bit) == 0) {
					int new_cost = matrix[now][i] + dfs(i, visited_bit | next_bit);
					ret = Math.min(ret, new_cost);
				}
			}
		}
		
		dp[visited_bit][now] = ret;
		return ret;
	}

}
