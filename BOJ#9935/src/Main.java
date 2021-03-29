import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String bomb = br.readLine();
		br.close();
		Stack<String> stack = new Stack<>();
		stack.add(str);
		while(!stack.isEmpty()) {
			String next = stack.peek().replace(bomb, "");
			if(next.equals(stack.pop())) {
				if(next.length() == 0) {
					System.out.println("FRULA");
				}else {
					System.out.println(next);
				}
			}else {
				stack.push(next);
			}
		}
		
	}

}
