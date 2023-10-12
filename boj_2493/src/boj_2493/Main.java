package boj_2493;
import java.util.*;

public class Main {

	public static class Tower{
		int height;
		int number;
		
		public Tower(int height, int number) {
			this.height = height;
			this.number = number;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		Stack<Tower> stack = new Stack<>();
		String[] towers = sc.nextLine().split(" ");
		
		for(int i = 0; i < N; i++) {
			int now = Integer.parseInt(towers[i]);
			if(!stack.isEmpty()) {
				if(stack.peek().height < now) {
					while(!stack.isEmpty() && stack.peek().height < now) {
						stack.pop();
					}
					if(stack.isEmpty()) {
						sb.append("0 ");
					}else {
						sb.append(stack.peek().number + " ");
					}
					stack.push(new Tower(now, i + 1));
				}else {
					sb.append(stack.peek().number + " ");
					stack.push(new Tower(now, i + 1));
				}
			}else {
				sb.append("0 ");
				stack.push(new Tower(now, i + 1));
			}
		}
		
		System.out.println(sb);
		
		
	}

}
