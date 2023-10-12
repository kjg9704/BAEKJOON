package boj_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N;
	static int[][] matrix;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] input;
		matrix = new int[101][101];
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				ArrayList<Integer> list = new ArrayList<>();
				int x = Integer.parseInt(input[0]);
				int y = Integer.parseInt(input[1]);
				int d = Integer.parseInt(input[2]);
				int g = Integer.parseInt(input[3]);
				
				int nowX = x + dx[d];
				int nowY = y + dy[d];
				matrix[y][x] = 1;
				matrix[nowY][nowX] = 1;
				list.add(d);
				for(int z = 1; z <= g; z++) {
					for(int k = list.size() - 1; k >= 0; k--) {
						int nextD = list.get(k) + 1;
						if(nextD > 3) {
							nextD = 0;
						}
						list.add(nextD);
						nowX = nowX + dx[nextD];
						nowY = nowY + dy[nextD];
						matrix[nowY][nowX] = 1;
					}
				}
			}
		}
		
		int result = 0;
		for(int y = 0; y < 100; y++) {
			for(int x = 0; x < 100; x++) {
				if(matrix[y][x] == 1) {
					if(matrix[y][x + 1] != 1) {
						continue;
					}
					if(matrix[y + 1][x] != 1) {
						continue;
					}
					if(matrix[y + 1][x + 1] != 1) {
						continue;
					}
					result++;
				}
			}
		}
		
		System.out.println(result);

	}

}
