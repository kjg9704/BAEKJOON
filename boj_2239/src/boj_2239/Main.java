package boj_2239;
import java.io.*;
import java.util.*;

public class Main {

	static int[][] matrix = new int[9][9];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int i = 0; i < 9; i++) {
			String input = br.readLine();
			for(int j = 0; j < 9; j++) {
				matrix[i][j] = input.charAt(j) - 0x30;
			}
		}
		
		backtracking(0, 0);

	}
	
	static void backtracking(int x, int y) {

		if(y == 9) {
			y = 0;
			x++;
		}
		
		if(x == 9) {
			print();
			System.exit(0);
		}
		
		if(matrix[x][y] != 0) {
			backtracking(x, y + 1);
		}else {
			for(int i = 1; i <= 9; i++) {
				if(!check(i, x, y)) {
					continue;
				}
				
				matrix[x][y] =  i;
				
				backtracking(x, y + 1);
				
				matrix[x][y] = 0;
			}
		}
		
		
	}
	
	static boolean check(int num, int x, int y) {
		
		for(int i = 0; i < 9; i++) {
			if(matrix[x][i] == num) {
				return false;
			}
		}
		
		for(int i = 0; i < 9; i++) {
			if(matrix[i][y] == num) {
				return false;
			}
		}
		
		int baseX = (x / 3) * 3;
		int baseY = (y / 3) * 3;

		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(matrix[baseX + i][baseY + j] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void print() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
	}

}
