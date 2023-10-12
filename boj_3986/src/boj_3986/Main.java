package boj_3986;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		int cnt = 0;
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			String str = sc.nextLine();
			
			for(int j = 0; j < str.length(); j++) {
				if(!stack.isEmpty()) {
					if(stack.peek() == str.charAt(j)) {
						stack.pop();
					}else {
						stack.push(str.charAt(j));
					}
				}else {
					stack.push(str.charAt(j));
				}
			}
			
			if(stack.isEmpty()) {
				cnt++;
			}
			
			stack.clear();
		}
		
		System.out.println(cnt);
		
	}

}
