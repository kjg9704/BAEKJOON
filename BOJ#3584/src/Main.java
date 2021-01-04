import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static int[] parents;
	static int[] depths;
	static void LCA(int node1, int node2) {
		if(node1 == node2) {
			System.out.println(node1);
			return;
		}
		else {
			if(parents[node1] == parents[node2]) {
				System.out.println(parents[node1]);
			}
			else {
				LCA(parents[node1], parents[node2]);
			}
		}
	}
	static void DFS(int root, int parent, int depth) {
		depths[root] = depth;
			for(int i = 1; i <= list.get(root).size(); i++) {
				int next = list.get(root).get(i - 1);
				if(next != parent) {
					DFS(next, parents[next], depth + 1);
				}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for(int i = 0; i < num; i++) {
			int n = sc.nextInt();
			list = new ArrayList<ArrayList<Integer>>();
			parents = new int[n + 1];
			depths = new int[n + 1];	
			for(int k = 0; k <= n; k++) {
				list.add(new ArrayList<Integer>());
			}
			for(int j = 0; j < n; j++) {
				if(j != n - 1) {
					int start = sc.nextInt();
					int end = sc.nextInt();
					list.get(start).add(end);
					list.get(end).add(start);
					parents[end] = start;
				}
				else {
					int root = 1;
					while(parents[root] != 0) {
						root++;
					}
					DFS(root, parents[root], 1);
					int node1 = sc.nextInt();
					int node2 = sc.nextInt();
					while(depths[node1] != depths[node2]) {
		            	if(depths[node1] < depths[node2]) {
		            		node2 = parents[node2];
		            	}
		            	else {
		            		node1 = parents[node1];
		            	}
		            }
		            	LCA(node1, node2);
				}
				
			}
		}
		sc.close();
	}

}
