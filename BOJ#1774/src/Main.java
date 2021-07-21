import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Point{
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Edge implements Comparable<Edge>{
	int start;
	int end;
	double distance;
	public Edge(int start, int end, double distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.distance, o.distance);
	}
	
}
public class Main {

	static int[] parents;
	static double result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		final int N = Integer.parseInt(temp[0]);
		final int M = Integer.parseInt(temp[1]);
		Point[] arr = new Point[N + 1];
		parents = new int[N + 1];
		PriorityQueue<Edge> pQue = new PriorityQueue<>();
		result = 0;
		for(int i = 0; i < N; i++) {
			temp = br.readLine().split(" ");
			int x = Integer.parseInt(temp[0]);
			int y = Integer.parseInt(temp[1]);
			arr[i + 1] = new Point(x, y);
		}
		for(int i = 0; i < N; i++) {
			parents[i + 1] = i + 1;
		}
		for(int i = 0; i < M; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			parents[Math.max(start, end)] = Math.min(start, end);
		}
		
		for(int i = 1; i <= N - 1; i++) {
			for(int j = i + 1; j <= N; j++) {
				double distance = getDistance(arr[i], arr[j]);
				Edge edge = new Edge(i, j, distance);
				pQue.add(edge);
			}
		}
		while(!pQue.isEmpty()) {
			Edge now = pQue.poll();
			union(now);
		}
		System.out.printf("%.2f", (double) Math.round(result * 100) / 100);
	}
	
	static int find(int u) {
		if(u == parents[u]) {
			return u;
		}
		return parents[u] = find(parents[u]);
	}
	
	static void union(Edge edge) {
		int a = find(edge.start);
		int b = find(edge.end);
		if(a == b) return;
		parents[b] = a;
		result += edge.distance;
	}
	
	static double getDistance(Point x1, Point x2) {
		long x1x = x1.x;
		long x2x = x2.x;
		long x1y = x1.y;
		long x2y = x2.y;
		return Math.sqrt((x1x - x2x) * (x1x - x2x) + (x1y - x2y) * (x1y - x2y));
	}
}