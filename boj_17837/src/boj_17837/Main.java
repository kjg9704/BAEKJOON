package boj_17837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class chess_board{
		int color;
		int height;
		horse[] horses;
		public chess_board(int color, int height, int K) {
			this.color = color;
			this.height = height;
			this.horses = new horse[K];
		}
	}
	
	static class horse{
		int x;
		int y;
		int dir;
		int index;
		public horse(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.index = 0;
		}
	}
	
	static int[] dx = {0, 0 , -1, 1};
	static int[] dy = {1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		chess_board[][] board = new chess_board[N + 1][N + 1];
		
		horse[] horse_arr = new horse[K];
		int result = 1;
		
		
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				board[i][j] = new chess_board(Integer.parseInt(input[j - 1]), 0, K);
			}
		}
		
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);
			int y = Integer.parseInt(input[1]);
			int dir = Integer.parseInt(input[2]);
			horse_arr[i] = new horse(x, y, dir - 1);
			board[x][y].height = 1;
			board[x][y].horses[0] = horse_arr[i];
		}

		print(horse_arr, board);
		System.out.println("-------------start------------");
		while(true) {
			System.out.println("round " + result);
			for(int i = 0; i < K; i++) {
				
				print(horse_arr, board);
				int nowX = horse_arr[i].x;
				int nowY = horse_arr[i].y;
				
				int nextX = nowX + dx[horse_arr[i].dir];
				int nextY = nowY + dy[horse_arr[i].dir];
				
				if(nextX > N || nextX < 1 || nextY > N || nextY < 1 || board[nextX][nextY].color == 2) {
					int new_dir = get_dir(horse_arr[i].dir);
					horse_arr[i].dir = new_dir;
					nextX = nowX + dx[horse_arr[i].dir];
					nextY = nowY + dy[horse_arr[i].dir];
				}
				
				if(nextX > N || nextX < 1 || nextY > N || nextY < 1 || board[nextX][nextY].color == 2) {
					continue;
				}
				
				if(board[nextX][nextY].color == 0) {
					int nowHeight = board[nowX][nowY].height;
					for(int j = horse_arr[i].index; j < nowHeight; j++) {
						int nextHeight = board[nextX][nextY].height;
						board[nextX][nextY].horses[nextHeight] = board[nowX][nowY].horses[j];
						board[nowX][nowY].horses[j] = null;
						board[nextX][nextY].horses[nextHeight].x = nextX;
						board[nextX][nextY].horses[nextHeight].y = nextY;
						board[nextX][nextY].horses[nextHeight].index = nextHeight;
						board[nextX][nextY].height++;
						board[nowX][nowY].height--;
						if(board[nextX][nextY].height == 4) {
							System.out.println(result);
							return;
						}
					}
				}else if(board[nextX][nextY].color == 1) {
					int nowHeight = board[nowX][nowY].height;
					int end_index = horse_arr[i].index;
					for(int j = nowHeight - 1; j >= end_index; j--) {
						int nextHeight = board[nextX][nextY].height;
						board[nextX][nextY].horses[nextHeight] = board[nowX][nowY].horses[j];
						board[nowX][nowY].horses[j] = null;
						board[nextX][nextY].horses[nextHeight].x = nextX;
						board[nextX][nextY].horses[nextHeight].y = nextY;
						board[nextX][nextY].horses[nextHeight].index = nextHeight;
						board[nextX][nextY].height++;
						board[nowX][nowY].height--;
						if(board[nextX][nextY].height == 4) {
							System.out.println(result);
							return;
						}
					}
				}
				
				
				
			}
			System.out.println(result);
			result++;
			if(result > 1000) {
				System.out.println(-1);
				return;
			}
		}
		
	}
	
	static void print(horse[] horse_arr, chess_board[][] board) {
		System.out.println("----------------------------------");
		for(int i = 1; i < board.length;i++) {
			for(int j = 1; j < board[i].length; j++) {
				System.out.print(board[i][j].height + " ");
			}
			System.out.println();
		}
	}
	static int get_dir(int dir) {
		if(dir == 0) {
			return 1;
		}else if(dir == 1) {
			return 0;
		}else if(dir == 2) {
			return 3;
		}else {
			return 2;
		}
	}

}
