import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static boolean[] visited;
	static boolean[] team;
	static int result;
	static int[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			list = new int[N + 1];
			String[] temp = br.readLine().split(" ");
			for(int j = 1; j <= temp.length; j++) {
				list[j] = Integer.parseInt(temp[j - 1]);
			}
			result = 0;
			visited = new boolean[N + 1];
			team = new boolean[N + 1];
			for(int j = 1; j <= N; j++) {
				if(!visited[j]) {
					dfs(j, j, list[j], 1);
				}
			}
			System.out.println(result);
		}

	}

	static void dfs(int start, int now, int next, int depth) {
		visited[now] = true;
		if(!visited[next]) {
			dfs(start, next, list[next], depth + 1);
		}else if(!team[next]){
			depth--;
			int member = next;
			while(member != now) {
				member = list[member];
				depth--;
			}
			result += depth;
		}else {
			result += depth;
		}
		team[now] = true;
	}
}
