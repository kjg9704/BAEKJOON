import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static int[] parent;
	static int[] cost;
	static ArrayList<Integer>[] adj_list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		adj_list = new ArrayList[N + 1];
		parent = new int[N + 1];
		cost = new int[N + 1];
		
		input = br.readLine().split(" ");
		
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(input[i - 1]);
		}
		
		for(int i = 1; i <= N; i++) {
			adj_list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			
			if(start != end) {
				adj_list[start].add(end);
				adj_list[end].add(start);
			}
		}
		boolean[] visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				parent[i] = i;
				visited[i] = true;
				dfs(visited, i);
			}
		}
		
		visited = new boolean[N + 1];
		int pay = 0;
		for(int i = 1; i <= N; i++) {
			int root = getRoot(i);
			if(!visited[root]) {
				int money = cost[root];
				visited[root] = true;
				if(K >= money) {
					pay += money;
					K -= money;
				}else {
					System.out.println("Oh no");
					return;
				}
			}
		}
		
		System.out.println(pay);
		
	}
	
	static void dfs(boolean[] visited, int start) {
		
		for(int next : adj_list[start]) {
			if(next == start) continue;
			if(visited[next]) continue;
			visited[next] = true;
			union(start, next);
			dfs(visited, next);
		}
	}
	
	static void union(int idx1, int idx2) {
		int tmp1 = getRoot(idx1);
		int tmp2 = getRoot(idx2);
		
		if(tmp1 == tmp2) {
			return;
		}
		
		if(cost[tmp1] > cost[tmp2]) {
			parent[tmp1] = tmp2;
		}else {
			parent[tmp2] = tmp1;
		}
		
	}
	
	static int getRoot(int index) {
		if(parent[index] == index) {
			return index;
		}
		
		if(parent[index] == 0) {
			return parent[index] = index;
		}
		
		return parent[index] = getRoot(parent[index]);
	}

}
