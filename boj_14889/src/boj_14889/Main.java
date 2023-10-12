package boj_14889;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] stats = new int[N + 1][N + 1];
		
		
		boolean[] visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			String[] temp = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				stats[i][j] = Integer.parseInt(temp[j - 1]);
			}
		}
		solution(stats, visited,  1, 0);
		
		System.out.println(result);
		
	}
	
	public static void solution(int[][] stats, boolean[] visited, int start, int depth) {
		if(depth == N / 2) {
			int star = 0;
			int link = 0;
			for(int i = 1; i <= N; i++) {
				if(visited[i]) {
					for(int j = i + 1; j <= N; j++) {
						if(visited[j]) {
							star += stats[i][j];
							star += stats[j][i];
						}
					}
				}
			}
			
			for(int i = 1; i <= N; i++) {
				if(!visited[i]) {
					for(int j = i + 1; j <= N; j++) {
						if(!visited[j]) {
							link += stats[i][j];
							link += stats[j][i];
						}
					}
				}
			}
			
			result = Math.min(result, Math.abs(star - link));
			
			return;
		}
		
		for(int i = start; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				solution(stats, visited, i + 1, depth + 1);
				visited[i] = false;
			}
			
		}
	}

}
