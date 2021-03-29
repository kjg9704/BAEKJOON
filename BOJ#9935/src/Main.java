import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		String bomb = br.readLine();
		br.close();
		Stack<Character> stack = new Stack<>();
		int bombLen = bomb.length();
		for(int i = 0; i < str.length; i++) {
			stack.add(str[i]);
			if(stack.size() >= bombLen) {
				boolean check = true;
				for(int j = 0; j < bombLen; j++) {
					if(!stack.get(stack.size() - bombLen + j).equals(bomb.charAt(j))) {
						check = false;
						break;
					}
				}
				if(check) {
					for(int j = 0; j < bombLen; j++) {
						stack.pop();
					}
				}
			}
		}
		if(stack.size() == 0) {
			System.out.println("FRULA");
		}else {
			StringBuilder sb = new StringBuilder();
			for(char c : stack) {
				sb.append(c);
			}
			System.out.println(sb);
		}
	}

}
