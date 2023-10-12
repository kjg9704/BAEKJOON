package boj_17779;

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
	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N + 1][N + 1];
		
		String[] input;
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		int result = Integer.MAX_VALUE;
		
		for(int x = 1; x <= N; x++) {
			for(int y = 1; y <= N; y++) {
				for(int d1 = 1; N >= x + d1; d1++) {
					for(int d2 = 1; N >= y + d2; d2++) {
						if(x + d1 + d2 <= N && 1 <= y - d1 &&  y + d2 <= N) {
							int max = 0;
							int min = Integer.MAX_VALUE;
							
							Queue<Point> que = new LinkedList<>();
							boolean[][] visited = new boolean[N + 1][N + 1];
							int[][] area = new int[N + 1][N + 1];
							
							area[x][y] = 5;
							for(int z = 1; z <= d1; z++) {
								area[x + z][y - z] = 5;
							}
							
							for(int z = 1; z <= d2; z++) {
								area[x + z][y + z] = 5;
							}
							
							for(int z = 1; z <= d2; z++) {
								area[x + d1 + z][y - d1 + z] = 5;
							}
							
							for(int z = 1; z < d1; z++) {
								area[x + d2 + z][y + d2 - z] = 5;
							}
							
							
							//1선거구
							int area1 = 0;
							que.add(new Point(1, 1));
							visited[1][1] = true;
							
							
							while(!que.isEmpty()) {
								Point now = que.poll();
								area[now.x][now.y] = 1;
								area1 += matrix[now.x][now.y];
								for(int z = 0; z < 4; z++) {
									int nextX = now.x + dx[z];
									int nextY = now.y + dy[z];
									if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= N && area[nextX][nextY] != 5 && !visited[nextX][nextY]) {
										if(nextX < x + d1 && nextY <= y) {
											visited[nextX][nextY] = true;
											que.add(new Point(nextX, nextY));
										}
									}
								}
							}
							
							max = Math.max(max, area1);
							min = Math.min(min, area1);
							
							//2선거구
							int area2 = 0;
							que.add(new Point(1, N));
							visited[1][N] = true;
							
							
							while(!que.isEmpty()) {
								Point now = que.poll();
								area[now.x][now.y] = 2;
								area2 += matrix[now.x][now.y];
								for(int z = 0; z < 4; z++) {
									int nextX = now.x + dx[z];
									int nextY = now.y + dy[z];
									if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= N && area[nextX][nextY] != 5 && !visited[nextX][nextY]) {
										if(nextX <= x + d2 && y < nextY) {
											visited[nextX][nextY] = true;
											que.add(new Point(nextX, nextY));
										}
									}
								}
							}
							
							max = Math.max(max, area2);
							min = Math.min(min, area2);
							
							//3선거구
							int area3 = 0;
							que.add(new Point(N, 1));
							visited[N][1] = true;
							
							
							while(!que.isEmpty()) {
								Point now = que.poll();
								area[now.x][now.y] = 3;
								area3 += matrix[now.x][now.y];
								for(int z = 0; z < 4; z++) {
									int nextX = now.x + dx[z];
									int nextY = now.y + dy[z];
									if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= N && area[nextX][nextY] != 5 && !visited[nextX][nextY]) {
										if(x + d1 <= nextX && nextY < y - d1 + d2) {
											visited[nextX][nextY] = true;
											que.add(new Point(nextX, nextY));
										}
									}
								}
							}
							
							max = Math.max(max, area3);
							min = Math.min(min, area3);
							
							//4선거구
							int area4 = 0;
							que.add(new Point(N, N));
							visited[N][N] = true;
							
							
							while(!que.isEmpty()) {
								Point now = que.poll();
								area[now.x][now.y] = 4;
								area4 += matrix[now.x][now.y];
								for(int z = 0; z < 4; z++) {
									int nextX = now.x + dx[z];
									int nextY = now.y + dy[z];
									if(nextX >= 1 && nextX <= N && nextY >= 1 && nextY <= N && area[nextX][nextY] != 5 && !visited[nextX][nextY]) {
										if(x + d2 < nextX && y - d1 + d2 <= nextY) {
											visited[nextX][nextY] = true;
											que.add(new Point(nextX, nextY));
										}
									}
								}
							}
							
							print(area);
							max = Math.max(max, area4);
							min = Math.min(min, area4);
							
							int area5 = 0;
							for(int z = 1; z <= N; z++) {
								for(int q = 1; q <= N; q++) {
									if(area[z][q] == 5 || area[z][q] == 0) {
										area5 += matrix[z][q];
									}
								}
							}
							
							max = Math.max(max, area5);
							min = Math.min(min, area5);
							System.out.println(max - min);
							System.out.println("----------------------");
							result = Math.min(result, max - min);
							
						}
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	static void print(int[][] area) {
		for(int i = 1; i < area.length; i++) {
			for(int j = 1; j < area[i].length; j++) {
				System.out.print(area[i][j] + " ");
			}
			System.out.println();
		}
	}

}
