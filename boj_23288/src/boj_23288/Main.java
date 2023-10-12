package boj_23288;

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
	
	static int N;
	static int M;
	static int K;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		int[] dice = new int[6];
		dice[0] = 1;
		dice[1] = 2;
		dice[2] = 4;
		dice[3] = 3;
		dice[4] = 5;
		dice[5] = 6;
		
		int result = 0;
		int[][] matrix = new int[N + 1][M + 1];
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= M; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		int direction = 0;
		int nowX = 1;
		int nowY = 1;
		
		Queue<Point> que = new LinkedList<>();
		for(int i = 0; i < K; i++) {
			int nextX = nowX + dx[direction];
			int nextY = nowY + dy[direction];
			if(nextX < 1 || nextX > N || nextY < 1 || nextY > M) {
				direction = (direction + 2) % 4;
				nextX = nowX + dx[direction];
				nextY = nowY + dy[direction];
			}
			
			move(dice, direction);
			int numA = dice[5];
			nowX = nextX;
			nowY = nextY;
			int numB = matrix[nowX][nowY];
			if(numA > numB) {
				direction += 1;
			}else if(numA < numB) {
				direction -= 1;
			}
			
			if(direction == 4) {
				direction = 0;
			}else if(direction == -1) {
				direction = 3;
			}
			
			int cnt = 1;
			
			que.add(new Point(nowX, nowY));
			boolean[][] visited = new boolean[N + 1][M + 1];
			visited[nowX][nowY] = true;
			while(!que.isEmpty()) {
				Point now = que.poll();
				for(int k = 0; k < 4; k++) {
					int targetX = now.x + dx[k];
					int targetY = now.y + dy[k];
					if(targetX >= 1 && targetX <= N && targetY >= 1 && targetY <= M && !visited[targetX][targetY] && numB == matrix[targetX][targetY]) {
						que.add(new Point(targetX, targetY));
						visited[targetX][targetY] = true;
						cnt++;
					}
				}
			}
			
			result += (cnt * numB);

		}
		
		System.out.println(result);
	}
	
	static void move(int[] dice, int direction) {
		int tmp = 0;
		switch(direction) {
		case 0://µ¿
			tmp = dice[3];
			dice[3] = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = tmp;
			break;
		case 1://³²
			tmp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
			break;
		case 2://¼­
			tmp = dice[2];
			dice[2] = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = tmp;
			break;
		case 3://ºÏ
			tmp = dice[0];
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
			break;
		}
	}

}
