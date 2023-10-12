package boj_10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
push X: 정수 X를 큐에 넣는 연산이다.
pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 큐에 들어있는 정수의 개수를 출력한다.
empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
		Queue<Integer> que = new LinkedList<>();
		int cnt = Integer.parseInt(br.readLine());
		int element = -1;
		
		for(int i = 0; i < cnt; i++) {
			String[] command = br.readLine().split(" ");
			switch(command[0]) {
			case "push":
				element = Integer.parseInt(command[1]);
				que.offer(element);
				break;
				
			case "pop":
				if(que.isEmpty()) {
					bw.write("-1" + "\n");
				}else {
					bw.write(que.poll() + "\n");
					if(que.isEmpty()) {
						element = -1;
					}
				}
				break;
				
			case "size":
				bw.write(que.size() + "\n");
				break;
			
			case "empty":
				if(que.isEmpty()) {
					bw.write("1" + "\n");
				}else {
					bw.write("0" + "\n");
				}
				break;
				
			case "front":
				if(que.isEmpty()) {
					bw.write("-1" + "\n");
				}else {
					bw.write(que.peek() + "\n");

				}
				break;
			
			case "back":
				bw.write(Integer.toString(element) + "\n");

			}		
		}
		bw.flush();
		
	}

}
