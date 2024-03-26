import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] population;
	
	static ArrayList<Integer>[] adj_list;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		population = new int[N + 1];
		String[] input = br.readLine().split(" ");
		adj_list = new ArrayList[N + 1];
		
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(input[i - 1]);
			adj_list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			int nodes = Integer.parseInt(input[0]);
			
			for(int j = 1; j <= nodes; j++) {
				int end = Integer.parseInt(input[j]);
				adj_list[i].add(end);
			}
		}
		
		boolean[] visited = new boolean[N + 1];
		
		combi(visited, 1, 0);
		
		if(result == Integer.MAX_VALUE) {
			System.out.println("-1");
		}else {
			System.out.println(result);
		}
		
	}
	
	static void combi(boolean[] visited, int index, int cnt) {
		
		if(cnt != 0 && cnt != N) {
			calc(visited);
		}
		
		for(int i = index; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				combi(visited, i, cnt + 1);
				visited[i] = false;
			}
		}
	}
	
	static void calc(boolean[] combi) {
		
		int start1 = 0;
		for(int i = 1; i <= N; i++) {
			if(combi[i]) {
				start1 = i;
				break;
			}
		}
		int start2 = 0;
		for(int i = 1; i <= N; i++) {
			if(!combi[i]) {
				start2 = i;
				break;
			}
		}
		
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		que.add(start1);
		visited[start1] = true;
		int cnt1 = 0;
		int sum1 = 0;
		while(!que.isEmpty()) {
			int now = que.poll();
			cnt1++;
			sum1 += population[now];
			for(int next : adj_list[now]) {
				if(!visited[next] && combi[next]) {
					visited[next] = true;
					que.add(next);
				}
			}
		}
		
		que = new LinkedList<>();
		visited = new boolean[N + 1];
		que.add(start2);
		visited[start2] = true;
		int cnt2 = 0;
		int sum2 = 0;
		while(!que.isEmpty()) {
			int now = que.poll();
			cnt2++;
			sum2 += population[now];
			for(int next : adj_list[now]) {
				if(!visited[next] && !combi[next]) {
					visited[next] = true;
					que.add(next);
				}
			}
		}
		
		if(cnt1 + cnt2 != N) return;
		
		result = Math.min(result, Math.abs(sum1 - sum2));
	}

}
