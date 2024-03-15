package boj_1956;
import java.io.*;
import java.util.*;

public class Main {

	static class Status{
		int vertex;
		int cnt;
		
		public Status(int vertex, int cnt) {
			this.vertex = vertex;
			this.cnt = cnt;
		}
	}
	static int V, E;
	static int min = Integer.MAX_VALUE;
	static int[][] matrix;
	static int INF = 100000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		V = Integer.parseInt(input[0]);
		E = Integer.parseInt(input[1]);
		
		matrix = new int[V + 1][V + 1];
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				matrix[i][j] = INF;
			}
		}
		for(int i = 0; i < E; i++) {
			input = br.readLine().split(" ");
			
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int dist = Integer.parseInt(input[2]);
			
			matrix[start][end] = dist;
		}
		
		
		for(int i = 1; i <= V; i++) {
			for(int j = 1; j <= V; j++) {
				for(int z = 1; z <= V; z++) {
					matrix[j][z] = Math.min(matrix[j][z], matrix[j][i] + matrix[i][z]);
				}
			}
			
		}
		
		for(int i = 1; i <= V; i++) {
			if(matrix[i][i] != INF) {
				min = Math.min(min, matrix[i][i]);
			}
		}
		
		if(min == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(min);
		}
		

	}

}
