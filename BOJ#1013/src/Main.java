import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			String temp = br.readLine();
			String pattern = "(100+1+|01)+";
			boolean flag = Pattern.matches(pattern, temp);
			if(flag) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}