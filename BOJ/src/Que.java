import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
public class Que {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        Queue<Integer> que = new LinkedList<Integer>();
        int num = sc.nextInt();
        int element = 0;
        for(int i =0; i < num; i++) {
        	switch(sc.next()) {
        	case "push":
        		element = sc.nextInt();
        		que.offer(element);
        		break;
        	case "pop":
        		if(que.isEmpty()) {
        			System.out.println(-1);
        		}
        		else {
        		System.out.println(que.poll());
        		}
        		break;
        	case "size":
        		System.out.println(que.size());
        		break;
        	case "empty":
        		if(que.isEmpty()) {
        			System.out.println(1);
        		}
        		else {
        			System.out.println(0);
        		}
        		break;
        	case "front":
        		if(que.peek() == null) {
        			System.out.println(-1);
        		}
        		else {
        		System.out.println(que.peek());
        		}
        		break;
        	case "back":
        		if(que.peek() == null) {
        			System.out.println(-1);
        		}
        		else {
        		System.out.println(element);
        		}
        		break;
        	}
        }

}
}
