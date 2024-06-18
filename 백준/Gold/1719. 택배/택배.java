import java.util.*;
import java.io.*;

// 골드 3. 택배 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[][] minDist;
	static boolean[] vis;
	static PriorityQueue<Node> pq;
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
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 집하장 개수 
		M = Integer.parseInt(st.nextToken());	// 경로 개수 
		adjList = new Node[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			adjList[a] = new Node(b, time, adjList[a]);
			adjList[b] = new Node(a, time, adjList[b]);
		}
		
		minDist = new int[N+1][2];
		for (int i=1; i<=N; i++) {
			dijkstra(i);
			
			for (int j=1; j<=N; j++) {
				if (i==j) {
					sb.append('-').append(" ");
				} else {
					sb.append(minDist[j][1]).append(" ");
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	private static void dijkstra(int start) {
		for (int i=1; i<=N; i++) {			
			minDist[i][0] = Integer.MAX_VALUE;
		}
		
		vis = new boolean[N+1];
		pq = new PriorityQueue<>();
		
		minDist[start][0] = 0;
		vis[start] = true;
		
		for (Node tmp = adjList[start]; tmp != null; tmp = tmp.next) {
			minDist[tmp.num][1] = tmp.num;
			minDist[tmp.num][0] = tmp.weight;
			pq.offer(new Node(tmp.num, tmp.weight, null));
		}
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				
				if (minDist[tmp.num][0] > minDist[cur.num][0] + tmp.weight) {
					minDist[tmp.num][0] = minDist[cur.num][0] + tmp.weight;
					pq.offer(new Node(tmp.num, minDist[tmp.num][0], null));
					
					minDist[tmp.num][1] = minDist[cur.num][1];
				}
			}
		}
	}
}