import java.io.*;
import java.util.*;

public class Main {

	static int min = Integer.MAX_VALUE;
	
	static int[][] matrix;
	static boolean[][] visited;
	static int[] papers;
	static int sum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		matrix = new int[10][10];
		visited = new boolean[10][10];
		papers = new int[5];
		for(int i = 0; i < 10; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 10; j++) {
				int now = Integer.parseInt(input[j]);
				matrix[i][j] = now;
				if(now == 1) sum++;
			}
		}
		
		dfs(0, 0, 0);
		if(min == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(min);
		}
		
	}
	
	static void dfs(int index, int cnt, int cover) {
		int x = index / 10;
		int y = index % 10;
		if(cover == sum) {
			min = Math.min(min, cnt);
			return;
		}
		
		if(matrix[x][y] == 0 || visited[x][y]) {
			dfs(index + 1, cnt, cover);
		}else {
			for(int i = 0; i <= 4; i++) {
				if(check(x, y, i)) {
					for(int z = 0; z <= i; z++) {
						for(int k = 0; k <= i; k++) {
							visited[x + z][y + k] = true;
						}
					}
					
					papers[i]++;
					int co = 1;
					if(i == 0) {
						co = 1;
					}else if(i == 1) {
						co = 4;
					}else if(i == 2) {
						co = 9;
					}else if(i == 3) {
						co = 16;
					}else {
						co = 25;
					}
					dfs(index + 1, cnt + 1, cover + co);
					papers[i]--;
					for(int z = 0; z <= i; z++) {
						for(int k = 0; k <= i; k++) {
							visited[x + z][y + k] = false;
						}
					}
				}
			}
		}
		
	}
	
	static boolean check(int x, int y, int size) {
		if(papers[size] == 5) {
			return false;
		}
		boolean flag = true;
		for(int i = 0; i <= size; i++) {
			for(int j = 0; j <= size; j++) {
				if(x + i >= 10 || y + j >= 10) {
					return false;
				}
				if(matrix[x + i][y + j] == 0 || visited[x + i][y + j]) {
					return false;
				}
			}
		}
		
		return flag;
	}

}
