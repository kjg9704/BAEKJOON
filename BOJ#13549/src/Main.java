import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Pair implements Comparable<Pair>{
	int num;
	int time;
	public Pair(int num, int time) {
		this.num = num;
		this.time = time;
	}
	@Override
	public int compareTo(Pair o) {
		return this.num - o.num;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		br.close();
		//PriorityQueue<Pair> que = new PriorityQueue<>();
		Queue<Pair> que = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		que.add(new Pair(N, 0));
		visited[N] = true;
		while(!que.isEmpty()) {
			Pair now = que.poll();
			if(now.num == K) {
				System.out.println(now.time);
				return;
			}else {
				if(now.num * 2 <= 100000 && !visited[now.num * 2]) {
					visited[now.num * 2] = true;
					que.add(new Pair(now.num * 2, now.time));
				}	
				if(now.num - 1 >= 0 && !visited[now.num - 1]) {
					visited[now.num - 1] = true;
					que.add(new Pair(now.num - 1, now.time + 1));
				}
				if(now.num + 1 <= 100000 && !visited[now.num + 1]) {
					visited[now.num + 1] = true;
					que.add(new Pair(now.num + 1, now.time + 1));
				}
				
			}
		}
	}
}