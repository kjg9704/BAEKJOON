import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static Queue<Integer> que;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		list = new ArrayList[n + 1];
		visited = new boolean[n + 1];
		for(int i = 0 ; i < n + 1 ; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			list[start].add(end);
			list[end].add(start);
		}
		que = new LinkedList<Integer>();
		int count = 0;
		count = list[1].size();
		visited[1] = true;
		for(int next : list[1]) {
			que.add(next);
			visited[next] = true;
			}
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int next : list[now]) {
				if(!visited[next]) {
					visited[next] = true;
					count++;
				}
			}
		}
		System.out.println(count);
		sc.close();
	}

}
