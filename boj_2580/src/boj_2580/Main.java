package boj_2580;

import java.io.*;
import java.util.*;

public class Main {

	static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int[][] matrix = new int[9][9];
	static ArrayList<Point> list;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		list = new ArrayList<Point>();
		
		for(int i = 0; i < 9; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < 9; j++) {
				int num = Integer.parseInt(input[j]);
				if(num == 0) {
					list.add(new Point(i, j));
				}
				matrix[i][j] = num;
			}
		}
		
		
		
		
		backtracking(0);
		
	}
	
	static void backtracking(int idx) {

		Point now = list.get(idx);

		for(int i = 1; i <= 9; i++) {
			
			if(!check(i, now.x, now.y)) {
				continue;
			}

			
			matrix[now.x][now.y] = i;
			
			if(idx == list.size() - 1) {
				print();
				System.exit(0);
			}
			
			backtracking(idx + 1);
			
			matrix[now.x][now.y] = 0;
			
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
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

}
