package boj_10828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
push X: ���� X�� ���ÿ� �ִ� �����̴�.
pop: ���ÿ��� ���� ���� �ִ� ������ ����, �� ���� ����Ѵ�. ���� ���ÿ� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
size: ���ÿ� ����ִ� ������ ������ ����Ѵ�.
empty: ������ ��������� 1, �ƴϸ� 0�� ����Ѵ�.
top: ������ ���� ���� �ִ� ������ ����Ѵ�. ���� ���ÿ� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 */
public class Main {
	
	public static class Node{
		int value;
		Node nextNode;
		
		public Node(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public void setNext(Node nextNode) {
			this.nextNode = nextNode;
		}
		
		public Node getNext() {
			return this.nextNode;
		}
		
	}
	
	public static class Stack{
		Node top;
		int size;
		
		public void push(int value) {
			Node newNode = new Node(value);
			
			if(isEmpty()) {
				this.top = newNode;
			}else {
				newNode.setNext(this.top);
				this.top = newNode;
			}
			
			size++;
		}
		
		public int pop() {
			if(isEmpty()) {
				return -1;
			}
			int popValue = this.top.getValue();
			this.top = this.top.getNext();
			size--;
			return popValue;
		}
		
		public int size() {
			return size;
		}
		
		public boolean isEmpty() {
			if(this.top == null) {
				return true;
			}
			return false;
		}
		
		public int top() {
			if(isEmpty()) {
				return -1;
			}
			return this.top.getValue();
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack stack = new Stack();
        int cnt = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < cnt; i++) {
        	String[] command = br.readLine().split(" ");
        	
        	switch(command[0]) {
        	case "push":
        		stack.push(Integer.parseInt(command[1]));
        		break;
        		
        	case "pop":
        		bw.write(stack.pop() + "\n");
        		break;
        	
        	case "size":
        		bw.write(stack.size() + "\n");
        		break;
        		
        	case "empty":
        		if(stack.isEmpty()) {
        			bw.write("1\n");
        		}else {
        			bw.write("0\n");
        		}
        		break;
        		
        	case "top":
        		bw.write(stack.top() + "\n");
        		break;
        	}
        }
        
        bw.flush();
	}

}
