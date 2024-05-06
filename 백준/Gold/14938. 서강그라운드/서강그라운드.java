import java.util.*;
import java.io.*;

// 골드 4. 서강그라운드 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, R, res;
	static int[] items;
	static Node[] adjList;
	static int[] minDist;
	static boolean[] vis;
	static class Node implements Comparable<Node>{
		int num, weight;
		Node next;
		public Node(int num, int weight, Node next) {
			super();
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public String toString() {
			return "Node [num=" + num + ", weight=" + weight + ", next=" + next + "]";
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 지역 개수 
		M = Integer.parseInt(st.nextToken());	// 수색범위 
		R = Integer.parseInt(st.nextToken());	// 길 개수
		items = new int[N+1];
		adjList = new Node[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, l, adjList[a]);
			adjList[b] = new Node(a, l, adjList[b]);
		}
		
		
		res = -1;
		for (int i=1; i<=N; i++) {
			minDist = new int[N+1];
			vis = new boolean[N+1];
			Arrays.fill(minDist, Integer.MAX_VALUE);
			dijkstra(i);
			
			int sum = items[i];
			for (int j=1; j<=N; j++) {
				if (i == j)	continue;
				if (minDist[j] <= M) {
					sum += items[j];
				}
			}
			res = Math.max(res, sum);
		}
		
		System.out.println(res);
		br.close();
	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start], null));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[cur.num] + tmp.weight) {
					minDist[tmp.num] = minDist[cur.num] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num], null));
				}
			}
		}
	}
}