package boj_1613;

import java.io.*;
import java.util.*;

public class Main {

	static int N, K;
	
	static boolean[][] path;
	static ArrayList<Integer>[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		list = new ArrayList[N + 1];
		path = new boolean[N + 1][N + 1];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int first = Integer.parseInt(input[0]);
			int second = Integer.parseInt(input[1]);
			
			list[first].add(second);
		}
		
		for(int i = 1; i <= N; i++) {
			
			Queue<Integer> que = new LinkedList<>();
			que.add(i);
			path[i][i] = true;
			while(!que.isEmpty()) {
				
				int now = que.poll();
				for(int next : list[now]) {
					if(!path[i][next]) {
						path[i][next] = true;
						que.add(next);
					}
				}
			}
		}
		int s = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < s; i++) {
			input = br.readLine().split(" ");
			int first = Integer.parseInt(input[0]);
			int second = Integer.parseInt(input[1]);
			
			if(path[first][second]) {
				System.out.println("-1");
			}else if(path[second][first]) {
				System.out.println("1");
			}else {
				System.out.println("0");
			}
		}

	}

}
