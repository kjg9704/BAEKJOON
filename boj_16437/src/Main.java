import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer> sheeps;
	static ArrayList<Integer>[] adj_list;
	static boolean[] is_wolf;
	static int[] amount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		sheeps = new ArrayList<Integer>();
		adj_list = new ArrayList[N + 1];
		is_wolf = new boolean[N + 1];
		amount = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			adj_list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 2; i <= N; i++) {
			String[] input = br.readLine().split(" ");
			
			char type = input[0].charAt(0);
			int num = Integer.parseInt(input[1]);
			int path = Integer.parseInt(input[2]);
			
			if(type == 'W') {
				is_wolf[i] = true;
			}else {
				sheeps.add(i);
			}
			
			amount[i] = num;
			adj_list[i].add(path);	
			adj_list[path].add(i);
		}
		
		boolean[] visited = new boolean[N + 1];
		
		visited[1] = true;
		long result = dfs(visited, 1);
		
		
		System.out.println(result);
		
	}
	
	static long dfs(boolean[] visited, int now) {
		
		long sheep_cnt = 0;
		
		for(int next : adj_list[now]) {
			if(!visited[next]) {
				visited[next] = true;
				if(is_wolf[next]) {
					sheep_cnt += dfs(visited, next);
				}else {
					sheep_cnt += dfs(visited, next);
				}
				
			}
		}
		
		if(!is_wolf[now]) {
			return sheep_cnt + amount[now];
		}else {
			if(amount[now] < sheep_cnt) {
				return sheep_cnt - amount[now];
			}else {
				return 0;
			}
		}
		
	}

}