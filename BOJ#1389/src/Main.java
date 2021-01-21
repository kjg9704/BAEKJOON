import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static Queue<Integer> que;
	static int count;
	static boolean [] visited;
	static ArrayList<Integer> [] list;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		final int N = sc.nextInt();
		final int M = sc.nextInt();
		list = new ArrayList[N + 1];	
		que = new LinkedList<Integer>();
		int[] result = new int[N + 1];
		for(int i = 0; i <= N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < M; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			list[start].add(end);
			list[end].add(start);
		}
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			dfs(i, 1);
			result[i] = count;
			count = 0;
		}
		int min = 1;
		for(int i = 2; i <= N; i++) {
			if(result[i] < result[min]) {
				min = i;
			}
		}
		System.out.println(min);
		sc.close();
	}
	static void dfs(int start, int add) {
		visited[start] = true;
		ArrayList<Integer> nexts = new ArrayList<Integer>();
		for(int next : list[start]) {
			if(!visited[next]) {
				visited[next] = true;
				nexts.add(next);
				count += add;
			}
		}
		for(int next : nexts) {
			dfs(next, add + 1);
		}
	}
}
