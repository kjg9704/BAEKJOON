import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		boolean[] visited = new boolean[N + 1];
		int[] parent = new int[N + 1];
		String[] temp;
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < N - 1; i++) {
			temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			list[start].add(end);
			list[end].add(start);
		}
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		visited[1] = true;
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int next : list[now]) {
				if(!visited[next]) {
					parent[next] = now;
					que.add(next);
					visited[next] = true;
				}
			}
		}
		for(int i = 2; i <= N; i++) {
			System.out.println(parent[i]);
		}
	}

}
