import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge>{
	int node;
	int distance;
	public Edge(int node, int distance) {
		this.node = node;
		this.distance = distance;
	}
	@Override 
	public int compareTo(Edge o) { 
		return Integer.compare(this.distance, o.distance);
	}
}

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int V = Integer.parseInt(temp[0]);
		int E = Integer.parseInt(temp[1]);
		int K = Integer.parseInt(br.readLine());
		ArrayList<Edge>[] list = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			temp = br.readLine().split(" ");
			int u = Integer.parseInt(temp[0]);
			int v = Integer.parseInt(temp[1]);
			int w = Integer.parseInt(temp[2]);
			list[u].add(new Edge(v, w));
		}
		br.close();
		PriorityQueue<Edge> que = new PriorityQueue<>();
		int[] distance = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		for (int i = 1; i <= V; i++) {
			// 원하는 출발지
			if (i == K) {
				distance[i] = 0;
			} else {
				distance[i] = Integer.MAX_VALUE;
			}
		}
		que.add(new Edge(K, 0));
		while(!que.isEmpty()) {
			Edge now = que.poll();
			for(Edge next : list[now.node]) {
				if(!visited[next.node] && distance[next.node] > distance[now.node] + next.distance) {
					distance[next.node] = distance[now.node] + next.distance;
					que.add(new Edge(next.node, distance[next.node]));
				}
			}
			visited[now.node] = true;
		}
		for(int i = 1; i <= V; i++) {
			if(distance[i] != Integer.MAX_VALUE) {
				System.out.println(distance[i]);
			}else {
				System.out.println("INF");
			}
		}
	}

}
