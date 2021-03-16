import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node{
	int num;
	int usado;
	public Node(int num, int usado) {
		this.num = num;
		this.usado = usado;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int Q = Integer.parseInt(temp[1]);
		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<Node>());
		}
		for(int i = 0; i < N - 1; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			int usado = Integer.parseInt(temp[2]);
			list.get(start).add(new Node(end, usado));
			list.get(end).add(new Node(start, usado));
		}
		for(int i = 0; i < Q; i++) {
			temp = br.readLine().split(" ");
			int K = Integer.parseInt(temp[0]);
			int V = Integer.parseInt(temp[1]);
			Queue<Integer> que = new LinkedList<>();
			boolean[] visited = new boolean[N + 1];
			int result = 0;
			que.add(V);
			visited[V] = true;
			while(!que.isEmpty()) {
				int now = que.poll();
				for(Node node : list.get(now)) {
					if(visited[node.num] == false && node.usado >= K) {
						que.add(node.num);
						visited[node.num] = true;
						result++;
					}
				}
			}
			System.out.println(result);
		}
	}
}
