package boj_16985;

import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y, z, cnt;
		
		public Point(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
		public Point(int x, int y, int z, int cnt) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.cnt = cnt;
		}
	}
	
	static int[][][] cube;
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, 1, 0, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	static int result = Integer.MAX_VALUE;
	static ArrayList<int[]> rotate_list;
	static int flag = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		cube = new int[5][5][5];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				String[] input = br.readLine().split(" ");
				for(int z = 0; z < 5; z++) {
					cube[i][j][z] = Integer.parseInt(input[z]);
				}
			}
		}
		
		
		rotate_list = new ArrayList<int[]>();
		int[] perm_list = new int[5];
		int[] rotate_arr = new int[5];
		
		rotate_perm(rotate_arr, 5, 0);
		
		boolean[] visited = new boolean[5];
		perm(perm_list, visited, 5, 0);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
		}
		
	}
	
	static int escape(int[][][] now_cube) {
		int min_count = Integer.MAX_VALUE;
		
		Queue<Point> que = new LinkedList<>();
		
		boolean[][][] visited = new boolean[5][5][5];
		
		que.add(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!que.isEmpty()) {
			Point now = que.poll();
			
			if(now.x == 4 && now.y == 4 && now.z == 4) {
				min_count = Math.min(min_count, now.cnt);
				break;
			}
			for(int i = 0; i < 6; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				int nextZ = now.z + dz[i];
				
				if(nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5 || nextZ < 0 || nextZ >= 5) {
					continue;
				}
				if(visited[nextZ][nextX][nextY]) {
					continue;
				}
				
				if(now_cube[nextZ][nextX][nextY] == 1) {
					que.add(new Point(nextX, nextY, nextZ, now.cnt + 1));
					visited[nextZ][nextX][nextY] = true;

				}
			}
		}
		
		return min_count;
	}
	static void perm(int[] perm_list, boolean[] visited, int num, int idx){
		if(num == 0) {
			int[][][] now_cube = new int[5][5][5];
			
			now_cube[0] = cube[perm_list[0]];
			now_cube[1] = cube[perm_list[1]];
			now_cube[2] = cube[perm_list[2]];
			now_cube[3] = cube[perm_list[3]];
			now_cube[4] = cube[perm_list[4]];
			
			sol(now_cube);
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(!visited[i]) {
				perm_list[idx] = i;
				visited[i] = true;
				perm(perm_list, visited, num - 1, idx + 1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate_perm(int[] rotate_arr, int num, int idx) {
		
		if(num == 0) {
			int[] add_list = {rotate_arr[0], rotate_arr[1], rotate_arr[2], rotate_arr[3], rotate_arr[4]};
			rotate_list.add(add_list);

			return;
		}
		
		for(int i = 0; i < 4; i++) {
			rotate_arr[idx] = i;
			rotate_perm(rotate_arr, num - 1, idx + 1);
		}
	}
	
	static void sol(int[][][] now_cube) {
		
		for(int[] rotate_arr : rotate_list) {
			
			if(rotate_arr[0] == 1) {
				//System.out.println();
			}
			int[][][] copy_arr = copy(now_cube);
			
			for(int i = 0; i < rotate_arr[0]; i++) {
				copy_arr[0] = rotate(copy_arr[0]);
			}
			
			for(int i = 0; i < rotate_arr[1]; i++) {
				copy_arr[1] = rotate(copy_arr[1]);
			}
			
			for(int i = 0; i < rotate_arr[2]; i++) {
				copy_arr[2] = rotate(copy_arr[2]);
			}
			
			for(int i = 0; i < rotate_arr[3]; i++) {
				copy_arr[3] = rotate(copy_arr[3]);
			}
			
			for(int i = 0; i < rotate_arr[4]; i++) {
				copy_arr[4] = rotate(copy_arr[4]);
			}
			
			if(copy_arr[0][0][0] == 1 && copy_arr[4][4][4] == 1) {
				int path = escape(copy_arr);

				if(path != Integer.MAX_VALUE) {
					result = Math.min(result, path);
				}
			}
			
			
		}
		
	}
	
	static int[][] rotate(int[][] matrix){
		int[][] new_matrix = new int[5][5];
		int N = 5;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				new_matrix[j][N - i - 1] = matrix[i][j]; 
			}
		}
		return new_matrix;
	}
	
	static int[][][] copy(int[][][] arg){
		int[][][] new_cube = new int[5][5][5];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int z = 0; z < 5; z++) {
					new_cube[i][j][z] = arg[i][j][z];
				}
			}
		}
		
		return new_cube;
	}
	
	static void print(int[][] matrix) {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
