package boj_21610;

import java.io.*;
import java.util.*;

public class Main {
	
	public static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int[][] matrix = new int[N + 1][N + 1];
		ArrayList<Point> cloudsList = new ArrayList<>();
		int result = 0;
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		cloudsList.add(new Point(N, 1));
		cloudsList.add(new Point(N, 2));
		cloudsList.add(new Point(N - 1, 1));
		cloudsList.add(new Point(N - 1, 2));

		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int d = Integer.parseInt(input[0]);
			int s = Integer.parseInt(input[1]);
			for(Point now : cloudsList) {
				Point moved = move(now, d - 1, s);
				matrix[moved.x][moved.y]++;
				now.x = moved.x;
				now.y = moved.y;
			}
			
			for(Point now : cloudsList) {
				int cnt = 0;
				for(int j = 1; j <= 7; j = j + 2) {
					int nextX = now.x + dx[j];
					int nextY = now.y + dy[j];
					if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= N && matrix[nextX][nextY] > 0) {
						cnt++;
					}
				}
				matrix[now.x][now.y] += cnt;
			}
			
			System.out.println("-----구름 이동, 복사 후 증발전---------");
			print(matrix);

			boolean[][] clouds = new boolean[N + 1][N + 1];
			for(Point cloud : cloudsList) {
				clouds[cloud.x][cloud.y] = true;
			}
			System.out.println("-----구름 이동, 복사 후 증발전 구름---------");
			cloud_print(clouds);
			
			cloudsList.clear();
			for(int j = 1; j <= N; j++) {
				for(int z = 1; z <= N; z++) {
					if(!clouds[j][z]) {
						if(matrix[j][z] >= 2) {
							clouds[j][z] = true;
							matrix[j][z] -= 2;
							cloudsList.add(new Point(j, z));
						}
					}else {
						clouds[j][z] = false;
					}
				}
			}
			
			System.out.println("-----증발후---------");
			print(matrix);
			System.out.println("-----증발후 구름---------");
			cloud_print(clouds);
		}

		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				result += matrix[i][j];
			}
		}
		System.out.println(result);
	}
	
	static void print(int[][] matrix) {
		for(int i = 1; i < matrix.length; i++) {
			for(int j = 1; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void cloud_print(boolean[][] clouds) {
		for(int i = 1; i < clouds.length; i++) {
			for(int j = 1; j < clouds[i].length; j++) {
				if(clouds[i][j]) {
					System.out.print("1 ");
				}else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
	}
	
	public static Point move(Point now, int d, int s) {
		int movX = dx[d] * s % N;
		int movY = dy[d] * s % N;
		int movedX = now.x + movX;
		int movedY = now.y + movY;
		if(movedX < 1) {
			movedX = N + movedX;
		}else if(movedX > N) {
			movedX = movedX % N;
		}
		
		if(movedY < 1) {
			movedY = N + movedY;
		}else if(movedY > N){
			movedY = movedY % N;
		}
		
		return new Point(movedX, movedY);
	}

}
