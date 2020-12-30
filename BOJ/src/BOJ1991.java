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
		System.out.println("-----����Ʈ��-----");
		tree2.preOrder(tree2.root); */
	/*	���� 4 - ���̳ʸ�Ʈ�� �ؽ��� ����, Ű���� ���̳ʸ�Ʈ��, �������� ������ ��
		HashMap<binaryTree t, int sum> - �䷱������.
	      �ΰ��� ���̳ʸ�Ʈ���� ������ ���Ҽ��ֵ��� ¥�� ����񱳿��� equals ����� ����尡 �Ȱ��� Ʈ�� ���� ���ƾߴ�. �̶� hashCode�� �������̵� ����
	      ���� �ؽ��ڵ带 �������̵� ���� �ʾ����� ����� �������� ���̰�, �� ������ �ذ��ϱ����� hashCode�� �������̵��ض�. */
	}

}
