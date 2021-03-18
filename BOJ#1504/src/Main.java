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
		int N = Integer.parseInt(temp[0]);
		int E = Integer.parseInt(temp[1]);
		ArrayList<Edge>[] list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < E; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int dis = Integer.parseInt(temp[2]);
			list[start].add(new Edge(end, dis));
			list[end].add(new Edge(start, dis));
		}
		temp = br.readLine().split(" ");
		int node1 = Integer.parseInt(temp[0]);
		int node2 = Integer.parseInt(temp[1]);
		br.close();
		PriorityQueue<Edge> que = new PriorityQueue<>();
		boolean[] visit;
		Edge[][] distance = new Edge[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) {
					distance[i][j] = new Edge(j, 0);
				}else {
				distance[i][j] = new Edge(j, Integer.MAX_VALUE);
				}
			}
		}
		for(int i = 1; i <= N; i++) {
			if(!(i == 1 || i == node1 || i == node2)) {
				continue;
			}
			visit = new boolean[N + 1];
			for(int j = 1; j <= N; j++) {
				que.add(distance[i][j]);
			}
			while(!que.isEmpty()) {
				Edge edge = que.poll();
				if(edge.distance == Integer.MAX_VALUE) break;
				for (Edge next : list[edge.node]) {
					if(visit[next.node]) continue;
					if(distance[i][next.node].distance > distance[i][edge.node].distance + next.distance) {
						distance[i][next.node].distance = distance[i][edge.node].distance + next.distance;
						distance[next.node][i].distance = distance[i][edge.node].distance + next.distance;
						que.remove(distance[i][next.node]);
						que.add(distance[i][next.node]);
					}
				} 
				visit[edge.node] = true;
			}
		}
		if(distance[1][node1].distance == Integer.MAX_VALUE || distance[1][node2].distance == Integer.MAX_VALUE || distance[1][N].distance == Integer.MAX_VALUE || distance[node1][node2].distance == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			long result = Math.min((long)distance[1][node1].distance + (long)distance[node1][node2].distance + (long)distance[node2][N].distance, (long)distance[1][node2].distance + (long)distance[node2][node1].distance + (long)distance[node1][N].distance);
			System.out.println(result);
		}
	}
}
