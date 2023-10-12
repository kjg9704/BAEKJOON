package boj_10845;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
push X: ���� X�� ť�� �ִ� �����̴�.
pop: ť���� ���� �տ� �ִ� ������ ����, �� ���� ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
size: ť�� ����ִ� ������ ������ ����Ѵ�.
empty: ť�� ��������� 1, �ƴϸ� 0�� ����Ѵ�.
front: ť�� ���� �տ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
back: ť�� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
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

