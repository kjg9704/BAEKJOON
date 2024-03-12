package boj_17182;

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	
	static int min = Integer.MAX_VALUE;
	static int[][] dist;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		

		dist = new int[N][N];
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(input[j]);

			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int z = 0; z < N; z++) {
					dist[j][z] = Math.min(dist[j][z], dist[j][i] + dist[i][z]);
				}
			}
		}
		
		boolean[] visited = new boolean[N];
		visited[K] = true;
		dfs(visited, K, 1, 0);
		
		System.out.println(min);
		
	}
	
	static void dfs(boolean[] visited, int now, int cnt, int sum) {
		if(cnt == N) {
			min = Math.min(min, sum);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(i == now) continue;
			if(visited[i]) continue;
			
			visited[i] = true;
			dfs(visited, i, cnt + 1, sum + dist[now][i]);
			visited[i] = false;
			
		}
		
	}

}
