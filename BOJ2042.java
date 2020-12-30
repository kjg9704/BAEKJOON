import java.util.Scanner;

class SegTree{
	long arr[];
	long tree[];
	public SegTree(int n) {
		arr = new long[n];
		tree = new long[n * 4];
	}
	long init(int start, int end, int node) {
		if(start == end) {
			return tree[node] = arr[start];
		}
		int mid = (start + end) / 2;
		return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
	}
	long sum(int start, int end, int node, int left, int right) {
		if(left > end || start > right) {
			return 0;
		}
		if(start >= left && end <= right) {
			return tree[node];
		}
		int mid = (start + end) / 2;
		return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right); 
	}
	void update(int start, int end, int node, int index, long change) {
		if(start > index || end < index) {
			return;
		}
		if(start == end) {
			tree[node] += change;
			return;
		}
		if(start <= index && end >= index) {
			tree[node] += change;
			int mid = (start + end) / 2;
			update(start, mid, node * 2, index, change);
			update(mid + 1, end, node * 2 + 1, index, change);
		}
	}
}
public class BOJ2042 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		SegTree tree = new SegTree(n);
		for(int i = 0 ; i < n; i++) {
			tree.arr[i] = sc.nextLong();
		}
		tree.init(0, n - 1, 1);
		for(int i = 0; i < m + k; i++) {
			int app = sc.nextInt();
			int num1 = sc.nextInt() - 1;
			long num2 = sc.nextLong();
			if(app == 1) {
				long temp = num2;
				num2 = num2 - tree.arr[num1];
				tree.arr[num1] = temp;
				tree.update(0, n - 1, 1, num1, num2);
			}
			else {
				num2 = num2 - 1;
				System.out.println(tree.sum(0, n - 1, 1, num1, (int)num2));
			}
		}
	}

}
