import java.util.Scanner;

class Node{
	int data;
	Node left;
	Node right;
	public Node(Node left, int data, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
}

class CBTree{
	Node root;
	public CBTree() {
		root = null;
	}
	void init(int n) {
		
	}
	Node createNode(int data) {
		Node newNode = new Node(null, data, null);
		return newNode;
	}
	void preOrder(Node root) {
		System.out.println(root.data);
		preOrder(root.left);
		preOrder(root.right);
	}
}
public class BOJ3038 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		CBTree tree = new CBTree();
		tree.init(num);
		tree.preOrder(tree.root);
	}
}
