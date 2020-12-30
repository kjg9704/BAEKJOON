import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
public class Josephus {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> que = new LinkedList<Integer>();
		StringBuilder str = new StringBuilder();
		int num = sc.nextInt();
		int kill = sc.nextInt();
		for(int i = 1; i <= num; i++) {
			que.add(i);
		}
		System.out.print("<");
		while(!que.isEmpty()) {
			for(int i = 0; i < kill; i++) {
				if(i == kill - 1) {
					str.append(que.poll());
				}
				else {
				que.add(que.poll());
				}
			}
			if(!que.isEmpty()) {
				str.append(", ");
			}
		}
		System.out.println(str + ">");

	}

}