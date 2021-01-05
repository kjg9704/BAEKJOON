import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static ArrayList<ArrayList<Integer>> list;
	static ArrayList<Integer> depths;
	static void DFS(int root, int parent, int depth) {
		boolean isLeaf = false;
		for(int i = 1; i <= list.get(root).size(); i++) {
			int next = list.get(root).get(i - 1);
			if(next != parent) {
				isLeaf = true;
				DFS(next, root, depth + 1);
				}
		}
		if(!isLeaf) {
			depths.add(depth);
		}
	}
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList<ArrayList<Integer>>();
		depths = new ArrayList<Integer>();
		for(int i = 0; i <= N; i++) {
			list.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < N - 1; i++) {
			String[] temp = br.readLine().split(" ");
            int start = Integer.parseInt(temp[0]);
            int end = Integer.parseInt(temp[1]);
            list.get(start).add(end);
            list.get(end).add(start);
		}
		DFS(1, 0, 0);
		int cnt = 0;
		for(int i = 0; i < depths.size(); i++) {
			cnt += depths.get(i);
		}
		if(cnt % 2 == 1) {
			System.out.println("Yes");
		}
		else {
			System.out.println("No");
		}
		
		br.close();
	}

}
