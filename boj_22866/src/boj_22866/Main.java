package boj_22866;

import java.util.*;

public class Main {

	public static class Tower{
		int height;
		int number;
		int cnt;
		int close;
		int diff;
		public Tower(int height, int number) {
			this.height = height;
			this.number = number;
			this.cnt = 0;
			this.close = 100001;
			this.diff = 100001;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		StringBuilder sb = new StringBuilder();
		
		Stack<Tower> stack = new Stack<>();
		
		String[] towers = sc.nextLine().split(" ");
		sc.close();
		Tower[] arr = new Tower[N + 1];
		
		for(int i = 1; i <= N; i++) {
			arr[i] = new Tower(Integer.parseInt(towers[i - 1]), i);
		}
		
		for(int i = 1; i <= N; i++) {
			Tower now = arr[i];
			if(!stack.isEmpty()) {
				
				while(!stack.isEmpty() && stack.peek().height <= now.height) {
					stack.pop();
				}
				now.cnt += stack.size();
				if(!stack.isEmpty()) {
					int dist = Math.abs(stack.peek().number - now.number);
					if(dist < now.diff) {
						now.close = stack.peek().number;
						now.diff = dist;
					}else if(dist == now.diff && stack.peek().number < now.close) {
						now.close = stack.peek().number;
					}
				}
				
				stack.push(now);
				
			}else {
				stack.push(now);
			}
		}
		
		stack.clear();
		
		
		for(int i = N; i > 0; i--) {
			Tower now = arr[i];
			if(!stack.isEmpty()) {
				while(!stack.isEmpty() && stack.peek().height <= now.height) {
					stack.pop();
				}
				now.cnt += stack.size();
				if(!stack.isEmpty()) {
					int dist = Math.abs(stack.peek().number - now.number);
					if(dist < now.diff) {
						now.close = stack.peek().number;
						now.diff = dist;
					}else if(dist == now.diff && stack.peek().number < now.close) {
						now.close = stack.peek().number;
					}
				}
				
				stack.push(now);
				
			}else {
				stack.push(now);
			}
		}

		for(int i = 1; i <= N; i++) {
			if(arr[i].cnt > 0) {
				sb.append(arr[i].cnt).append(" ").append(arr[i].close).append("\n");
			}else {
				sb.append(arr[i].cnt).append("\n");
			}
		}
		
		System.out.println(sb);
		
		
		
	}

}
