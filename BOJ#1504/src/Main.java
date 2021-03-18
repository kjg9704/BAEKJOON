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
			int dis = Integer.parseInt(temp[1]);
			list[start].add(new Edge(end, dis));
		}
		temp = br.readLine().split(" ");
		int node1 = Integer.parseInt(temp[0]);
		int node2 = Integer.parseInt(temp[1]);
		br.close();
		PriorityQueue<Edge> que = new PriorityQueue<>();
		boolean[] visit = new boolean[N + 1];
		Edge[] distance = new Edge[N + 1];
		for(int i = 1; i <= N; i++) {
			if(i == 1) {
				distance[i] = new Edge(i, 0);
			}else {
				distance[i] = new Edge(i, Integer.MAX_VALUE);
			}
			que.add(distance[i]);
		}
		

	}

}
