import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			String[] input = br.readLine().split(" ");
			
			int Sm = Integer.parseInt(input[0]);
			int Sc = Integer.parseInt(input[1]);
			int Si = Integer.parseInt(input[2]);
			
			short[] arr = new short[Sm];
			
			String inst = br.readLine();
			String str = br.readLine();
			int ptr = 0;
			int str_ptr = 0;
			int inst_ptr = 0;
			Stack<Integer> stack = new Stack<>();
			int now_loop = 0;
			int cnt = 0;
			boolean inf_loop = false;
			int loop_start = 0;
			int loop_end = 0;
			int[] loop_pair = new int[Sc];
			
			for(int i = 0; i < Sc; i++) {
				char now = inst.charAt(i);
				if(now == '[') {
					stack.add(i);
				}else if(now == ']') {
					int start = stack.pop();
					loop_pair[start] = i;
					loop_pair[i] = start;
				}
			}

			while(inst_ptr < Sc) {
				if(inf_loop && cnt >= 50000000) break;
				char now = inst.charAt(inst_ptr);
				cnt++;
				switch(now) {
				case '-':
					arr[ptr]--;
					if(arr[ptr] == -1) arr[ptr] = 255;
					inst_ptr++;
					break;
					
				case '+':
					arr[ptr] = (short) ((arr[ptr] + 1) % 256);
					inst_ptr++;
					break;
					
				case '<':
					ptr--;
					if(ptr == -1) ptr = Sm - 1;
					inst_ptr++;
					break;
					
				case '>':
					ptr++;
					if(ptr == Sm) ptr = 0;
					inst_ptr++;
					break;
					
				case ',':
					if(str_ptr >= Si) {
						arr[ptr] = 255;
					}else {
						arr[ptr] = (short) str.charAt(str_ptr);
						str_ptr++;
					}
					inst_ptr++;
					break;
					
				case '[':
					if(arr[ptr] == 0) {
						inst_ptr = loop_pair[inst_ptr];
					}else {
						inst_ptr++;
					}
					break;
					
				case ']':
					if(arr[ptr] != 0) {
						int next = loop_pair[inst_ptr];
						if(cnt >= 50000000 && !inf_loop) {
							inf_loop = true;
							cnt = 0;
						}else if(inf_loop) {
							if(loop_end < inst_ptr) {
								loop_start = next;
								loop_end = inst_ptr;
							}
						}
						inst_ptr = next + 1;
					}else {
						inst_ptr++;
					}
					break;
				default:
					inst_ptr++;
					break;
					
				}
			}
			
			if(inf_loop) {
				System.out.println("Loops " + loop_start + " " + loop_end);
			}else {
				System.out.println("Terminates");
			}

		}

	}

}
