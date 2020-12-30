import java.util.Scanner;
import java.util.Stack;
public class Vps {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Stack<Character> stack = new Stack();
		int number = sc.nextInt();
		String [] sen = new String[number];
		sc.nextLine();
		for(int i = 0; i < number;i++) {
			sen[i] = sc.nextLine();
		}
		for(int j = 0; j <number; j++) {
			boolean vps = true;
			for(int k = 0; k < sen[j].length(); k++ ) {
				char c = sen[j].charAt(k);
				if(c == '(') {
					stack.push('(');
				}
				else {
					if(stack.isEmpty()) {
                        vps = false;
                        break;
                    }
					stack.pop();
				}
			}
			if(vps && stack.isEmpty()) {
				vps = true;
			}
			else {
				vps = false;
				stack.clear();
			}
			if(vps == true) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}

}