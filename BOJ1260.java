import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class Graph{
	int num;
	LinkedList<Integer>[] list;
	boolean [] visited;
	boolean [] visited2;
	Queue<Integer> que;
	public Graph(int num) {
		this.num = num;
		list = new LinkedList[num + 1];
		visited = new boolean[num + 1];
		visited2 = new boolean[num + 1];
		que = new LinkedList<Integer>();
		for(int i =0; i < num + 1; i++) {
			list[i] = new LinkedList<Integer>();
		}
	}
	void addEdge(int m, int n) {
		list[m].add(n);
	}
	void searchDFS(int v) {
		visited[v] = true;
		System.out.print(v + " ");
		Collections.sort(list[v]);
		for(int i : list[v]) {
			if(visited[i] == false) {
				searchDFS(i);
			}
		}
	}
	void searchBFS(int v) {
		visited2[v] = true;
		que.add(v);
		while(!que.isEmpty()) {
			int node = que.poll();
			System.out.print(node + " ");
			Collections.sort(list[node]);
			for(int i : list[node]) {
				if(visited2[i] == false) {
				visited2[i] = true;
				que.add(i);
				}
			}
		}
		
	}
}
public class BOJ1260 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();
		Graph g = new Graph(num);
		for(int i = 0; i < m; i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			g.addEdge(node1, node2);
			g.addEdge(node2, node1);
		}
		g.searchDFS(v);
		System.out.println("");
		g.searchBFS(v);
		sc.close();

	}

}
