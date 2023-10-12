package boj_17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class Shark{
		int num;
		int r;
		int c;
		int s;
		int d;
		int z;
		
		public Shark(int num, int r, int c, int s, int d, int z) {
			this.num = num;
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	static int R, C, M;
	static int[][] matrix;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static Shark[] sharks;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		M = Integer.parseInt(input[2]);

		matrix = new int[R + 1][C + 1];
		sharks = new Shark[M + 1];
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			int d = Integer.parseInt(input[3]) - 1;
			int z = Integer.parseInt(input[4]);
			
			sharks[i + 1] = new Shark(i + 1, r, c, s, d, z);
			matrix[r][c] = i + 1;
		}
		
		int fisher = 0;
		int result = 0;
		
		while(fisher < C) {
			fisher++;
			int row = 1;
			while(row <= R) {
				if(matrix[row][fisher] > 0) {
					int shark_num = matrix[row][fisher];
					result += sharks[shark_num].z;
					sharks[shark_num] = null;
					matrix[row][fisher] = 0;
					break;
				}else {
					row++;
				}
			}
			
			move();
			
		}
		
		System.out.println(result);
	}
	
	static void move() {
		for(int i = 1; i <= M; i++) {
			if(sharks[i] == null) continue;
			
			Shark now = sharks[i];
			
			int nextX = now.r;
			int nextY = now.c;
			for(int j = 0; j < now.s; j++) {
				int nowX = nextX;
				int nowY = nextY;
				nextX = nextX + dx[now.d];
				nextY = nextY + dy[now.d];
				
				if(nextX > R || nextX < 1 || nextY > C || nextY < 1) {
					int now_dir = now.d;
					if(now_dir == 0) {
						now.d = 1;
					}else if(now_dir == 1) {
						now.d = 0;
					}else if(now_dir == 2) {
						now.d = 3;
					}else {
						now.d = 2;
					}
					
					nextX = nowX + dx[now.d];
					nextY = nowY + dy[now.d];
				}
				
			}
			
			matrix[now.r][now.c] = 0;
			now.r = nextX;
			now.c = nextY;
		}
		
		for(int i = 1; i <= M; i++) {
			if(sharks[i] == null) continue;
			Shark now = sharks[i];
			
			if(matrix[now.r][now.c] == 0) {
				matrix[now.r][now.c] = now.num;
			}else if(sharks[matrix[now.r][now.c]].z > now.z) { // 칸에있는 상어가 더큼
				sharks[i] = null;
			}else { // 다음상어가 더큼
				sharks[matrix[now.r][now.c]] = null;
				matrix[now.r][now.c] = now.num;
			}
		}
		
	}
}
