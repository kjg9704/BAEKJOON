import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		int L = Integer.parseInt(temp[0]);
		int C = Integer.parseInt(temp[1]);
		temp = br.readLine().split(" ");
		char[] arr = new char[C];
		for(int i = 0; i < C; i++) {
			arr[i] = temp[i].charAt(0);
		}
		Arrays.sort(arr);
		boolean[] visited = new boolean[C];
		combi(arr, visited, 0, C, L);
	}
	static void combi(char[] arr, boolean[] visited, int depth, int n, int r) {
        if(r == 0) {
        	if(check(arr, visited)) {
        		StringBuilder sb = new StringBuilder();
            	for(int i = 0; i < n; i++) {
            		if(visited[i] == true) {
            			sb.append(arr[i]);
            		}
            	}
            	System.out.println(sb.toString());
        	}
            return;
        }
        if(depth == n) {
            return;
        } else {
            visited[depth] = true;
            combi(arr, visited, depth + 1, n, r - 1);
 
            visited[depth] = false;
            combi(arr, visited, depth + 1, n, r);
        }
    }
	
	static boolean check(char[] arr, boolean[] visited) {
		int vowel = 0;
		int consonant = 0;
		for(int i = 0; i < arr.length; i++) {
			if(visited[i] == true) {
				if(arr[i] == 'a' || arr[i] == 'i' || arr[i] == 'e' || arr[i] == 'o' || arr[i] == 'u') {
					vowel++;
				}else {
					consonant++;
				}
			}
		}
		if(vowel >= 1 && consonant >= 2) {
			return true;
		}else {
			return false;
		}
	}

}
