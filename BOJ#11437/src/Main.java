import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static int[] parents;
	static int[] depths;
	static void FindParent(int node1, int node2) {
		if(node1 == node2) {
			System.out.println(node1);
			return;
		}
		else {
			if(parents[node1] == parents[node2]) {
				System.out.println(parents[node1]);
			}
			else {
				FindParent(parents[node1], parents[node2]);
			}
		}
	}
	static void dfs(int node, int parent, int depth) {
			parents[node] = parent;
			depths[node] = depth;
			for(int i = 1; i <= list.get(node).size(); i++) {
				int next = list.get(node).get(i - 1);
				if(next != parent) {
					dfs(list.get(node).get(i - 1), node, depth + 1);
				}
			}
	}
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		 list = new ArrayList<ArrayList<Integer>>();
		 parents = new int[num + 1];
		 depths = new int[num + 1];
		 for(int i = 0; i < num + 1; i++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < num - 1; i++) {
			String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
				list.get(start).add(end);
				list.get(end).add(start);
		}
		dfs(1, 0, 1);
		num = Integer.parseInt(br.readLine());
		for(int i = 0; i < num; i++) {
			String[] temp = br.readLine().split(" ");
            int node1 = Integer.parseInt(temp[0]);
            int node2 = Integer.parseInt(temp[1]);
            while(depths[node1] != depths[node2]) {
            	if(depths[node1] < depths[node2]) {
            		node2 = parents[node2];
            	}
            	else {
            		node1 = parents[node1];
            	}
            }
            	FindParent(node1, node2);
		}
		br.close();
	}

}
