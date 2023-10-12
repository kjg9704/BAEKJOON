package boj_10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
push X: ���� X�� ť�� �ִ� �����̴�.
pop: ť���� ���� �տ� �ִ� ������ ����, �� ���� ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
size: ť�� ����ִ� ������ ������ ����Ѵ�.
empty: ť�� ��������� 1, �ƴϸ� 0�� ����Ѵ�.
front: ť�� ���� �տ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
back: ť�� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
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
