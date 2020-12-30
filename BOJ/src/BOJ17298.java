import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
public class BOJ17298 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack();
		int num = Integer.parseInt(bf.readLine());
		int [] result = new int[num];
		int [] list = new int[num];
		int index = 0;
		String [] str = bf.readLine().split(" ");
		for(int i = 0; i < num; i++) {
			list[i] = Integer.parseInt(str[i]);
		}
		for(int i = 0; i < num; i++) {
			result[i] = -1;
		}
		for(int i = 0; i< num; i++) {
			if(stack.isEmpty()) {
				stack.push(list[i]);
			}
			else if(!stack.isEmpty()) {
				if(stack.peek() < list[i]) {
					result[i - 1] = list[i];
					stack.pop();
					while(!stack.isEmpty()) {
						if(stack.peek() < list[i]) {
							index--;
							result[index] = list[i];
							stack.pop();
						}
						else {
							index++;
							break;
						}
					}
					stack.push(list[i]);
					index++;
				}
				else {
					stack.push(list[i]);
					index++;
				}
			}
		}
		for(int i = 0; i < num; i++) {
			if(i == num - 1) {
				System.out.println(result[i]);
			}
			else {
				System.out.print(result[i] + " ");
			}
		}

	}

}
