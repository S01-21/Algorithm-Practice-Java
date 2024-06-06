import java.util.*;
import java.io.*;

// 골드 3. 파티 
public class Main {	
	static StringBuilder sb = new StringBuilder();
	static int N, M, X, res;
	static int[] minDist;
	static boolean[] vis;
	static Node[] adjList;
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
		N = Integer.parseInt(st.nextToken());	// 학생 수 
		M = Integer.parseInt(st.nextToken());	// 도로 수 
		X = Integer.parseInt(st.nextToken());	// 파티 장소 
		adjList = new Node[N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		res = -1;
		minDist = new int[N+1];
		
		for (int i=1; i<=N; i++) {
			int sum = 0;
			sum += dijkstra(i, X);
			sum += dijkstra(X, i);
			res = Math.max(res, sum);
		}
		System.out.println(res);
		br.close();
	}
	private static int dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(minDist, Integer.MAX_VALUE);
		vis = new boolean[N+1];
		minDist[start] = 0;
		pq.offer(new Node(start, minDist[start], null));
		
		while(!pq.isEmpty()) {
			Node stopover = pq.poll();
			
			if (vis[stopover.num])	continue;
			
			vis[stopover.num] = true;
			
			if (stopover.num == end) break;
			
			for (Node tmp = adjList[stopover.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[stopover.num] + tmp.weight) {
					minDist[tmp.num] = minDist[stopover.num] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num], null));
				}
			}
		}
		return minDist[end];
	}
	
}