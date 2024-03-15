package boj_16940;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		

		ArrayList<Integer>[] list = new ArrayList[N + 1];

		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < N - 1; i++) {
			String[] input = br.readLine().split(" ");
			
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			list[start].add(end);
			list[end].add(start);

		}
		
		String[] input = br.readLine().split(" ");
		int[] visit_arr = new int[N];
		for(int i = 0; i < N; i++) {
			visit_arr[i] = Integer.parseInt(input[i]);
		}
		if(visit_arr[0] != 1) {
			System.out.println("0");
			return;
		}
		
		boolean[] visited = new boolean[N + 1];
		
		Queue<Integer> que = new LinkedList<>();
		
		int visit_idx = 0;
		int init = visit_arr[visit_idx];
		que.add(init);
		visited[init] = true;
		visit_idx++;
		while(!que.isEmpty()) {
			int now = que.poll();
			int cnt = 0;
			for(int next : list[now]) {
				if(!visited[next]) {
					visited[next] = true;
					cnt++;
				}
			}
			
			for(int i = visit_idx; i < visit_idx + cnt; i++) {
				if(!visited[visit_arr[i]]) {
					System.out.println("0");
					return;
				}
				que.add(visit_arr[i]);				
			}
			visit_idx += cnt;

		}
		
		System.out.println("1");
		
	}

}
