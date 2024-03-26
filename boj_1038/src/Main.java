import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();
		
		ArrayList<Long> list = new ArrayList<Long>();
		
		if(N <= 9) {
			System.out.println(N);
		}else {
			for(int i = 0; i <= 9; i++) {
				dfs(list, (long)i);
			}
			
			Collections.sort(list);
			
			if(list.size() > N) {
				System.out.println(list.get(N));
			}else {
				System.out.println(-1);
			}
		}
		
	}
	
	static void dfs(ArrayList<Long> list, long num) {
		
		if(num > Long.parseLong("9876543210")) {
			return;
		}
		
		list.add(num);
		for(int i = 0; i <= 9; i++) {
			if(num % 10 > i) {
				dfs(list, (num * 10) + i);
			}
		}
		
	}


}