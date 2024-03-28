import java.util.*;
import java.io.*;

// 골드3. 야쿠르트 아줌마 야쿠르트 주세요 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static long[] minDist;
	static boolean[] vis;
	static long[] dist;
	static Node[] adjList;
	static int[] order;
	static long[] time;
	static int start;
	static long res;
	static class Node implements Comparable<Node>{
		int num;
		long weight;
		Node next;
		public Node(int num, long weight, Node next) {
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
			return Long.compare(this.weight, o.weight);	
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		adjList = new Node[V+1];
		order = new int[10];	// 야쿠르트 아줌마 방문지점 순서 
		time = new long[10];		// 야쿠르트 아줌마 방문지점 도착시간 
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<10; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		start = Integer.parseInt(br.readLine());	// 출발정점
		
		Arrays.fill(time, -1);
		int from = 0;
		int to = 1;
		time[from] = 0;
		while (to < 10) {
			long ret = dijkstra(order[from], order[to]);
			if (ret == Long.MAX_VALUE) {
				to++;
			} else {
				time[to] = time[from] + ret;
				from = to;
				to = from+1;
			}
		}
		
		res = Long.MAX_VALUE;
		for (int i=0; i<10; i++) {
			if (time[i] == -1)	continue;	// 야쿠르트 아줌마가 도달할 수 없는 곳
			if (order[i] == start) {
				res = Math.min(res, order[i]);
			}
			bfs(i);
		}
		
		if (res == Long.MAX_VALUE) {
			res = -1;
		}
		System.out.println(res);
	}
	private static void bfs(int dest) {
		Queue<Integer> queue = new ArrayDeque<>();
		dist = new long[V+1];
		Arrays.fill(dist, -1);
		dist[start] = 0;
		queue.offer(start);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (Node tmp = adjList[cur]; tmp != null; tmp = tmp.next ) {
				if (dist[tmp.num] >= 0)	continue;
				if (tmp.num == order[dest] && time[dest] >= dist[cur] + tmp.weight) {
					res = Math.min(res, order[dest]);
					return;
				}
				dist[tmp.num] = dist[cur] + tmp.weight;
				queue.offer(tmp.num);
			}
		}
	}
	private static long dijkstra(int from, int to) {
		minDist = new long[V+1];
		Arrays.fill(minDist, Long.MAX_VALUE);
		vis = new boolean[V+1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		minDist[from] = 0;
		pq.offer(new Node(from, 0, null));
		
		while (!pq.isEmpty()) {
			Node stopover = pq.poll();
			
			if (vis[stopover.num])	continue;
			
			vis[stopover.num] = true;
			
			if (stopover.num == to) break;
			
			for (Node tmp = adjList[stopover.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[stopover.num] + tmp.weight) {
					minDist[tmp.num] = minDist[stopover.num] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num], null));
				}
			}
		}
		
		return minDist[to];
	}
}