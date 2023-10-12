package boj_10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
push X: 정수 X를 큐에 넣는 연산이다.
pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 큐에 들어있는 정수의 개수를 출력한다.
empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
 */

public class Main2 {
	public static class Node{
		int value;
		Node nextNode;
		public Node(int val) {
			this.value = val;
			this.nextNode = null;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public Node getNext() {
			return this.nextNode;
		}
		
		public void setNext(Node nextNode) {
			this.nextNode = nextNode;
		}
	}
	
	public static class Queue{
		Node front;
		Node rear;
		int size = 0;
		
		public void push(int val) {
			Node newNode = new Node(val);
			if(isEmpty()) {
				this.front = newNode;
				this.rear = newNode;
			}
			else {
				this.rear.setNext(newNode);
				this.rear = newNode;
			}
			
			size++;
			
		}
		
		public int pop() {
			if(isEmpty()) {
				return -1;
			}
			
			int popVal = this.front.getValue();
			if(this.front == this.rear) {
				this.front = null;
				this.rear = null;
				
			}else {
				this.front = this.front.nextNode;
			}
			size--;
			return popVal;
		}
		
		public int peek() {
			if(isEmpty()) {
				return -1;
			}
			
			return front.getValue();
		}
		
		public int back() {
			if(isEmpty()) {
				return -1;
			}
			
			return rear.getValue();
		}
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			if(front == null && rear == null) {
				return true;
			}
			return false;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Queue que = new Queue();
        
		int cnt = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < cnt; i++) {
			String[] command = br.readLine().split(" ");
			switch(command[0]) {
			case "push":
				que.push(Integer.parseInt(command[1]));
				break;
				
			case "pop":
				bw.write(que.pop() + "\n");
				break;
				
			case "size":
				bw.write(que.size() + "\n");
				break;
			
			case "empty":
				if(que.isEmpty()) {
					bw.write("1\n");
				}else {
					bw.write("0\n");
				}
				break;
				
			case "front":
				bw.write(que.peek() + "\n");
				break;
			
			case "back":
				bw.write(que.back() + "\n");
				break;
			}		
		}
		bw.flush();
	}

}

