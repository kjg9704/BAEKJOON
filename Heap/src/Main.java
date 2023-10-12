import java.util.*;

public class Main {

	
	public static class Heap{
		int[] arr;
		int size;
		int max;
		
		public Heap(int N) {
			this.max = N;
			this.size = 0;
			this.arr = new int[this.max];
		}
		
		
		
		public void add(int value) {
			if(size >= max - 1) {
				expand();
			}
			
			int idx = size;
			arr[idx] = value;
			size++;
			
			if(idx >= 1) {
				int parent = getParent(idx);
				
				while(arr[parent] > arr[idx]) {
					swap(parent, idx);
					idx = parent;
					if(idx == 0) {
						break;
					}
					parent = getParent(idx);
				}
			}
		}
		
		public int delete() {
			int delete_Value = 0;
			if(size == 0) {
				return 0;
			}
			
			delete_Value = arr[0];
			int last = arr[size - 1];
			size--;
			
			arr[0] = last;
			
			int idx = 0;
			int left = idx * 2 + 1;
			int right = idx * 2 + 2;
			int min = idx;
			while(true) {
				if(left <= size && arr[min] > arr[left]) {
					min = left;
				}
				if(right <= size && arr[min] > arr[right]) {
					min = right;
				}
				
				if(min != idx) {
					swap(min, idx);
					idx = min;
					left = idx * 2 + 1;
					right = idx * 2 + 2;
				}else {
					break;
				}
			}
			
			
			
			return delete_Value;
		}
		
		
		public int getParent(int index) {
			int parent;
			if(index % 2 == 1) {
				parent = index / 2;
			}else {
				parent = index / 2 - 1;
			}
			
			return parent;
		}
		public void expand() {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
			max = max * 2;
			int[] new_arr = new int[max];
			for(int i = 0; i < arr.length; i++) {
				new_arr[i] = arr[i];
			}
			this.arr = new_arr;
		}
		
		public void swap(int idx1, int idx2) {
			int temp = arr[idx1];
			arr[idx1] = arr[idx2];
			arr[idx2] = temp;
		}
		
		public void print() {
			for(int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
	
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = Integer.parseInt(sc.nextLine());
		Heap heap = new Heap(N);
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(sc.nextLine());
			if(num == 0) {
				System.out.println(heap.delete());
			}else {
				heap.add(num);
			}
		}
		sc.close();
		/*
		
		heap.add(15);
		heap.add(10);
		heap.add(20);
		heap.add(5);
		heap.add(17);
		heap.add(11);
		
		heap.print();
		int size = heap.size;
		for(int i = 0; i < size; i++) {
			System.out.println(heap.delete());
		}
		
		*/
		
	}

}
