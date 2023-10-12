package boj_1620;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	static class Node{
		String name;
		int num;
		
		public Node(String name, int num) {
			this.name = name;
			this.num = num;
		}
	}
	static int [] dict = new int[100001];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		HashMap<String, Integer> map = new HashMap<>();
		String[] command = br.readLine().split(" ");
		int N = Integer.parseInt(command[0]);
		int M = Integer.parseInt(command[1]);
		String [] names = new String[N+1];
		
		for(int i = 0; i < N; i++) {
			String name = br.readLine();
			map.put(name, i + 1);
			names[i + 1] = name;
		}
		
		for(int i = 0; i < M; i++) {
			String cmd = br.readLine();
			try {
				int dicNum = Integer.parseInt(cmd);
				bw.write(names[dicNum] + "\n");
			}catch(Exception e) {
				
				bw.write(map.get(cmd) + "\n");
			}
		}
		bw.flush();
	}

}
