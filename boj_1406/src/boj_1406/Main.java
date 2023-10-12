package boj_1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack1 = new Stack<>();
		Stack<Character> stack2 = new Stack<>();

		String str = br.readLine();
		for(int i = 0; i < str.length(); i++) {
			stack1.push(str.charAt(i));
		}
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			String command = br.readLine();
			char cmd = command.charAt(0);
			
			if(cmd == 'L') {
				if(!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			}else if(cmd == 'D') {
				if(!stack2.isEmpty()) {
					stack1.push(stack2.pop());
				}
			}else if(cmd == 'B') {
				if(!stack1.isEmpty()) {
					stack1.pop();
				}
			}else {
				stack1.push(command.charAt(2));
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		
		while(!stack2.isEmpty()) {
			sb.append(stack2.pop());
		}
		
		System.out.println(sb);
		
	}

}
