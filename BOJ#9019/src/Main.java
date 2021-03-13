import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
	int var;
	String order;
	public Pair(int var, String order) {
		this.var = var;
		this.order = order;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			String[] temp = br.readLine().split(" ");
			int start = Integer.parseInt(temp[0]);
			int end = Integer.parseInt(temp[1]);
			Queue<Pair> que = new LinkedList<>();
			boolean[] visited = new boolean[10001];
			que.add(new Pair(start, ""));
			while(!que.isEmpty()) {
				Pair now = que.poll();
				int next = now.var * 2 % 10000;
				if(now.var == end) {
					bw.write(now.order + "\n");
					break;
				}
				if(!visited[next]) {
					que.add(new Pair(next, now.order + "D"));
					visited[next] = true;
				}
				if(now.var == 0) {
					next = 9999;
				}else {
					next = now.var - 1;
				}
				if(!visited[next]) {
					que.add(new Pair(next, now.order + "S"));
					visited[next] = true;
				}
				next = now.var % 1000 * 10 + now.var / 1000;
				if(!visited[next]) {
					que.add(new Pair(next, now.order + "L"));
					visited[next] = true;
				}
				next = now.var % 10 * 1000 + now.var / 10;
				if(!visited[next]) {
					que.add(new Pair(next, now.order + "R"));
					visited[next] = true;
				}
			}
			bw.flush();
		}
		
		br.close();
		bw.close();
	}

}
