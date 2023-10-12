package boj_20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {0, 1 , 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int N;
	
	static int[][] move_direction_X = {
			{-1, -1, -2, -1, 1, 1, 2, 1, 0, 0},
			{1, 0, 0, -1, -1, 0, 0, 1, 2, 1},
			{-1, -1, -2, -1, 1, 1, 2, 1, 0, 0},
			{-1, 0, 0, 1, 1, 0, 0, -1, -2, -1}
	};
	static int[][] move_direction_Y = {
			{-1, 0, 0, 1, 1, 0, 0, -1, -2, -1},
			{-1, -1, -2, -1, 1, 1, 2, 1, 0, 0},
			{1, 0, 0, -1, -1, 0, 0, 1, 2, 1},
			{1, 1, 2, 1, -1, -1, -2, -1, 0, 0}
	};
	
	static int[] ratio = {10, 7, 2, 1, 1, 7, 2, 10, 5};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		int[][] matrix = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		int direction = 0;
		int nowX = N / 2;
		int nowY = N / 2;
		int result = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < i; k++) {
					int nextX = nowX + dx[direction];
					int nextY = nowY + dy[direction];
					int now_sand = matrix[nextX][nextY];
					System.out.println(nextX + " " + nextY);
					for(int z = 0; z < 10; z++) {
						int sandX = nextX + move_direction_X[direction][z];
						int sandY = nextY + move_direction_Y[direction][z];
						int move_sand = 0;
						System.out.println("날아가는 위치 ----- " + sandX + " " + sandY + "\n");
						if(z == 9) {
							move_sand = now_sand;
						}else {
							move_sand = matrix[nextX][nextY] * ratio[z] / 100;
							now_sand -= move_sand;
						}
						
	//					System.out.println(move_sand);
						if(sandX >= N || sandX < 0 || sandY >= N || sandY < 0) {
							result += move_sand;
//							System.out.println("밖으로 나감");
						}else {
							matrix[sandX][sandY] += move_sand;
						}
						
					}
					matrix[nextX][nextY] = 0;
					if(nextX == 0 && nextY == 0) {
						System.out.println(result);
						return;
					}
					nowX = nextX;
					nowY = nextY;
					print(matrix);
					System.out.println(result + "\n");
				}
				
				direction++;
				if(direction >= 4) direction = 0;
			}
			
		}

	}
	static void print(int[][] area) {
		for(int i = 0; i < area.length; i++) {
			for(int j = 0; j < area[i].length; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
	}
}
