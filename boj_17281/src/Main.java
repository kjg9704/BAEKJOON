import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] inning;
	static int max;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		inning = new int[N][9];
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			for(int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		boolean[] visited = new boolean[9];
		int[] selected = new int[9];
		visited[0] = true;
		dfs(visited, selected, 0);
		
		System.out.println(max);
	}
	
	static void dfs(boolean[] visited, int[] selected, int depth) {
		
		if(depth == 9) {

			int hitter = 0;
			int score = 0;
			
			for(int ing = 0; ing < N; ing++) {
				int[] runner = new int[4];
				int out = 0;
				while(out < 3) {
					if(hitter == 9) hitter = 0;
					int res = inning[ing][selected[hitter++]];
					
					if(res == 0) {
						out++;
					}else {
						for(int i = 2; i >= 0; i--) {
							if(runner[i] == 1) {
								int next = i + res;
								if(next >= 3) {
									runner[3] += 1;
								}else {
									runner[next] = 1;
								}
								runner[i] = 0;
							}
						}
						
						runner[res - 1] += 1;
						score += runner[3];
						runner[3] = 0;
					}
				}
			}
			max = Math.max(max, score);
			
			return;
		}
		
		if(depth == 3) {
			selected[depth] = 0;
			dfs(visited, selected, depth + 1);
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				dfs(visited, selected, depth + 1);
				visited[i] = false;
			}
		}
	}

}
