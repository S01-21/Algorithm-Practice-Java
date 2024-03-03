import java.util.*;


import java.io.*;

// 최단경로 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int V, E, K;
	static int[] minDist;
	static Node[] adjList;
	static boolean[] vis;
	static final int INF = Integer.MAX_VALUE;
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
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		adjList = new Node[V + 1];
		minDist = new int[V + 1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		dijkstra();
		
		System.out.println(sb);
	}
	
	static void dijkstra() {
		Arrays.fill(minDist, INF);
		vis = new boolean[V + 1];
		
		minDist[K] = 0;
		for (int i = 0; i < V; i++) {	// 정점 개수만큼 반복 
			int min = INF;
			int stopover = 0;
			for (int j = 1; j <= V; j++) {
				if (vis[j])	continue;
				if (min > minDist[j]) {
					min = minDist[j];
					stopover = j;
				}
			}
//			if (stopover == -1) continue;
			vis[stopover] = true;
			
			for (Node tmp = adjList[stopover]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[stopover] + tmp.weight) {
					minDist[tmp.num] = minDist[stopover] + tmp.weight;
				}
			}
		}
		for (int i = 1; i<= V; i++) {
			if (i == K) {
				sb.append(0).append("\n");
				continue;
			}
			if (minDist[i] != INF) {
				sb.append(minDist[i]);
			} else {
				sb.append("INF");
			}
			sb.append("\n");
		}
	}
}