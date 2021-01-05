import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static boolean[] visited;
	static int[] subNodes;
	static int[] parents;
	static void makeTree(int root, int parent) {
		int size = list.get(root).size();
		parents[root] = parent;
		for(int i = 1; i <= size; i++) {
			int next = list.get(root).get(i - 1);
			if(next != parent) {
				makeTree(next, root);
				}
		}
	}
	static void countSubNode(int node) {
		subNodes[node] = 1;
		int size = list.get(node).size();
		for(int i = 1; i <= size; i++) {
			int next = list.get(node).get(i - 1);
			if(next != parents[node]) {
				countSubNode(next);
				subNodes[node] += subNodes[next];
				}
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
        int N = Integer.parseInt(temp[0]);
        int R = Integer.parseInt(temp[1]);
        int Q = Integer.parseInt(temp[2]);
		visited = new boolean[N + 1];
		subNodes = new int[N + 1];
		parents = new int[N + 1];
		list = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < N - 1; i++) {
			temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            list.get(start).add(end);
            list.get(end).add(start);
		}
		makeTree(R, -1);
		countSubNode(R);
		for(int i = 0; i < Q; i++) {
			int query = Integer.parseInt(br.readLine());
			System.out.println(subNodes[query]);
		}
		br.close();
	}

}
