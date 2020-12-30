import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10430 {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String [] str1 = bf.readLine().split(" ");
		int A = Integer.parseInt(str1[0]);
		int B = Integer.parseInt(str1[1]);
		int C = Integer.parseInt(str1[2]);
		System.out.println((A+B)%C);
		System.out.println(((A%C) + (B%C))%C);
		System.out.println((A*B)%C);
		System.out.println(((A%C) * (B%C))%C);
		
	}

}
