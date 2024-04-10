import java.util.*;
import java.io.*;

// 골드 5. 택배 배송 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] minDist;
	static boolean[] vis;
	static Node[] adjList;
	static class Node implements Comparable<Node>{
		int num, weight;
		Node nxt;
		public Node(int num, int weight, Node nxt) {
			super();
			this.num = num;
			this.weight = weight;
			this.nxt = nxt;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 헛간 수 
		M = Integer.parseInt(st.nextToken());	// 길 수 
		adjList = new Node[N+1];
		minDist = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		
		Arrays.fill(minDist, Integer.MAX_VALUE);
		vis = new boolean[N+1];
		minDist[1] = 0;
		dijkstra();
		
		System.out.println(minDist[N]);
	}
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, minDist[1], null));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			
			if (cur.num == N)	break;
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.nxt) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[cur.num] + tmp.weight) {
					minDist[tmp.num] = minDist[cur.num] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num], null));
				}
			}
		}
	}
}