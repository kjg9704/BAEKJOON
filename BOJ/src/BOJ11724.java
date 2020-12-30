import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class GraphCount{
	LinkedList<Integer> list[];
	int num;
	ArrayList<Boolean> visited; 
	public GraphCount(int n) {
		list = new LinkedList[n + 1];
		visited = new ArrayList<Boolean>();
		for(int i =0; i < n + 1; i++) {
			list[i] = new LinkedList<Integer>();
		}
		for(int i = 0; i < n; i++) {
			visited.add(false);
		}
		this.num = n;
	}
	void dfs(int v) {
		visited.set(v - 1, true);
		for(int i : list[v]) {
			if(!visited.get(i - 1)) {
				dfs(i);
			}
		}
	}
	void addEdge(int m, int n) {
		list[m].add(n);
	}
}
public class BOJ11724 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ; 
		st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int link = Integer.parseInt(st.nextToken());
		GraphCount g= new GraphCount(num);
		int count = 0;
		for(int i = 0; i < link; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			g.addEdge(node1, node2);
			g.addEdge(node2, node1);
		}
		for(int i = 0; i <num; i++) {
			if(!g.visited.get(i)){
				g.dfs(i + 1);
				count++;
			}
		}
		System.out.println(count);

	}

}
