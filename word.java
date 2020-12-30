import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class word {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = bf.readLine();
		Stack<String> stack = new Stack();
		for(int i = 0; i < str.length(); i++) {
			if(String.valueOf(str.charAt(i)).equals("<")) {
				if(!stack.isEmpty()) {
					while(!stack.isEmpty()) {
						System.out.print(stack.pop());
					}
				}
				int k = i;
				System.out.print(String.valueOf(str.charAt(k)));
				while(!String.valueOf(str.charAt(k)).equals(">")) {
				k++;
				System.out.print(str.charAt(k));
				}
				i = k;
			}
			else {
				if(String.valueOf(str.charAt(i)).equals(" ")) {
					while(!stack.isEmpty()) {
						System.out.print(stack.pop());
					}
					System.out.print(" ");
				}
				else {
					stack.push(String.valueOf(str.charAt(i)));
				}
			}
			
		}
		while(!stack.isEmpty()) {
			System.out.print(stack.pop());
		}
		}
}
