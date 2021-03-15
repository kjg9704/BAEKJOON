import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		sc.close();
		int count = 0;
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < str.length(); i++) {
			char temp = str.charAt(i);
			if(stack.contains(temp)) {
				if(stack.peek() == temp) {
					stack.pop();
				}else {
					int index = stack.indexOf(temp);
					count += stack.size() - 1 - index;
					stack.removeElementAt(index);
				}
			}else {
				stack.push(temp);
			}

		}
		System.out.println(count);
	}
}
