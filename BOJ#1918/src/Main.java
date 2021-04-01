import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String infix = br.readLine();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('(', 0);
		map.put('+', 1);
		map.put('-', 1);
		map.put('*', 2);
		map.put('/', 2);
		for(int i = 0; i < infix.length(); i++) {
			char now = infix.charAt(i);
			switch(now) {
			case '(':
				stack.push(now);
				break;
			case ')':
				while(stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop();
				break;
			case '+':
				if(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
					while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
						sb.append(stack.pop());
					}
					stack.push(now);
				}else {
					stack.push(now);
				}
				break;
			case '-':
				if(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
					while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
						sb.append(stack.pop());
					}
					stack.push(now);
				}else {
					stack.push(now);
				}
				break;
			case '*':
				if(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
					while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
						sb.append(stack.pop());
					}
					stack.push(now);
				}else {
					stack.push(now);
				}
				break;
			case '/':
				if(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
					while(!stack.isEmpty() && map.get(stack.peek()) >= map.get(now)) {
						sb.append(stack.pop());
					}
					stack.push(now);
				}else {
					stack.push(now);
				}
				break;
			default:
				sb.append(now);
				break;
			}
		}
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}

}
