package boj_1062;

import java.io.*;
import java.util.*;

public class Main {

	static int N, K, result;
	static String[] words;
	
	static int max = 0;
	static String chars = "abcdefghijklmnopqrstuvwxyz";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		boolean[] teach = new boolean[26];
		words = new String[N];
		for(int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		teach[0] = true;
		teach[2] = true;
		teach[8] = true;
		teach[13] = true;
		teach[19] = true;
		
		if(K < 5) {
			System.out.println("0");
		}else {
			backtracking(teach, 0, 5);
			System.out.println(max);
		}
		
		
		
		
	}
	
	static void backtracking(boolean[] teach, int index, int cnt) {
		if(cnt == K) {
			int read = 0;
			for(int i = 0; i < N; i++) {
				if(check(teach, words[i])) {
					read++;
				}
			}
			
			max = Math.max(max, read);
			return;
		}
		
		
		for(int i = index; i < 26; i++) {
			if(!teach[i]) {
				teach[i] = true;
				backtracking(teach, i, cnt + 1);
				teach[i] = false;
			}
		}
	}
	
	static boolean check(boolean[] teach, String word) {
		for(int i = 0; i < word.length(); i++) {
			if(!teach[word.charAt(i) - 0x61]) {
				return false;
			}
		}
		
		return true;
	}

}
