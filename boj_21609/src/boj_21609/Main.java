package boj_21609;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M;
	static int[][] matrix;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		matrix = new int[N][N];
		int result = 0;
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		Queue<Point> que = new LinkedList<>();
		while(true) {
			ArrayList<Point> biggest = new ArrayList<>();
			int rainbow = 0;
			Point standard = null;
			
			boolean[][] visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					if(matrix[i][j] == -1 || matrix[i][j] == 0 || matrix[i][j] == -2) continue;
					
					ArrayList<Point> group = new ArrayList<>();
					int now_rainbow = 0;
					int now_standard_color = matrix[i][j];
					Point now_standard = new Point(i, j);
					group.add(now_standard);
					que.add(new Point(i, j));
					visited[i][j] = true;
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							if(nextX >= N || nextX < 0 || nextY >= N || nextY < 0 || visited[nextX][nextY] == true || matrix[nextX][nextY] == -1) {
								continue;
							}
							
							if(matrix[nextX][nextY] == 0 || matrix[nextX][nextY] == now_standard_color) {
								que.add(new Point(nextX, nextY));
								visited[nextX][nextY] = true;
								group.add(new Point(nextX, nextY));
								if(matrix[nextX][nextY] == 0) now_rainbow++;
								
							}
						}
					}
					
					for(Point now : group) {
						if(matrix[now.x][now.y] == 0) {
							visited[now.x][now.y] = false;
						}
					}
					if(group.size() >= 2) {
						if(biggest.size() < group.size()) { //블록의 크기가 가장큰것
							biggest = group;
							rainbow = now_rainbow;
							standard = now_standard;
						}else if(biggest.size() == group.size()) { // 크기가 같을때 무지개갯수 비교
							if(rainbow < now_rainbow) {
								biggest = group;
								rainbow = now_rainbow;
								standard = now_standard;
							}else if(rainbow == now_rainbow) { // 블록의 크기, 무지개 갯수도 같을때 기준블록 x, y 비교
								if(standard.x < now_standard.x) {
									biggest = group;
									rainbow = now_rainbow;
									standard = now_standard;
								}else if(standard.x == now_standard.x) {
									if(standard.y < now_standard.y) {
										biggest = group;
										rainbow = now_rainbow;
										standard = now_standard;
									}
								}
							}
						}
					}
					
				}
			}
			
			if(biggest.size() == 0) {
				System.out.println(result);
				break;
			}
			
			int block_size = biggest.size();
			result = result + (block_size * block_size);
			print();
			delete_block(biggest);
			print();
			gravity();
			print();
			
			matrix = rotate();
			print();
			gravity();
			print();

		}
		

	}
	
	static void delete_block(ArrayList<Point> arr) {
		for(Point now : arr) {
			matrix[now.x][now.y] = -2;
		}
	}
	
	static void gravity() {
		for(int i = N - 1; i >= 1; i--) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] == -2) {
					for(int z = i - 1; z >= 0; z--) {
						if(matrix[z][j] == -1) {
							break;
						}
						
						if(matrix[z][j] >= 0) {
							matrix[i][j] = matrix[z][j];
							matrix[z][j] = -2;
							break;
						}
					}
				}
			}
		}
	}
	
	static int[][] rotate() {
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				temp[i][j] = matrix[j][N - i - 1];
			}
		}
		
		return temp;
	}
	
	static void print() {
		System.err.println("------------");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
