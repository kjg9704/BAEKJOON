import java.io.*;
import java.util.*;

public class Main {

	static int cnt = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		String[] input = br.readLine().split(" ");
		
		int[] arr = new int[N];
		ArrayList<Integer>[] adj_list = new ArrayList[N];
		int root = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
			adj_list[i] = new ArrayList<Integer>();
			if(arr[i] == -1) {
				root = i;
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(arr[i] >= 0) {
				adj_list[arr[i]].add(i);
			}
		}
		
		int remove = Integer.parseInt(br.readLine());
		
		if(arr[remove] == -1) {
			adj_list[remove] = null;;
		}else {
			adj_list[arr[remove]].remove(new Integer(remove));
			adj_list[remove] = null;
		}
		
		
		dfs(adj_list, arr, root);

		System.out.println(cnt);
		
	}
	
	static void dfs(ArrayList<Integer>[] adj_list, int[] arr, int node) {
		if(adj_list[node] == null) return;
		if(adj_list[node].size() == 0) {
			cnt++;
			return;
		}
		for(int next : adj_list[node]) {
			dfs(adj_list, arr, next);
		}
	}

}
