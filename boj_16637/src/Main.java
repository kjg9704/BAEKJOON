import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int max = Integer.MIN_VALUE;
	static String str;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		str = br.readLine();
		
		dfs(str.charAt(0) - 0x30, 1);
		System.out.println(max);
	}
	
	static void dfs(int calc, int index) {
		if(index >= N) {
			max = Math.max(max, calc);
			return;
		}
		
		int sum = calc_func(str.charAt(index), calc, str.charAt(index + 1) - 0x30);
		dfs(sum, index + 2);
		
		if(index + 3 < N) {
			sum = calc_func(str.charAt(index + 2), str.charAt(index + 1) - 0x30, str.charAt(index + 3) - 0x30);
			sum = calc_func(str.charAt(index), calc, sum);
			dfs(sum, index + 4);
		}
	}
	
	static int calc_func(char op, int operand1, int operand2) {
		switch(op) {
		case '+':
			return operand1 + operand2;
		case '-':
			return operand1 - operand2;
		default:
			return operand1 * operand2;
		}
	}

}
