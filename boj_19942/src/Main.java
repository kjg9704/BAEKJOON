import java.io.*;
import java.util.*;

public class Main {

	static int result = Integer.MAX_VALUE;
	static String set;
	static int N;
	static int[][] matrix;
	static int[] min_nut = new int[4];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		matrix = new int[N][5];
		String[] input = br.readLine().split(" ");
		min_nut[0] = Integer.parseInt(input[0]);
		min_nut[1] = Integer.parseInt(input[1]);
		min_nut[2] = Integer.parseInt(input[2]);
		min_nut[3] = Integer.parseInt(input[3]);
		
		for(int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			for(int j = 0; j < 5; j++) {
				matrix[i][j] = Integer.parseInt(input[j]);
			}
		}
		
		boolean[] visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			dfs(visited, i);
		}
		
		if(result == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(result);
			System.out.println(set);
		}
	}
	
	static void dfs(boolean[] visited, int start) {
		
		int sum = 0;
		int mp = 0;
		int mf = 0;
		int ms = 0;
		int mv = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				mp += matrix[i][0];
				mf += matrix[i][1];
				ms += matrix[i][2];
				mv += matrix[i][3];
				sum += matrix[i][4];
				sb.append(i + 1 + " ");
			}
		}
		
		if(mp >= min_nut[0] && mf >= min_nut[1] && ms >= min_nut[2] && mv >= min_nut[3]) {
			if(result > sum) {
				result = sum;
				set = sb.toString();
			}
			return;
		}
		
		for(int i = start; i < N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				dfs(visited, i);
				visited[i] = false;
			}
		}
	}

}
