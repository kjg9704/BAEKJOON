package boj_16506;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("ADD", "0000");
		map.put("SUB", "0001");
		map.put("MOV", "0010");
		map.put("AND", "0011");
		map.put("OR", "0100");
		map.put("NOT", "0101");
		map.put("MULT", "0110");
		map.put("LSFTL", "0111");
		map.put("LSFTR", "1000");
		map.put("ASFTR", "1001");
		map.put("RL", "1010");
		map.put("RR", "1011");
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			
			StringBuilder sb = new StringBuilder();
			String instruction = input[0];
			
			if(instruction.charAt(instruction.length() - 1) == 'C') {
				String inst = instruction.substring(0, instruction.length() - 1);
				String res = map.get(inst);
				res += "1";
				sb.append(res);
			}else {
				String res = map.get(instruction);
				res += "0";
				sb.append(res);
			}
			sb.append("0");
			
			int rd = Integer.parseInt(input[1]);
			sb.append(String.format("%3s", Integer.toBinaryString(rd)).replace(" ", "0"));
			
			int ra = Integer.parseInt(input[2]);
			sb.append(String.format("%3s", Integer.toBinaryString(ra)).replace(" ", "0"));
			
			int rb = Integer.parseInt(input[3]);
			
			if(sb.charAt(4) == '0') {
				sb.append(String.format("%3s", Integer.toBinaryString(rb)).replace(" ", "0"));
				sb.append("0");
			}else {
				sb.append(String.format("%4s", Integer.toBinaryString(rb)).replace(" ", "0"));
			}
			System.out.println(sb);
		}
	}

}
