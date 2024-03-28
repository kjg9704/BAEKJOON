import java.io.*;
import java.util.*;

public class Main {

	static int result;
	static int N;
	static int M;
	static int[][] matrix;
	static int[] parent;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		matrix = new int[N][M];
		parent = new int[N * M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				char dir = str.charAt(j);
				switch(dir) {
				case 'U':
					matrix[i][j] = 0;
					break;
				case 'D':
					matrix[i][j] = 1;
					break;
				case 'L':
					matrix[i][j] = 2;
					break;
				case 'R':
					matrix[i][j] = 3;
					break;
				}
			}
		}
		for(int i = 0; i < N * M; i++) {
			parent[i] = -1;
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j]) {
					int idx = get_arr_idx(i, j);
					parent[idx] = idx;
					visited[i][j] = true;
					dfs(i, j);
				}
			}
		}
		
		HashMap<Integer, Integer> map = new HashMap<>();
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int idx = get_arr_idx(i, j);
				int root = find_root(idx);
				if(!map.containsKey(root)) {
					map.put(root, 0);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
	}
	
	static void dfs(int startX, int startY) {
		int dir = matrix[startX][startY];
		
		int nextX = startX + dx[dir];
		int nextY = startY + dy[dir];
		
		if(visited[nextX][nextY]) {
			int idx1 = get_arr_idx(startX, startY);
			int idx2 = get_arr_idx(nextX, nextY);
			union(idx1, idx2);
			return;
		}
		visited[nextX][nextY] = true;
		
		int root = find_root(get_arr_idx(startX, startY));
		parent[get_arr_idx(nextX, nextY)] = root;
		dfs(nextX, nextY);
	}
	
	static void union(int idx1, int idx2) {
		idx1 = find_root(idx1);
		idx2 = find_root(idx2);
		
		if(idx1 != idx2) {
			parent[idx1] = idx2;
		}
	}
	
	static int find_root(int idx) {
		if(parent[idx] == idx) {
			return idx;
		}else {
			parent[idx] = find_root(parent[idx]);
			return parent[idx];
		}
	}
	
	static int get_arr_idx(int x, int y) {
		return x * M + y;
	}

}
