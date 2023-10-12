package boj_4949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> stack = new Stack<>();
		
		String str;
		while(true) {
			str = br.readLine();
			if(str.charAt(0) == '.') break;
			
			for(int i = 0; i < str.length(); i++) {
				char now = str.charAt(i);
				if(now == '(' || now == ')' || now == '[' || now == ']') {
					if(!stack.isEmpty()) {
						char top = stack.peek();
						if(top == '(') {
							if(now == ')') stack.pop();
							else stack.push(now);
						}else if (top == '['){
							if(now == ']') stack.pop();
							else stack.push(now);
						}else {
							stack.push(now);
						}
					}else {
						stack.push(now);
					}
				}
				
			}
			
			if(stack.isEmpty()) {
				System.out.println("yes");
			}else {
				System.out.println("no");
			}
			
			stack.clear();
		}
	}

}
