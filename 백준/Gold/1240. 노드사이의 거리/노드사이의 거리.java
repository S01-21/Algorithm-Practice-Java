import java.util.*;
import java.io.*;

// 골드5. 노드사이의 거리
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int res;
	static boolean[] vis;
	static Node[] tree;
	static class Node{
		int num, dist;
		Node next;
		public Node(int num, int dist, Node next) {
			super();
			this.num = num;
			this.dist = dist;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", dist=" + dist + ", next=" + next + "]";
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 노드 개수 
		M = Integer.parseInt(st.nextToken());	// 거리 알고싶은 노드 쌍 개수
		tree = new Node[N+1];
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			tree[from] = new Node(to, dist, tree[from]);
			tree[to] = new Node(from, dist, tree[to]);
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			res = 0;
			vis = new boolean[N+1];
			vis[a] = true;
			
			findDist(a, b, 0);
			
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void findDist(int a, int b, int sum) {
		for (Node tmp = tree[a]; tmp != null; tmp = tmp.next) {
			if (vis[tmp.num]) continue;
			if (tmp.num == b) {
				res = sum + tmp.dist;
				return;
			}
			vis[tmp.num] = true;
			findDist(tmp.num, b, sum + tmp.dist);
		}
		
	}
}