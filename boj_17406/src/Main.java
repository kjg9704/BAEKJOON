import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[][] matrix;
	static int[][] rotates;
	
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		matrix = new int[N + 1][M + 1];
		rotates = new int[K][3];
		
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= M; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			rotates[i][0] = Integer.parseInt(input[0]);
			rotates[i][1] = Integer.parseInt(input[1]);
			rotates[i][2] = Integer.parseInt(input[2]);
		}
		
		boolean[] visited = new boolean[K];
		int[] selected = new int[K];
		dfs(visited, selected, 0, 0);
		System.out.println(result);

	}
	
	static void dfs(boolean[] visited, int[] selected, int start, int depth) {
		if(depth == K) {
			int[][] temp = copy();
			for(int idx : selected) {
				rotate(temp, rotates[idx][0], rotates[idx][1], rotates[idx][2]);
			}
			
			int min = get_min_row(temp);
			result = Math.min(result, min);
			return;
		}
		for(int i = 0; i < K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				dfs(visited, selected, i, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int get_min_row(int[][] temp) {
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 1; j <= M; j++) {
				cnt += temp[i][j];
			}
			min = Math.min(min, cnt);
		}
		
		return min;
	}
	
	static int[][] rotate(int[][] start, int x, int y, int s){
		int size = 2;
		for(int i = 1; i <= s; i++) {
			int baseX = x - i;
			int baseY = y - i;
			int tmp = start[baseX][baseY];
			
			for(int j = 0; j < size; j++) {
				int now_x = baseX + j;
				start[now_x][baseY] = start[now_x + 1][baseY];
				
			}
			
			for(int j = 0; j < size; j++) {
				int now_x = baseX + size;
				int now_y = baseY + j;
				start[now_x][now_y] = start[now_x][now_y + 1];
			}
			
			for(int j = 0; j < size; j++) {
				int now_x = baseX + size;
				int now_y = baseY + size;
				start[now_x - j][now_y] = start[now_x - j - 1][now_y];
			}
			
			for(int j = 0; j < size; j++) {
				int now_y = baseY + size;
				if(j == size - 1) {
					start[baseX][now_y - j] = tmp;
				}else {
					start[baseX][now_y - j] = start[baseX][now_y - j - 1];
				}
			}
			
			size += 2;

		}
		
		return start;
	}
	
	static int[][] copy() {
		int[][] ret = new int[N + 1][M + 1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				ret[i][j] = matrix[i][j];
			}
		}
		
		return ret;
	}

}
