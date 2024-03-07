package boj_2234;

import java.io.*;
import java.util.*;

public class Main {

	
	static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static int M;
	static int[][] matrix;
	static boolean[][] visited;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int rooms;
	static int max_area;
	static int max_room;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		
		Queue<Point> que = new LinkedList<>();
		
		matrix = new int[M][N];
		visited = new boolean[M][N];
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		rooms = 0;
		max_area = 0;
		max_room = 0;
		int[][] room_group = new int[M][N];
		int room_num = 0;
		ArrayList<Integer> room_cnt = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					int now_area = 0;
					que.add(new Point(i, j));
					visited[i][j] = true;
					now_area++;
					room_group[i][j] = room_num;
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || visited[nextX][nextY] == true) {
								continue;
							}
							
							if(z == 0 && (matrix[now.x][now.y] & 2) == 2) {
								continue;
							}
							
							if(z == 1 && (matrix[now.x][now.y] & 4) == 4) {
								continue;
							}
							
							if(z == 2 && (matrix[now.x][now.y] & 8) == 8) {
								continue;
							}
							
							if(z == 3 && (matrix[now.x][now.y] & 1) == 1) {
								continue;
							}
							
							visited[nextX][nextY] = true;
							room_group[nextX][nextY] = room_num;
							now_area++;
							que.add(new Point(nextX, nextY));
							
						}
					}
					
					room_cnt.add(now_area);
					max_area = Math.max(max_area, now_area);
					rooms++;
					room_num++;
				}
			}
		}
		
		visited = new boolean[M][N];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					int now_group = room_group[i][j];
					que.add(new Point(i, j));
					visited[i][j] = true;
					
					while(!que.isEmpty()) {
						Point now = que.poll();
						
						for(int z = 0; z < 4; z++) {
							int nextX = now.x + dx[z];
							int nextY = now.y + dy[z];
							
							if(nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || visited[nextX][nextY] == true) {
								continue;
							}
							
							if(room_group[nextX][nextY] == now_group) {
								visited[nextX][nextY] = true;
								que.add(new Point(nextX, nextY));
							}else {
								int merge = room_cnt.get(now_group) + room_cnt.get(room_group[nextX][nextY]);
								max_room = Math.max(max_room, merge);
							}
						}
					}
				}
			}
		}
		
		System.out.println(rooms);
		System.out.println(max_area);
//		for(int i : room_cnt) {
//			System.out.print(i + " ");
//		}
		System.out.println(max_room);
	}

}
