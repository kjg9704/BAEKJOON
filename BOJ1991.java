import java.util.Scanner;

class Node{
	char data;
	Node left;
	Node right;
	public Node(Node left, char data, Node right) {
		this.left = left;
		this.data = data;
		this.right = right;
	}
}
class BinTree{
	public Node root;
	public void createNode(char left, char data, char right) {
		if(root != null) {
			selectNode(root, left, data, right);
		}
		else {
			root = new Node(null, data, null);
			if(left != '.') {
				root.left = new Node(null, left, null);	
			}
			if(right != '.') {
				root.right = new Node(null, right, null);
			}
		}
		}
	public void selectNode(Node root, char left, char data, char right) {
		if(root != null) {
			if(root.data == data) {
				if(left != '.') {
					root.left = new Node(null, left, null);
				}
				if(right != '.') {
					root.right = new Node(null, right, null);
				}
			}
			else {
				selectNode(root.left, left, data, right);
				selectNode(root.right, left, data, right);
			}
		}
		else {
			return;
		}
	}
	public void preOrder(Node node) {
		if(node != null) {
        System.out.print(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
    }
	public void inOrder(Node node) {
		if(node != null) {
        inOrder(node.left);
        System.out.print(node.data);
        inOrder(node.right);
    }
    }
	public void postOrder(Node node) {
		if(node != null) {
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data);
    }
    }
}

public class BOJ1991 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BinTree tree = new BinTree();
		int num = Integer.parseInt(sc.nextLine());
		for(int i = 0; i < num; i++) {
			String s = sc.nextLine();
			s = s.replace(" ", "");
			tree.createNode(s.charAt(1), s.charAt(0), s.charAt(2));
		}
		tree.preOrder(tree.root);
		System.out.println("");
		tree.inOrder(tree.root);
		System.out.println("");
		tree.postOrder(tree.root);
	/*	BinTree tree2 = new BinTree(tree);
		System.out.println("-----복사트리-----");
		tree2.preOrder(tree2.root); */
	/*	과제 4 - 바이너리트리 해쉬맵 만듬, 키값은 바이너리트리, 나머지는 노드들의 합
		HashMap<binaryTree t, int sum> - 요런식으로.
	      두개의 바이너리트리가 같은지 비교할수있도록 짜라 동등비교연산 equals 만드셈 각노드가 똑같고 트리 위상도 같아야댐. 이때 hashCode는 오버라이딩 안함
	      그후 해쉬코드를 오버라이딩 하지 않았을때 생기는 문제점을 보이고, 이 문제를 해결하기위해 hashCode를 오버라이딩해라. */
	}

}
