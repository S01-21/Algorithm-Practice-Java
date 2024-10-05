import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, E, v1, v2, res;
	static boolean[] vis;
	static int[] minDist;
	static Node[] adjList;
	static class Node implements Comparable<Node>{
		int num, weight;
		Node next;
		public Node(int num, Node next, int weight) {
			this.num = num;
			this.next = next;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];
		minDist = new int[N+1];
		vis = new boolean[N+1];
		
		for (int i = 0; i< E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from], weight);
			adjList[to] = new Node(from, adjList[to], weight);
		}
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		res = Integer.MAX_VALUE;
		
		int tmp1 = dijkstra(1, v1);
		int tmp2 = dijkstra(v1, v2);
		int tmp3 = dijkstra(v2, N);
		
		if (tmp1 != -1 && tmp2 != -1 && tmp3 != -1) {
			res = tmp1 + tmp2 + tmp3;
		}
		tmp1 = dijkstra(1, v2);
		tmp3 = dijkstra(v1, N);
		if (tmp1 != -1 && tmp2 != -1 && tmp3 != -1) {
			res = Math.min(res, tmp1 + tmp2 + tmp3);
		}
		if (res == Integer.MAX_VALUE) {
			res = -1;
		}
		
		System.out.println(res);
		br.close();
	}
	static int dijkstra(int start, int end) {
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(vis, false);
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;
		pq.offer(new Node(start, null, minDist[start]));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			
			if (cur.num == end)	break;
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[cur.num] + tmp.weight) {
					minDist[tmp.num] = minDist[cur.num] + tmp.weight;
					pq.offer(new Node(tmp.num, null, minDist[tmp.num]));
				}
			}
		}
		return minDist[end] != Integer.MAX_VALUE ? minDist[end] : -1;
	}
}