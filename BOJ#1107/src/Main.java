import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static boolean[] button;
	static int result;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int target = Integer.parseInt(br.readLine());
		int broken = Integer.parseInt(br.readLine());
		int start = 100;
		button = new boolean[10];
		if(broken > 0) {
			String[] temp = br.readLine().split(" ");
			for(int i = 0; i < broken; i++) {
				int but = Integer.parseInt(temp[i]);
				button[but] = true; 
			}
		}
		result = Math.abs(target - start);
		if(target == start || broken == 10) {
			System.out.println(Math.abs(target - start));
			return;
		}
		
		for(int i = 0; i <= 1000000; i++) {
			int count = check(i);
			if(count > 0) {
				result = Math.min(result, Math.abs(target - i) + count);
			}
		}
		System.out.println(result);
		
	}
	static int check(int num) {
		int count = 0;
		if(num == 0) {
			if(button[num]) {
				return 0;
			}else {
				return 1;
			}
		}
		while(num > 0) {
			if(button[num % 10]) {
				return 0;
			}
			count++;
			num = num / 10;
		}
		
		return count;
	}


}
