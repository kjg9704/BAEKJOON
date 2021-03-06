import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pair{
	int num;
	int time;
	public Pair(int num, int time) {
		this.num = num;
		this.time = time;
	}
}
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int N = Integer.parseInt(temp[0]);
		int K = Integer.parseInt(temp[1]);
		br.close();
		Queue<Pair> que = new LinkedList<>();
		boolean[] visited = new boolean[100001];
		int[] count = new int[100001];
		que.add(new Pair(N, 0));
		visited[N] = true;
		Arrays.fill(count, Integer.MAX_VALUE);
		count[N] = 0;
		int result = Integer.MAX_VALUE;
		int result2 = 0;
		while(!que.isEmpty()) {
			Pair now = que.poll();
			if(now.num == K) {
				result = Math.min(result, now.time);
				result2++;
			}else if(N < K && count[now.num] <= result){
				if(now.num * 2 <= 100000) {
					if(!visited[now.num * 2]) {
						visited[now.num * 2] = true;
						count[now.num * 2] = now.time + 1;
						que.add(new Pair(now.num * 2, now.time + 1));
					}else if(count[now.num * 2] >= now.time + 1) {
						count[now.num * 2] = now.time + 1;
						que.add(new Pair(now.num * 2, now.time + 1));
					}

				}	
				if(now.num - 1 >= 0) {
					if(!visited[now.num - 1]) {
						visited[now.num - 1] = true;
						count[now.num - 1] = now.time + 1;
						que.add(new Pair(now.num - 1, now.time + 1));
					}else if(count[now.num - 1] >= now.time + 1) {
						count[now.num - 1] = now.time + 1;
						que.add(new Pair(now.num - 1, now.time + 1));
					}
				}
				if(now.num + 1 <= 100000) {
					if(!visited[now.num + 1]) {
						visited[now.num + 1] = true;
						count[now.num + 1] = now.time + 1;
						que.add(new Pair(now.num + 1, now.time + 1));
					}else if(count[now.num + 1] >= now.time + 1) {
						count[now.num + 1] = now.time + 1;
						que.add(new Pair(now.num + 1, now.time + 1));
					}

				}
			}else if(N > K) {
				System.out.println(N - K + "\n" + 1);
				return;
			}
		}
		System.out.println(result + "\n" + result2);
	}

}