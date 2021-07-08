import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = br.readLine();
		String pattern = "(100+1+|01)+";
		boolean flag = Pattern.matches(pattern, temp);
		if(flag) {
			System.out.println("SUBMARINE");
		}else {
			System.out.println("NOISE");
		}
		
	}

}
