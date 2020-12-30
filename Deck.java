import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class Deck {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> deque = new ArrayDeque<Integer>();
		int num = Integer.parseInt(bf.readLine());
		for(int i = 0; i < num; i++) {
			String [] str = bf.readLine().split(" ");
			switch(str[0]) {
			case "push_front":
				deque.addFirst(Integer.parseInt(str[1]));
				break;
			case "push_back":
				deque.addLast(Integer.parseInt(str[1]));
				break;
			case "pop_front":
				if(!deque.isEmpty()) {
				System.out.println(deque.pollFirst());
				}
				else {
					System.out.println(-1);
				}
				break;
			case "pop_back":
				if(!deque.isEmpty()) {
					System.out.println(deque.pollLast());
					}
					else {
						System.out.println(-1);
					}
				break;
			case "size":
				System.out.println(deque.size());			
				break;
			case "empty":
				if(deque.isEmpty()) {
					System.out.println(1);
				}
				else {
					System.out.println(0);
				}
				break;
			case "front":
				if(!deque.isEmpty()) {
				System.out.println(deque.peekFirst());
				}
				else {
					System.out.println(-1);
				}
				break;
			case "back":
				if(!deque.isEmpty()) {
					System.out.println(deque.peekLast());
					}
					else {
						System.out.println(-1);
					}
				break;
			}
		}
		bf.close();

	}

}
