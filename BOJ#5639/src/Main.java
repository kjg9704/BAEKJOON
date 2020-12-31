import java.util.Scanner;

class Node{
	int data;
	Node left;
	Node right;
	Node(int data){
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

class BinarySearchTree{
	Node root;
	void postOrder(Node root){
		if(root != null) {
			postOrder(root.left);
			postOrder(root.right);
			System.out.println(root.data);
		}
	}
}

public class Main {
	static void selectNode(Node addNode, Node root) {
		if(addNode.data < root.data) {
			if(root.left == null) {
				root.left = addNode;
			}
			else {
				selectNode(addNode, root.left);
			}
		}
		else {
			if(root.right == null) {
				root.right = addNode;
			}
			else {
				selectNode(addNode, root.right);
			}
			
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int data = Integer.parseInt(sc.nextLine());
		BinarySearchTree tree = new BinarySearchTree();
		Node rootNode = new Node(data);
		tree.root = rootNode;
		while(sc.hasNextLine()) {
			data = Integer.parseInt(sc.nextLine());
			Node addNode = new Node(data);
			Node root = tree.root;
			selectNode(addNode, root);
		}
		tree.postOrder(tree.root);
		sc.close();
	}

}
