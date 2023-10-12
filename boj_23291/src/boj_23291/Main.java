package boj_23291;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, K;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int max, min;
	static int[][] matrix;
	static int row;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		row = N / 2;
		if(row < 4) row = 4;
		matrix = new int[row + 1][N + 1];
		
		input = br.readLine().split(" ");
		for(int i = 1; i <= N; i++) {
			matrix[row][i] = Integer.parseInt(input[i - 1]);
		}
		
		int count = 0;
		while(!check()) {
			
			min_add();
			
			matrix[row - 1][1] = matrix[row][1];
			for(int i = 2; i <= N; i++) {
				matrix[row][i - 1] = matrix[row][i];
				matrix[row][i] = 0;
			}
			
			while(true) {
				int cnt = get_height(1);
				int remain = get_remain();
				
				if(cnt > remain) {
					break;
				}
				
				
				stack_90();
				
				
			}
			
			spread();
			
			make_floor();
			
			stack_180();
			
			stack_180();
			
			spread();
			
			make_floor();
			
			count++;
		}
		
		System.out.println(count);
	}
	
	static void spread() {
		int[][] temp_matrix = new int[row + 1][N + 1];
		
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= N; j++) {
				if(matrix[i][j] == 0) {
					continue;
				}
				int nowX = i;
				int nowY = j;
				
				for(int z = 0; z < 4; z++) {
					int nextX = nowX + dx[z];
					int nextY = nowY + dy[z];
					
					if(nextX > row || nextX < 1 || nextY > N || nextY < 1 || matrix[nextX][nextY] == 0) {
						continue;
					}
					
					if(matrix[nowX][nowY] < matrix[nextX][nextY]) {
						temp_matrix[nowX][nowY] += (matrix[nextX][nextY] - matrix[nowX][nowY]) / 5;
					}else if(matrix[nowX][nowY] > matrix[nextX][nextY]) {
						temp_matrix[nowX][nowY] -= (matrix[nowX][nowY] - matrix[nextX][nextY]) / 5;
					}
				}
				
			}
		}
		
		for(int i = 1; i <= row; i++) {
			for(int j = 1; j <= N; j++) {
				if(matrix[i][j] > 0) {
					matrix[i][j] += temp_matrix[i][j];
				}
			}
		}
	}
	
	
	static void stack_90() {
		int height = get_height(1);
		int height_cnt = get_height_count();
		int[][] temp_matrix = rotate_90(height, height_cnt);
		
		for(int i = 1; i < row; i++) {
			matrix[i] = new int[N + 1];
		}
		for(int i = height_cnt + 1; i <= N; i++) {
			if(matrix[row][i] == 0) {
				break;
			}
			matrix[row][i - height_cnt] = matrix[row][i];
			matrix[row][i] = 0;
		}
		
		for(int i = 0; i < temp_matrix.length; i++) {
			for(int j = 0; j < temp_matrix[i].length; j++) {
				matrix[row - height_cnt + i][1 + j] = temp_matrix[i][j];
			}
		}
		
	}
	
	static void stack_180() {
		int height = get_height(1);

		int col = N;
		for(int i = 0; i < height; i++) {
			col = col / 2;
		}
		int[][] temp_matrix = rotate_180(height, col);
		
		for(int i = row; i >= row - height + 1; i--) {
			for(int j = col + 1; j <= N; j++) {
				if(matrix[i][j] == 0) {
					break;
				}
				
				matrix[i][j - col] = matrix[i][j];
				matrix[i][j] = 0;
			}
		}
		
		
		
		for(int i = 0; i < temp_matrix.length; i++) {
			for(int j = 0; j < temp_matrix[i].length; j++) {
				if(height == 1) {
					matrix[row - 1 + i][1 + j] = temp_matrix[i][j];
				}else if(height == 2) {
					matrix[row - 3 + i][1 + j] = temp_matrix[i][j];
				}
				
			}
		}
		
	}
	
	static int[][] rotate_90(int height, int col){
		int[][] temp = new int[col][height];
		
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < height; j++) {
				temp[i][j] = matrix[row - j][i + 1];
			}
		}
		return temp;
	}
	
	static int[][] rotate_180(int height, int col){
		int[][] temp = new int[height][col];
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < col; j++) {
				temp[i][j] = matrix[row - i][col - j];
			}
		}
		return temp;
	}
	
	static boolean check() {
		boolean flag = false;
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, matrix[row][i]);
			min = Math.min(min, matrix[row][i]);
		}
		
		if(max - min <= K) {
			flag = true;
		}
		return flag;
	}
	
	static void min_add() {
		for(int i = 1; i <= N; i++) {
			if(matrix[row][i] == min) {
				matrix[row][i]++;
			}
		}
	}
	
	static void make_floor() {
		int[] temp = new int[N];
		int index = 0;
		
		for(int i = 1; i <= N; i++) {
			if(matrix[row][i] == 0) {
				break;
			}
			for(int j = row; j >= 1; j--) {
				if(matrix[j][i] > 0) {
					temp[index++] = matrix[j][i];
				}else {
					break;
				}
			}
		}
		matrix = new int[row + 1][N + 1];
		
		for(int i = 0 ; i < N; i++) {
			matrix[row][i + 1] = temp[i];
		}
		
		
	}
	
	static int get_height(int col) {
		int height = 1;
		for(int i = row - 1; i >= 1; i--) {
			if(matrix[i][col] > 0) {
				height++;
			}else {
				break;
			}
		}
		
		return height;
	}
	
	static int get_height_count() {
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if(get_height(i) > 1) {
				count++;
			}else {
				break;
			}
		}
		return count;
	}
	
	static int get_remain() {
		int remain = 0;
		for(int i = 1; 1 <= N; i++) {
			if(matrix[row][i] == 0) {
				break;
			}
			if(get_height(i) == 1) {
				remain++;
			}
		}
		return remain;
	}
	
	
}
