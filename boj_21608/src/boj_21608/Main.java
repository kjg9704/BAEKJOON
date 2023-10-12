package boj_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N + 1][N + 1];
		ArrayList<Integer>[] list = new ArrayList[N * N + 1];
		for(int i = 1; i <= N * N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		String[] input;
		int result = 0;
		for(int i = 0; i < N * N; i++) {
			input = br.readLine().split(" ");
			int student = Integer.parseInt(input[0]);
			for(int j = 1; j <= 4; j++) {
				list[student].add(Integer.parseInt(input[j]));
			}
			
			int result_adj = -1;
			int result_empty = 0-1;
			int resultX = 0;
			int resultY = 0;
			for(int j = 1; j <= N; j++) {
				for(int k = 1; k <= N; k++) {
					if(matrix[j][k] == 0) {
						int now_empty = 0;
						int now_adj = 0;
						for(int z = 0 ; z < 4; z++) {
							int nextX = j + dx[z];
							int nextY = k + dy[z];
							if(nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
								continue;
							}
							if(matrix[nextX][nextY] == 0) {
								now_empty++;
							}else {
								if(list[student].contains(matrix[nextX][nextY])) {
									now_adj++;
								}
							}
						}
						System.out.println(student);
					System.out.println("now adj = " + now_adj +  " // " + "now_empty = " + now_empty);
						System.out.println("result adj = " + result_adj + " // " + "result_empty = " + result_empty);
						System.out.println();

						if(now_adj > result_adj) {
							result_adj = now_adj;
							result_empty = now_empty;
							resultX = j;
							resultY = k;
						}else if(now_adj == result_adj) {
							if(now_empty > result_empty) {
								result_adj = now_adj;
								result_empty = now_empty;
								resultX = j;
								resultY = k;
							}
						}
						
					}
				}
			}
			matrix[resultX][resultY] = student;
			print(matrix);
			System.out.println("-----------------");
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int now = matrix[i][j];
				int count = 0;
				for(int z = 0 ; z < 4; z++) {
					int nextX = i + dx[z];
					int nextY = j + dy[z];
					if(nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
						continue;
					}
					if(list[now].contains(matrix[nextX][nextY])) {
						count++;
					}
				}
				switch(count) {
				case 1:
					result += 1;
					break;
				case 2:
					result += 10;
					break;
				case 3:
					result += 100;
					break;
				case 4:
					result += 1000;
					break;
				}
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

}
