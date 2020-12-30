import java.util.Scanner;
import java.util.Stack;
public class IronStick {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		Stack<String> stack = new Stack();
		int result = 0;
		for(int i = 0; i< str.length(); i++) {
			if(String.valueOf(str.charAt(i)).equals("(")) {
				stack.push(String.valueOf(str.charAt(i)));
			}
			else {
				if(stack.peek().equals("(") && str.charAt(i) != str.charAt(i - 1)) {
					stack.pop();
					result = result + stack.size();
				}
				else {
					stack.pop();
					result = result + 1;
				}
			}
		}
		System.out.println(result);
	}

}
