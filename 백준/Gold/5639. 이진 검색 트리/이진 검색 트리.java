import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static BSTree tree;
	static class Node{
		int num;
		Node left, right;
		Node(int num){
			this.num = num;
		}
	}
	static class BSTree{
		Node root;
		void insert(int x) {
			root = insertElem(root, x);
		}
		Node insertElem(Node node, int x) {
			if (node == null) {
				return new Node(x);
			}
			if (x < node.num) {
				node.left = insertElem(node.left, x);
			} else {
				node.right = insertElem(node.right, x);
			}
			
			return node;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
		
		tree = new BSTree();
		String str;
		while ((str = br.readLine()) != null && !str.isEmpty()) {
			tree.insert(Integer.parseInt(str));
		}
		
		postOrder(tree.root);
		
		System.out.println(sb);
		br.close();
	}
	
	private static void postOrder(Node node) {
		if (node == null)	return;
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.num).append("\n");
	}
}
