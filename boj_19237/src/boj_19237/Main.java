package boj_19237;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class Shark{
		int num;
		int x;
		int y;
		int dir;
		
		int[][] priority;
		public Shark(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
	
	static class Board{
		int shark;
		int smell;
		public Board(int shark, int smell) {
			this.shark = shark;
			this.smell = smell;
		}
	}
	
	static Board[][] matrix;
	static Shark[] sharks;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int time = 0;
	static int N, M, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		
		matrix = new Board[N + 1][N + 1];
		sharks = new Shark[M + 1];
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				int num = Integer.parseInt(input[j - 1]);
				if(num > 0) {
					matrix[i][j] = new Board(num, time + K);
					sharks[num] = new Shark(num, i, j);
					sharks[num].priority = new int[4][4];
				}else {
					matrix[i][j] = new Board(num, 0);
				}
			}
		}
		
		input = br.readLine().split(" ");
		for(int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(input[i - 1]) - 1;
		}
		for(int i = 1; i <= M; i++) {
			for(int j = 0; j < 4; j++) {
				input = br.readLine().split(" ");
				sharks[i].priority[j][0] = Integer.parseInt(input[0]) - 1;
				sharks[i].priority[j][1] = Integer.parseInt(input[1]) - 1;
				sharks[i].priority[j][2] = Integer.parseInt(input[2]) - 1;
				sharks[i].priority[j][3] = Integer.parseInt(input[3]) - 1;
			}
			
		}
		
		
		while(count(sharks) >= 2) {
			if(time >= 1000) {
				System.out.println(-1);
				return;
			}
			for(int i = 1; i <= M; i++) {
				if(sharks[i] != null) {
					//¸ÕÀú ³¿»õ¾ø´ÂÄ­ Ã£±â
					Shark now = sharks[i];
					if(!smell_check(sharks[i])) {
						for(int j = 0; j < 4; j++) {
							int nextDir = now.priority[now.dir][j];
							int nextX = now.x + dx[nextDir];
							int nextY = now.y + dy[nextDir];
							if(nextX >= 1 && nextX <= N && nextY >=1 && nextY <= N) {
								if(matrix[nextX][nextY].smell == 0 || matrix[nextX][nextY].smell <= time) {
									now.x = nextX;
									now.y = nextY;
									now.dir = nextDir;
									break;
								}
							}
						}
					}else {
						for(int j = 0; j < 4; j++) {
							int nextDir = now.priority[now.dir][j];
							int nextX = now.x + dx[nextDir];
							int nextY = now.y + dy[nextDir];
							if(nextX >= 1 && nextX <= N && nextY >=1 && nextY <= N) {
								if(matrix[nextX][nextY].shark == now.num) {
									now.x = nextX;
									now.y = nextY;
									now.dir = nextDir;
									break;
								}
							}
						}
					}
					
				}
			}
			
			for(int i = 1; i <= M; i++) {
				Shark now = sharks[i];
				if(now == null) continue;
				if(matrix[now.x][now.y].shark != 0 && matrix[now.x][now.y].smell > time && matrix[now.x][now.y].shark < now.num) {
					sharks[i] = null;
					System.out.println("shark " + i + " deleted");
				}else {
					matrix[now.x][now.y].shark = now.num;
					matrix[now.x][now.y].smell = time + K + 1;
				}
			}
			time++;
			print();
		}
		
		System.out.println(time);
		
	}
	
	
	static boolean smell_check(Shark shark) {
		for(int i = 0; i < 4; i++) {
			int nextX = shark.x + dx[i];
			int nextY = shark.y + dy[i];
			if(nextX > N || nextX < 1 || nextY > N || nextY < 1) {
				continue;
			}
			if(matrix[nextX][nextY].smell == 0 || matrix[nextX][nextY].smell <= time) {
				return false;
			}
		}
		
		return true;
	}
	
	static int count(Shark[] sharks) {
		int cnt = 0;
		for(int i = 1; i <= M; i++) {
			if(sharks[i] != null) {
				cnt++;
			}
		}
		return cnt;
	}
	
	static void print() {
		System.out.println("--------------");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				System.out.print(matrix[i][j].shark + " ");
			}
			System.out.println();
		}
	}
	
	

}
