import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			PriorityQueue<Integer> que = new PriorityQueue<>();
			PriorityQueue<Integer> que2 = new PriorityQueue<>(Collections.reverseOrder());
			PriorityQueue<Integer> deletedQue = new PriorityQueue<>();
			PriorityQueue<Integer> deletedQue2 = new PriorityQueue<>(Collections.reverseOrder());
			int k = Integer.parseInt(br.readLine());
			for(int j = 0; j < k; j++) {
				String[] temp = br.readLine().split(" ");
				char instruction = temp[0].charAt(0);
				int n = Integer.parseInt(temp[1]);
				if(instruction == 'I') {
					que.add(n);
					que2.add(n);
				} else if(instruction == 'D') {
					if(n == 1) {
						try {
							int top = top(que2, deletedQue2);
							deletedQue2.add(top);
							deletedQue.add(top);
						} catch(Exception e) {
							
						}
						
					}else if(n == -1) {
						try {
							int top = top(que, deletedQue);
							deletedQue.add(top);
							deletedQue2.add(top);
						} catch(Exception e) {
							
						}
						
					}
				}
				
			}
			int max = 0;
			int min = 0;
			try {
				max = top(que2, deletedQue2);
			} catch(Exception e) {
				System.out.println("EMPTY");
				continue;
			}
			try {
				min = top(que, deletedQue);
			} catch(Exception e) {
			}
			System.out.println(max + " " + min);
			
		}
	}
	
	static int top(PriorityQueue<Integer> que, PriorityQueue<Integer> deletedQue) {
		while (deletedQue.size() > 0 && que.peek().equals(deletedQue.peek()))
        {
            que.poll();
            deletedQue.poll();
        }
		return que.peek();
	}

}