package boj_19236;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	
	static class Fish{
		int num;
		int dir;
		public Fish(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	
	static class FishPoint{
		int x;
		int y;
		int num;
		int dir;
		public FishPoint(int x, int y, int num, int dir) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
	static int[] dx = {-1 , -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int result = 0;
	static FishPoint shark;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Fish[][] table = new Fish[4][4];
		FishPoint[] arr = new FishPoint[17];
		for(int i = 0; i < 4; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 8; j += 2) {
				int num = Integer.parseInt(input[j]);
				int dir = Integer.parseInt(input[j + 1]);
				table[i][j / 2] = new Fish(num, dir - 1);
				arr[num] = new FishPoint(i, j / 2, num, dir - 1);
			}
		}
		
		int fishNum = table[0][0].num;
		arr[fishNum] = null;
		table[0][0].num = -1;
		result += fishNum;
		shark = new FishPoint(0, 0, -1, table[0][0].dir);
		dfs(table, arr, fishNum);
		System.out.println(result);
		
	}
	
	static void dfs(Fish[][] table, FishPoint[] arr, int sum) {
		
		print(table);

		for(int i = 1; i <= 16; i++) {
			FishPoint now = arr[i];
			if(now == null) continue;
			
			int nextX = now.x + dx[now.dir];
			int nextY = now.y + dy[now.dir];
			int nextDir = now.dir;
			
			for(int z = 1; z < 8; z++) {
				nextX = now.x + dx[nextDir];
				nextY = now.y + dy[nextDir];
				if(nextX >= 4 || nextX < 0 || nextY >= 4 || nextY < 0 || table[nextX][nextY].num == -1) {
					nextDir = nextDir + 1;
					if(nextDir == 8) {
						nextDir = 0;
					}
				}else {
					now.dir = nextDir;
					table[now.x][now.y].dir = nextDir;
					arr[i].dir = nextDir;
					break;
				}
				
			}
			
			Fish next = table[nextX][nextY];
			int tmpX = now.x;
			int tmpY = now.y;
			int nextNum = next.num;
			Fish tmp = new Fish(table[now.x][now.y].num, nextDir);
			
			table[now.x][now.y] = next;
			table[nextX][nextY] = tmp;
			
			arr[tmp.num].x = nextX;
			arr[tmp.num].y = nextY;
			if(nextNum > 0) {
				arr[nextNum].x = tmpX;
				arr[nextNum].y = tmpY;
			}
			
		}
		
		Fish[][] prev_table = new Fish[4][4];
		FishPoint[] prev_arr = new FishPoint[17];
		for(int i = 1; i < 17; i++) {
			if(arr[i] != null) {
				prev_arr[i] = new FishPoint(arr[i].x, arr[i].y, arr[i].num, arr[i].dir);
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				prev_table[i][j] = new Fish(table[i][j].num, table[i][j].dir);
			}
		}
		
		FishPoint[] next_arr = get_next(table, shark.x, shark.y, shark.dir);
		
		if(next_arr[0] == null) {
			result = Math.max(result, sum);
			return;
		}
		
		int shark_x = shark.x;
		int shark_y = shark.y;
		int shark_dir = shark.dir;
		for(int i = 0; i < 3; i++) {
			if(next_arr[i] == null) break;
			table[shark.x][shark.y].num = 0;
			
			shark.x = next_arr[i].x;
			shark.y = next_arr[i].y;
			shark.dir = next_arr[i].dir;
			arr[next_arr[i].num] = null;
			table[shark.x][shark.y].num = -1;
			
			dfs(table, arr, sum + next_arr[i].num);
			
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					table[j][k] = new Fish(prev_table[j][k].num, prev_table[j][k].dir);
				}
			}
			for(int j = 1; j < 17; j++) {
				if(prev_arr[j] != null) {
					arr[j] = new FishPoint(prev_arr[j].x, prev_arr[j].y, prev_arr[j].num, prev_arr[j].dir);
				}
			}

			shark.x = shark_x;
			shark.y = shark_y;
			shark.dir = shark_dir;
		}
		
	}
	
	static FishPoint[] get_next(Fish[][] table, int startX, int startY, int dir) {
		int nextX = startX;
		int nextY = startY;
		FishPoint[] arr = new FishPoint[3];
		int index = 0;
		for(int i = 0; i < 3; i++) {
			nextX = nextX + dx[dir];
			nextY = nextY + dy[dir];
			if(nextX >= 4 || nextX < 0 || nextY >= 4 || nextY < 0 || table[nextX][nextY].num == 0) {
				continue;
			}
			arr[index++] = new FishPoint(nextX, nextY, table[nextX][nextY].num, table[nextX][nextY].dir);
		}
		return arr; 
	}
	
	static void print(Fish[][] table) {
		System.out.println("------------");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(table[i][j].num + " " );
			}
			System.out.println();
		}
	}

}
