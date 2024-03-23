import java.util.*;
import java.io.*;

// 최소비용 구하기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] minDist;
	static Node[] adjList;
	static boolean[] vis;
	static int res;
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
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 도시 개수 
		M = Integer.parseInt(br.readLine());	// 버스 개수 
		adjList = new Node[N+1];
		
		StringTokenizer st = null;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		res = 0;
		vis = new boolean[N+1];
		dijkstra(start, end);
		System.out.println(res);
		
	}
	private static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minDist = new int[N+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		minDist[start] = 0;
		
		pq.offer(new Node(start, 0, null));
		
		while(!pq.isEmpty()) {
			Node stopover = pq.poll();
			
			if (vis[stopover.num])	continue;
			
			vis[stopover.num] = true;
			
			if (stopover.num == end) break;
			
			for (Node tmp = adjList[stopover.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num]) continue;
				if (minDist[tmp.num] > minDist[stopover.num] + tmp.weight) {
					minDist[tmp.num] = minDist[stopover.num] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num], null));
				}
			}
			
		}
		
		res = minDist[end];
	}
}