import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;


public class Main {
	static int count;
	public static class GraphNode{
		LinkedList<Integer> list[];
		int num;
		boolean [] visited;
		Queue<Integer> que;
		public GraphNode(int n) {
			list = new LinkedList[n + 1];
			visited = new boolean[n + 1];
			que = new LinkedList<Integer>();
			for(int i =0; i < n + 1; i++) {
				list[i] = new LinkedList<Integer>();
			}
			this.num = n;
		}
		void BFS(int v) {
			visited[v] = true;
			que.add(v);
			while(!que.isEmpty()) {
				int node = que.poll();
				for(int i : list[node]) {
					if(visited[i] == false) {
						count++;
					visited[i] = true;
					que.add(i);
					}
				}
			}
		}
		void addEdge(int m, int n) {
			list[m].add(n);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int testCase = sc.nextInt();
		for(int i = 0; i < testCase;i++) {
			count = 0;
			int country = sc.nextInt();
			int air = sc.nextInt();
			GraphNode g = new GraphNode(country);
			for(int j = 0; j < air; j++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				g.addEdge(start, end);
				g.addEdge(end, start);
			}
			g.BFS(1);
			System.out.println(count);
		}
		sc.close();
	}

}
