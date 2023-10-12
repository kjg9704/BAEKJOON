import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Queue<Integer> que = new LinkedList<>();
		
		for(int i = 0 ; i < N; i++) {
			que.offer(i + 1);
		}
		
		while(que.size() != 1) {
			que.poll();
			
			if(que.size() == 1) {
				break;
			}
			int num = que.poll();
			que.offer(num);
		}
		
		System.out.println(que.poll());
		
	}

}
