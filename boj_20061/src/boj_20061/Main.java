package boj_20061;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] blue_board = new int[4][6];
	static int[][] green_board = new int[6][4];
	
	static int result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] input;
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			int x = Integer.parseInt(input[1]);
			int y = Integer.parseInt(input[2]);
			
			green_input(t, x, y);
			blue_input(t, x, y);
			green_check();
			blue_check();
			print();

			
		}
		print();
		int cnt = 0;
		for(int i = 2; i <= 5; i++) {
			for(int j = 0; j < 4; j++) {
				if(green_board[i][j] == 1) {
					cnt++;
				}
				if(blue_board[j][i] == 1) {
					cnt++;
				}
			}
		}
		
		
		System.out.println(result);
		System.out.println(cnt);

	}
	
	static void green_input(int t, int x, int y) {
		int idx = -1;
		if(t == 1) {
			for(int r = 0; r < 6; r++) {
				if(green_board[r][y] == 0) continue;
				idx = r-1;
				break;
			}
			if(idx == -1) green_board[5][y] = 1;
			else green_board[idx][y] = 1;
		} else if(t == 2) {
			for(int r = 0; r < 6; r++) {
				if(green_board[r][y] == 0 && green_board[r][y+1] == 0) continue;
				idx = r-1;
				break;
			}
			if(idx == -1) green_board[5][y] = green_board[5][y+1] = 1;
			else green_board[idx][y] = green_board[idx][y+1] = 1;
		} else {
			for(int r = 1; r < 6; r++) {
				if(green_board[r][y] == 0) continue;
				idx = r-1;
				break;
			}
			if(idx == -1) green_board[4][y] = green_board[5][y] = 1;
			else green_board[idx-1][y] = green_board[idx][y] = 1;
		}
//		int nowX = 1;
//		int nowY = y;
//		int nextX = nowX;
//		int nextY = nowY;
//		if(t == 1) {
//			while(true) {
//				if(nextX + 1 > 5 || green_board[nextX + 1][nowY] != 0 ) {
//					break;
//				}
//				nextX = nextX + 1;
//			}
//			
//			green_board[nextX][nowY] = 1;
//		}else if(t == 2) {
//			while(true) {
//				if(nextX + 1 > 5 || green_board[nextX + 1][nowY] != 0  || green_board[nextX + 1][nowY + 1] != 0) {
//					break;
//				}
//				nextX = nextX + 1;
//			}
//			
//			green_board[nextX][nowY] = 1;
//			green_board[nextX][nowY + 1] = 1;
//		}else {
//			while(true) {
//				if(nextX + 1 > 4 || green_board[nextX + 1][nowY] != 0 || green_board[nextX + 2][nowY] != 0) {
//					break;
//				}
//				nextX = nextX + 1;
//			}
//			
//			green_board[nextX][nowY] = 1;
//			green_board[nextX + 1][nowY] = 1;
//		}
	}
	
	static void blue_input(int t, int x, int y) {
		int idx = -1;
		if(t == 1) {
			for(int c = 1; c < 6; c++) {
				if(blue_board[x][c] == 0) continue;
				idx = c-1;
				break;
			}
			if(idx == -1) blue_board[x][5] = 1;
			else blue_board[x][idx] = 1;
		} else if(t == 2) {
			for(int c = 1; c < 6; c++) {
				if(blue_board[x][c] == 0) continue;
				idx = c-1;
				break;
			}
			if(idx == -1) blue_board[x][4] = blue_board[x][5] = 1;
			else blue_board[x][idx-1] = blue_board[x][idx] = 1;
		} else {
			for(int c = 1; c < 6; c++) {
				if(blue_board[x][c] == 0 && blue_board[x+1][c] == 0) continue;
				idx = c-1;
				break;
			}
			if(idx == -1) blue_board[x][5] = blue_board[x+1][5] = 1;
			else blue_board[x][idx] = blue_board[x+1][idx] = 1;
		}
//		int nowX = x;
//		int nowY = 1;
//		int nextX = nowX;
//		int nextY = nowY;
//		if(t == 1) {
//			while(true) {
//				if(nextY + 1 > 5 || blue_board[nowX][nextY + 1] != 0 ) {
//					break;
//				}
//				nextY = nextY + 1;
//			}
//			
//			blue_board[nowX][nextY] = 1;
//		}else if(t == 2) {
//			while(true) {
//				if(nextY + 1 > 4 || blue_board[nowX][nextY + 1] != 0  || blue_board[nowX][nextY + 2] != 0) {
//					break;
//				}
//				nextY = nextY + 1;
//			}
//			
//			blue_board[nowX][nextY] = 1;
//			blue_board[nowX][nextY + 1] = 1;
//		}else {
//			while(true) {
//				if(nextY + 1 > 5 || blue_board[nowX][nextY + 1] != 0 || blue_board[nowX + 1][nextY + 1] != 0) {
//					break;
//				}
//				nextY = nextY + 1;
//			}
//			
//			blue_board[nowX][nextY] = 1;
//			blue_board[nowX + 1][nextY] = 1;
//		}
	}
	
	static void green_check() {
		for(int i = 5; i >=  2; i--) {
			boolean is_full = true;
			for(int j = 0; j < 4; j++) {
				if(green_board[i][j] == 0) {
					is_full = false;
					break;
				}
			}
			if(is_full) {
				
				for(int z = i; z >= 1; z--) {
					for(int j = 0; j < 4; j++) {
						green_board[z][j] = green_board[z - 1][j];
					}
				}
				result++;
				i++;
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < 2; i++) {

			for(int j = 0; j < 4; j++) {
				if(green_board[i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		
		if(cnt != 0) {
			for(int i = 5; i >= 2; i--) {
				for(int j = 0; j < 4; j++) {
					green_board[i][j] = green_board[i - cnt][j];
				}
			}
			for(int j = 0; j < 4; j++) {
				green_board[0][j] = green_board[1][j] = 0;
			}
		}
	}
	
	static void blue_check() {
		for(int i = 5; i >=  2; i--) {
			boolean is_full = true;
			for(int j = 0; j < 4; j++) {
				if(blue_board[j][i] == 0) {
					is_full = false;
					break;
				}
			}
			if(is_full) {
				
				for(int z = i; z >= 1; z--) {
					for(int j = 0; j < 4; j++) {
						blue_board[j][z] = blue_board[j][z - 1];
					}
				}
				result++;
				i++;
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < 2; i++) {

			for(int j = 0; j < 4; j++) {
				if(blue_board[j][i] == 1) {
					cnt++;
					break;
				}
			}
		}
		
		if(cnt != 0) {
			for(int i = 5; i >= 2; i--) {
				for(int j = 0; j < 4; j++) {
					blue_board[j][i] = blue_board[j][i - cnt];
				}
			}
			for(int j = 0; j < 4; j++) {
				blue_board[j][0] = blue_board[j][1] = 0;
			}
		}
	}
	
	static void print() {
		System.out.println("-----green-----");
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print(green_board[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----blue-----");

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 6; j++) {
				System.out.print(blue_board[i][j]);
			}
			System.out.println();
		}
	}

}
