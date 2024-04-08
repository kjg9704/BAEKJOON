import java.io.*;
import java.util.*;

public class Main {

	static class Problem implements Comparable<Problem>{
		int dead;
		int cups;
		public Problem(int dead, int cups) {
			this.dead = dead;
			this.cups = cups;
		}
		@Override
		public int compareTo(Main.Problem o) {
			if(this.dead - o.dead == 0) {
				return o.cups - this.cups;
			}else {
				return this.dead - o.dead;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Problem> pq = new PriorityQueue<>();
		PriorityQueue<Integer> cups_pq = new PriorityQueue<>();

		

		int last = 0;
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			int dead = Integer.parseInt(input[0]);
			int cups = Integer.parseInt(input[1]);
			pq.add(new Problem(dead, cups));
			last = Math.max(last, dead);
		}
		
		int result = 0;
		
		int time = 0;
		
		while(!pq.isEmpty()) {
			if(pq.peek().dead <= time) {
				if(cups_pq.peek() < pq.peek().cups) {
					cups_pq.poll();
					cups_pq.add(pq.poll().cups);
				}else {
					pq.poll();
				}
			}else {
				cups_pq.add(pq.poll().cups);
				time++;
			}
			
			
		}
		
		for(int i = 0; i < time; i++) {
			result += cups_pq.poll();
		}
		System.out.println(result);

	}

}
