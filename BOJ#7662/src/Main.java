import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			PriorityQueue<Integer> que = new PriorityQueue<>();
			TreeMap<Integer, Integer> map = new TreeMap<>();
			int k = Integer.parseInt(br.readLine());
			for(int j = 0; j < k; j++) {
				String[] temp = br.readLine().split(" ");
				char instruction = temp[0].charAt(0);
				int n = Integer.parseInt(temp[1]);
				if(instruction == 'I') {
					que.add(n);
					if(map.containsKey(1)) {
						if(map.get(1) < n) {
							map.put(1, n);
						}
					}else {
						map.put(1, n);
					}
				} else if(instruction == 'D') {
					if(n == 1) {
						que.remove(map.get(1));
					}else if(n == -1) {
						que.poll();
					}
				}
				
			}
			if(que.isEmpty()) {
				System.out.println("EMPTY");
			}else {
				System.out.println(que.poll());
			}
			
		}
		
		
	}

}
