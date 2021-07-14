import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class Point{
	double x;
	double y;
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
class Edge implements Comparable<Edge>{
	int start;
	int end;
	double cost;
	public Edge(int start, int end, double cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
		return (int)(this.cost - o.cost);
	}
}
public class Main {

	static int[] parents;
	static Point[] points;
	static double result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		points = new Point[N];
		parents = new int[N];
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			double x = Double.parseDouble(temp[0]);
			double y = Double.parseDouble(temp[1]);
			points[i] = new Point(x, y);
		}
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
		ArrayList<Edge> edgeList = new ArrayList<>();
		for(int i = 0; i < N - 1; i++) {
			for(int j = i + 1; j < N; j++) {
				double distance = getDistance(points[i], points[j]);
				Edge newEdge = new Edge(i, j, distance);
				edgeList.add(newEdge);
			}
		}
		Collections.sort(edgeList);
		result = 0;
		for(Edge now : edgeList) {
			union(now);
		}
		System.out.println(result);
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
		result += edge.cost;
	}
	
	static double getDistance(Point x1, Point x2) {
		return Math.round(Math.sqrt((x1.x - x2.x) * (x1.x - x2.x) + (x1.y - x2.y) * (x1.y - x2.y))* 100) / 100.0;
	}

}