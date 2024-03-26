import java.io.*;
import java.util.*;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			
			String[] input = br.readLine().split(" ");
			
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			
			Queue<Integer> que = new LinkedList<>();
			
			ArrayList<Integer>[] adj_list = new ArrayList[n + 1];
			int[] color = new int[n + 1];
			
			for(int i = 1; i <= n; i++) {
				adj_list[i] = new ArrayList<Integer>();
			}
			
			for(int i = 0; i < m; i++) {
				input = br.readLine().split(" ");
				int start = Integer.parseInt(input[0]);
				int end = Integer.parseInt(input[1]);
				
				adj_list[start].add(end);
				adj_list[end].add(start);
				que.add(start);
			}
			
			boolean flag = true;
			while(!que.isEmpty()) {
				if(!flag) break;
				int now = que.poll();
				
				if(color[now] == 0) {
					color[now] = 1;
				}
				for(int next : adj_list[now]) {
					if(color[next] == 0) {
						if(color[now] == 1) {
							color[next] = 2;
							que.add(next);
						}else {
							color[next] = 1;
							que.add(next);
						}
					}else {
						if(color[next] == color[now]) {
							flag = false;
							break;
						}
					}
				}
			}
			
			if(flag) {
				System.out.println("possible");
			}else {
				System.out.println("impossible");
			}
			
			
			
		}

	}

}
