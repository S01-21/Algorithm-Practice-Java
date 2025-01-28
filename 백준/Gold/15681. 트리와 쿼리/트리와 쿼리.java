import java.util.*;
import java.io.*;

public class Main {
	static int N, R, Q;
	static int[] note;
	static boolean[] vis;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, adjList[a]);
			adjList[b] = new Node(a, adjList[b]);
		}
		vis = new boolean[N+1];
		note = new int[N+1];
		
		vis[R] = true;
		dfs(R);
		
		for (int i = 0; i < Q; i++) {
			sb.append(note[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	
	private static void dfs(int cur) {
		if (adjList[cur] == null) {
			note[cur] = 1;
			return;
		}
		for (Node nxt = adjList[cur]; nxt != null; nxt = nxt.next) {
			if (vis[nxt.num])	continue;
			vis[nxt.num] = true;
			dfs(nxt.num);
			note[cur] += note[nxt.num];
		}
		note[cur] += 1;
	}
}
