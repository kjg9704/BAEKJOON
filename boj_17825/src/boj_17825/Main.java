package boj_17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static class board{
		int num;
		int nextRed;
		int nextBlue;
		boolean isBlue;
		
		public board(int num, int nextRed) {
			this.num = num;
			this.nextRed = nextRed;
			this.isBlue = false;
		}
	}
	
	static int result;
	static int[] numbers;
	static board[] boards;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		numbers = new int[10];
		for(int i = 0; i < 10; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		boards = new board[43];
		int[] horses = {0, 0, 0, 0};
		
		for(int i = 0; i <= 40; i += 2) {
			boards[i] = new board(i, i + 2);
		}

		boards[10].isBlue = true;
		boards[10].nextBlue = 13;
		boards[20].isBlue = true;
		boards[20].nextBlue = 19;
		boards[30].isBlue = true;
		boards[30].nextBlue = 23;
		
		boards[13] = new board(13, 15);
		boards[15] = new board(16, 17);
		boards[17] = new board(19, 25);
		
		boards[19] = new board(22, 21);
		boards[21] = new board(24, 25);
		
		boards[23] = new board(28, 27);
		boards[27] = new board(27, 29);
		boards[29] = new board(26, 25);
		boards[25] = new board(25, 31);
		
		boards[31] = new board(30, 33);
		boards[33] = new board(35, 40);
		
		boards[42] = new board(0, 0);

		
		dfs(horses, 0, 0);
		
		System.out.println(result);
		
	}
	
	static void dfs(int[] horses, int sum, int depth) {
		if(depth == 10) {
			result = Math.max(result, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(horses[i] != 42) {
				int now_num = horses[i];
				int next = boards[now_num].nextRed;
				if(boards[now_num].isBlue) {
					next = boards[now_num].nextBlue;
				}
				
				for(int j = 1; j < numbers[depth]; j++) {
					if(next == 42) break;
					next = boards[next].nextRed;
				}

				if(check(horses, next)) {
					horses[i] = next;
					dfs(horses, sum + boards[next].num, depth + 1);
					horses[i] = now_num;
				}
			}
		}
	}
	
	static boolean check(int[] horses, int next) {
		for(int i = 0; i < 4; i++) {
			if(horses[i] == next && next != 42) {
				return false;
			}
		}
		return true;
	}

}
