import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				priorityQueue.add(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i = 0; i < N; i++) {
			if(i != N - 1) {
				priorityQueue.poll();
			}
			else {
				System.out.println(priorityQueue.peek());
			}
		}
		br.close();

	}

}
