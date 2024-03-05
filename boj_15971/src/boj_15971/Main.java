package boj_15971;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Edge{
		int node;
		int dist;
		public Edge(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
//	static int[][] edges;
	static boolean[] visited;
	static int robot1;
	static int robot2;
	static int result;
	static int N;
	static ArrayList<Edge>[] adjList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		robot1 = Integer.parseInt(input[1]);
		robot2 = Integer.parseInt(input[2]);
//		edges = new int[N + 1][N + 1];
		visited = new boolean[N + 1];
		
		adjList = new ArrayList[N + 1];
		for(int i = 0; i < N - 1; i++) {
			input = br.readLine().split(" ");
			int node1 = Integer.parseInt(input[0]);
			int node2 = Integer.parseInt(input[1]);
			int dist = Integer.parseInt(input[2]);
			if(adjList[node1] == null) {
				adjList[node1] = new ArrayList<Edge>();
			}
			if(adjList[node2] == null) {
				adjList[node2] = new ArrayList<Edge>();
			}
			
			adjList[node1].add(new Edge(node2, dist));
			adjList[node2].add(new Edge(node1, dist));
//			edges[node1][node2] = dist;
//			edges[node2][node1] = dist;
		}
		
		dfs(robot1, 0, 0);

	}
	
	static void dfs(int node, int dist, int max) {
		
		visited[node] = true;
		if(node == robot2) {
			result = dist - max;
			System.out.println(result);
			return;
		}
		
		for(Edge e : adjList[node]) {
			if(!visited[e.node]) {
				dfs(e.node, dist + e.dist, Math.max(max, e.dist));
			}
		}
//		for(int i = 1; i <= N; i++) {
//			if(!visited[i] && edges[node][i] > 0) {
//				dfs(i, dist + edges[node][i], Math.max(max, edges[node][i]));
//			}
//		}
		
		
	}

}
