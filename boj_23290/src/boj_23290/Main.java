package boj_23290;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	
	static class Fish{
		int x;
		int y;
		int dir;
		public Fish(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
	
	static int M, S;
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[][][] matrix;
	static int[][] fish_num;
	static int[][] smell;
	static int[][][] clone;
	static int[][][] move_matrix;
	
	static int SharkX;
	static int SharkY;
	static int[] shark_dx = {0, -1, 0, 1, 0};
	static int[] shark_dy = {0, 0, -1, 0, 1};
	
	static int stage;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		matrix = new int[5][5][8];
		
		smell = new int[5][5];
		
		
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		S = Integer.parseInt(input[1]);

		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int d = Integer.parseInt(input[2]) - 1;
			matrix[x][y][d]++;
		}
		input = br.readLine().split(" ");
		SharkX = Integer.parseInt(input[0]);
		SharkY = Integer.parseInt(input[1]);
		
		stage = 1;
		for(int loop = 0; loop < S; loop++) {
			System.out.println("------stage " + stage + "-------");
			clone = new int[5][5][8];
			move_matrix = new int[5][5][8];
			fish_num = new int[5][5];
			
			clone_fish();
			
			fish_move();
			print(move_matrix);
			
			shark_move();
			print(move_matrix);
			remove_smell();
			
			copy_fish();
			print(matrix);
			stage++;
			
		}
		
		int cnt = 0;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				for(int z = 0; z < 8; z++) {
					cnt += matrix[i][j][z];
				}
			}
		}
		
		System.out.println(cnt);
		
	}
	
	static void clone_fish() {
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				for(int z = 0; z < 8; z++) {
					if(matrix[i][j][z] > 0) {
						clone[i][j][z] = matrix[i][j][z];
					}
				}
			}
		}
	}
	
	static void remove_smell() {
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				if(smell[i][j] == stage - 2) {
					smell[i][j] = 0;
				}
			}
		}
	}
	
	static void copy_fish() {
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				for(int z = 0; z < 8; z++) {
					matrix[i][j][z] = move_matrix[i][j][z] + clone[i][j][z];
				}
			}
		}
	}
	
	static void fish_move() {
		
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				for(int z = 0; z < 8; z++) {
					if(matrix[i][j][z] == 0) {
						continue;
					}
					int dir = z;
					for(int k = 0; k < 8; k++) {
						int nextX = i + dx[dir];
						int nextY = j + dy[dir];
						if(fish_check(nextX, nextY)) {
							move_matrix[nextX][nextY][dir] += matrix[i][j][z];
							fish_num[nextX][nextY] += matrix[i][j][z];
							matrix[i][j][z] = 0;
							break;
						}else {
							if(k == 7) {
								move_matrix[i][j][z] += matrix[i][j][z];
								fish_num[i][j] += matrix[i][j][z];
								break;
							}
							dir = get_next_dir(dir);
						}
					}
					
				}
			}
		}
		
	}
	
	static boolean fish_check(int nextX, int nextY) {
		if(nextX > 4 || nextX < 1 || nextY > 4 || nextY < 1) {
			return false;
		}
		
		if(nextX == SharkX && nextY == SharkY) {
			return false;
		}
		
		if(smell[nextX][nextY] > 0) {
			return false;
		}
		return true;
	}
	
	static void shark_move() {
		
		int[] mov_arr = new int[3];
		
		int max = -1;
		for(int i = 1; i <= 4; i++) {
			boolean[][] visited = new boolean[5][5];
			int moveX_1 = SharkX + shark_dx[i];
			int moveY_1 = SharkY + shark_dy[i];
			int cnt_1 = 0;
			if(moveX_1 > 4 || moveX_1 < 1 || moveY_1 > 4 || moveY_1 < 1) {
				continue;
			}
			
			if(fish_num[moveX_1][moveY_1] > 0) {
				cnt_1 += fish_num[moveX_1][moveY_1];
				visited[moveX_1][moveY_1] = true;
			}
			
			for(int j = 1; j <= 4; j++) {
				int moveX_2 = moveX_1 + shark_dx[j];
				int moveY_2 = moveY_1 + shark_dy[j];
				int cnt_2 = cnt_1;
				if(moveX_2 > 4 || moveX_2 < 1 || moveY_2 > 4 || moveY_2 < 1) {
					continue;
				}
				if(fish_num[moveX_2][moveY_2] > 0 && !visited[moveX_2][moveY_2]) {
					cnt_2 += fish_num[moveX_2][moveY_2];
					visited[moveX_2][moveY_2] = true;
				}

	
				for(int z = 1; z <= 4; z++) {
					int moveX_3 = moveX_2 + shark_dx[z];
					int moveY_3 = moveY_2 + shark_dy[z];
					int cnt_3 = cnt_2;
					if(moveX_3 > 4 || moveX_3 < 1 || moveY_3 > 4 || moveY_3 < 1) {
						continue;
					}
					
					if(fish_num[moveX_3][moveY_3] > 0 && !visited[moveX_3][moveY_3]) {
						cnt_3 += fish_num[moveX_3][moveY_3];
					}
					
					if(max < cnt_3) {
						max = cnt_3;
						mov_arr[0] = i;
						mov_arr[1] = j;
						mov_arr[2] = z;
					}
					
				}
				visited[moveX_2][moveY_2] = false;
			}
		}
		
		int nextX = SharkX;
		int nextY = SharkY;
		for(int i = 0; i < 3; i++) {
			int dir = mov_arr[i];
			
			nextX = nextX + shark_dx[dir];
			nextY = nextY + shark_dy[dir];
			if(fish_num[nextX][nextY] > 0) {
				move_matrix[nextX][nextY] = new int[8];
				fish_num[nextX][nextY] = 0;
				smell[nextX][nextY] = stage;
			}
		}
		
		SharkX = nextX;
		SharkY = nextY;

	}
	
	static int get_next_dir(int dir) {
		if(dir == 0) {
			return 7;
		}else {
			return dir - 1;
		}
	}
	
	static void print(int[][][] matrix) {
		System.out.println("-----------");
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				int cnt = 0;
				for(int z = 0; z < 8; z++) {
					if(matrix[i][j][z] > 0) {
						cnt++;
					}
				}
				System.out.print(cnt + " ");
			}
			System.out.println();
		}
	}

}
