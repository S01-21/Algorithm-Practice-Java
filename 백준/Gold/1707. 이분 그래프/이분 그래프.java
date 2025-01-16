import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static int[] vis;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new Node[V];
			vis = new int[V];
			
			for (int i = 0 ; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}
			
			
			boolean flag = true;
			for (int i = 0; i < V; i++) {
				if (vis[i] == 0) {
					boolean res = func(i);
					if (!res) {
						flag = false;
						break;
					}
				}
			}

			if (flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}
		System.out.println(sb);
		br.close();
	}
	private static boolean func(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		vis[start] = 1;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Node nxt = adjList[cur]; nxt != null; nxt = nxt.next) {
				if (vis[nxt.num] == vis[cur])	return false;
				if (vis[nxt.num] != 0)	continue;
				vis[nxt.num] = (vis[cur]==1 ? 2 : 1);
				queue.offer(nxt.num);
			}
		}
		return true;
	}
}
