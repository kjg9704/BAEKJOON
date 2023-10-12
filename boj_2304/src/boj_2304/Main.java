package boj_2304;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		int[] arr = new int[1001];
		int start = 1001;
		int end = 0;
		String[] temp;
		Stack<Integer> stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			temp = sc.nextLine().split(" ");
			
			int L = Integer.parseInt(temp[0]);
			int H = Integer.parseInt(temp[1]);
			
			arr[L] = H;
			
			start = Math.min(start, L);
			end = Math.max(end, L);
		}
		
		
		int pillar = arr[start];
		for(int i = start + 1; i <= end; i++) {
			if(arr[i] < pillar) {
				stack.push(i);
			}else {
				while(!stack.isEmpty()) {
					int idx = stack.pop();
					arr[idx] = pillar;
				}
				pillar = arr[i];
			}
		}
		stack.clear();
		pillar = arr[end];
		for(int i = end - 1; i >= start; i--) {
			if(arr[i] < pillar) {
				stack.push(i);
			}else {
				while(!stack.isEmpty()) {
					int idx = stack.pop();
					arr[idx] = pillar;
				}
				pillar = arr[i];
			}
		}
		
		int result = 0;
		for(int i = start; i <= end; i++) {
			result += arr[i];
		}
		
		System.out.println(result);
	}

}
