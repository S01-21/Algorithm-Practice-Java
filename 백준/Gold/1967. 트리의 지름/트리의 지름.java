import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, res;
	static boolean[] check, vis;
	static class Node {
		int num, weight;
		Node next;
		public Node(int num, Node next, int weight) {
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
	}
	static Node[] adjList;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjList = new Node[N+1];
		check = new boolean[N+1];
		StringTokenizer st = null;
		for (int i = 0 ;i  < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			check[from] = true;
			adjList[from] = new Node(to, adjList[from], weight);
			adjList[to] = new Node(from, adjList[to], weight);
		}
		
		for (int i = 1 ; i <= N; i++) {
			if (check[i])	continue;
			check[i] = true;
			
			vis = new boolean[N+1];
			vis[i] = true;
			dfs(i, 0); 
		}
		
		System.out.println(res);
		br.close();
	}
	private static void dfs(int num, int sum) {

		for (Node nxt = adjList[num]; nxt != null; nxt = nxt.next) {
			if (vis[nxt.num])	continue;
			vis[nxt.num] = true;
			dfs(nxt.num, sum + nxt.weight);
			vis[nxt.num] = false;
		}
		res = Math.max(res, sum);
	}
}
