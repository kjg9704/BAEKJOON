
public class main {

	public static class Node<E>{
		E data;
		Node<E> next;
		
		public Node(E data) {
			this.data = data;
			this.next = null;
		}
	}
	
	public static class List<E>{
		Node<E> head;
		Node<E> tail;
		int size = 0;
		
		public List() {
			this.head = null;
			this.tail = null;
			this.size = 0;
		}
		
		public Node<E> search(int index) {
			if(index < 0 || index >= size) {
				return null;
			}
			
			Node<E> now = head;
			for(int i = 0; i < index; i++) {
				now = now.next;
			}
			return now;
			
		}
		
		public void add(E data) {
			Node<E> newNode = new Node<>(data);
			if(tail == null) {
				this.head = newNode;
				this.tail = newNode;
			}else if(head == tail){
				this.head.next = newNode;
				this.tail = newNode;
			}else {
				this.tail.next = newNode;
				this.tail = newNode;
			}
			
			this.size++;
		}
		
		public void remove(int index) {
			if(index < 0 || index >= size) {
				return;
			}
			
			if(index == 0) {
				this.head = head.next;
				size--;
				return;
			}
			
			Node<E> prev = search(index - 1);
			Node<E> remove_node = prev.next;
			
			prev.next = remove_node.next;
			
			if(prev.next == null) {
				this.tail = prev;
			}
			
			size--;
		}
		
		public E get(int index) {
			if(index < 0 || index >= size) {
				return null;
			}
			
			return search(index).data;
		}
		
		public void set(int index, E data) {
			if(index < 0 || index >= size) {
				return;
			}
			
			Node<E> newNode = new Node<>(data);
			
			Node<E> prev = search(index - 1);
			Node<E> now = search(index);
			
			prev.next = newNode;
			
			newNode.next = now.next;
			if(newNode.next == null) {
				this.tail = newNode;
			}
			
		}
		
		public int size() {
			return this.size;
		}
		public void print() {
			Node<E> now = head;
			while(now != null) {
				System.out.print(now.data + " ");
				now = now.next;
			}
			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		int[] arr = new int[10];
//		for(int i = 0; i < 10; i++) {
//			arr[i] = (int) (Math.random() * 100);
//		}

		
		
		List<Integer> list = new List<>();
		
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.add(5);
		list.add(6);
		list.remove(0);
		list.print();
		
		System.out.println(list.get(0));
		System.out.println(list.size());

		
		
		
	}

}
