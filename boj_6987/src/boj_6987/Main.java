package boj_6987;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] result_table;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		
		for(int i = 0; i < 4; i++) {
			result_table = new int[6][3];
			String[] input = br.readLine().split(" ");
			int idx = 0;
			for(int j = 0; j < 6; j++) {
				result_table[j][0] = Integer.parseInt(input[idx++]);
				result_table[j][1] = Integer.parseInt(input[idx++]);
				result_table[j][2] = Integer.parseInt(input[idx++]);
			}
			
			int[][] cases = new int[6][3];
			flag = false;
			dfs(cases, 0, 1);
			
			if(flag) {
				sb.append("1 ");
			}else {
				sb.append("0 ");
			}
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int[][] cases, int start, int end) {
		if(flag) return;
		if(end == 6) {
			start++;
			end = start + 1;
		}
		
		if(start == 5) {
			if(check(cases)) {
				flag = true;
			}
			return;
		}
		
		
		//win
		cases[start][0]++;
		cases[end][2]++;
		dfs(cases, start, end + 1);
		cases[start][0]--;
		cases[end][2]--;
		
		//draw
		cases[start][1]++;
		cases[end][1]++;
		dfs(cases, start, end + 1);
		cases[start][1]--;
		cases[end][1]--;
		
		//lose
		cases[start][2]++;
		cases[end][0]++;
		dfs(cases, start, end + 1);
		cases[start][2]--;
		cases[end][0]--;
		
	}
	
	static boolean check(int[][] cases) {
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 3; j++) {
				if(result_table[i][j] != cases[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
