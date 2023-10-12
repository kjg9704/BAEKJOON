
public class Main {

	
	
	public static class Tree {
		Node root;
		int size;
		
		public Tree() {
			this.root = null;
			this.size = 0;
		}
		
		public void addNode(Node newNode) {
			
			if(root == null) {
				this.root = newNode;
				this.size = 1;
				return;
			}
			
			Node current = root;
			
			Node current_Parent = null;
			
			while(current != null) {
				current_Parent = current;
				
				if(current.value < newNode.value) {
					current = current.right;
				}else if(current.value > newNode.value) {
					current = current.left;
				}else {
					return;
				}
			}
			
			if(current_Parent.value < newNode.value) {
				current_Parent.right = newNode;
			}else {
				current_Parent.left = newNode;
			}
			
			size++;
			
		}
		
		public void deleteNode() {
			
		}
		
		public int size() {
			return this.size;
		}
		
		public void preOrder() {
			preOrder(root);
			System.out.println();
		}
		
		public void preOrder(Node now) {
			if(now == null) {
				return;
			}
			
			System.out.print(now.value + " ");
			preOrder(now.left);
			preOrder(now.right);
		}
		
		public void inOrder() {
			inOrder(root);
			System.out.println();
		}
		
		public void inOrder(Node now) {
			if(now == null) {
				return;
			}
			
			inOrder(now.left);
			System.out.print(now.value + " ");
			inOrder(now.right);
		}
		
		public void postOrder() {
			postOrder(root);
			System.out.println();
		}
		
		public void postOrder(Node now) {
			if(now == null) {
				return;
			}
			
			postOrder(now.left);
			postOrder(now.right);
			System.out.print(now.value + " ");
		}
	}
	
	public static class Node {
		
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
			
		}
		
		
		
	}
	public static void main(String[] args) {
		
		Tree tree = new Tree();
		
		tree.addNode(new Node(20));
		tree.addNode(new Node(14));
		tree.addNode(new Node(25));
		tree.addNode(new Node(10));
		tree.addNode(new Node(18));
		tree.addNode(new Node(23));
		
		System.out.println("------------preOder-------------");
		tree.preOrder();
		System.out.println("------------inOder-------------");
		tree.inOrder();
		System.out.println("------------postOder-------------");
		tree.postOrder();
	}

}
