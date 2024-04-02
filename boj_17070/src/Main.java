import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] matrix;
	static int[][][] cnt;
	
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 0, 1};
	
	static int result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		matrix = new int[N + 1][N + 1];
		cnt = new int[N + 1][N + 1][3];
		
		for(int i = 1; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				matrix[i][j] = Integer.parseInt(input[j - 1]);
			}
		}
		
		cnt[1][2][0] = 1;
		
		dfs(1, 2, 0);
		System.out.println(result);
	}
	
	static void dfs(int x, int y, int dir) {
		//print();
		if(x == N && y == N) {
			result++;
			return;
		}
		
		for(int z = 0; z < 3; z++) {
			if(dir == 0 && z == 1) continue;
			if(dir == 1 && z == 0) continue;
			
			
			if(check(x, y, z)) {
				int nextX = x + dx[z];
				int nextY = y + dy[z];
				
				cnt[nextX][nextY][z] += 1;
				dfs(nextX, nextY, z);
			}
		}
	}
	
	static boolean check(int x, int y, int dir) {
		if(dir == 2) {
			for(int i = 0; i < 3; i++) {
				int nextX = x + dx[i];
				int nextY = y + dy[i];
				if(nextX < 1 || nextX > N || nextY < 0 || nextY > N) return false;
				if(matrix[nextX][nextY] == 1) return false;
			}
		}else {
			int nextX = x + dx[dir];
			int nextY = y + dy[dir];
			if(nextX < 1 || nextX > N || nextY < 0 || nextY > N) return false;
			if(matrix[nextX][nextY] == 1) return false;
		}
		
		return true;
	}
	
	static void print() {
		System.out.println("------------------");
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int sum = 0;
				for(int z = 0; z < 3; z++) {
					sum += cnt[i][j][z];
					if(sum == 4) {
						int aa = 0;
						continue;
					}
				}
				System.out.print(sum + " ");
			}
			System.out.println();
		}
	}

}
