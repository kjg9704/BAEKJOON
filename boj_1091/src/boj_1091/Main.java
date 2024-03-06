package boj_1091;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[] P;
	static int[] S;
	static int[] start;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		P = new int[N];
		start = new int[N];
		for(int i = 0 ; i < N; i++) {
			P[i] = Integer.parseInt(input[i]);
			start[i] = P[i];
		}
		
		input = br.readLine().split(" ");
		S = new int[N];
		for(int i = 0 ; i < N; i++) {
			S[i] = Integer.parseInt(input[i]);
		}
		
		int cnt = 0;

		while(!check(P)) {
			P = shuffle(P);

			cnt++;
			boolean cycle = true;
			for(int i = 0; i < N; i++) {
				if(P[i] != start[i]) {
					cycle = false;
					break;
				}
			}
			
			if(cycle) {
				cnt = -1;
				break;
			}
			
		}
		
		System.out.println(cnt);
		
			
	}
	
	static int[] shuffle(int[] arr) {
		int[] new_arr = new int[N];
		
		for(int i = 0; i < N; i++) {
			int tmp = arr[i];
			new_arr[S[i]] = tmp;
		}
		return new_arr;
		
	}
	

	static boolean check(int[] P) {
		for(int i = 0; i < N; i++) {
			if(P[i] != i % 3) {
				return false;
			}
		}
		
		return true;
	}

}
