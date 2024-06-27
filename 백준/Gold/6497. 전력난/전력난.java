import java.util.*;
import java.io.*;

// 골드 4. 전력난 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int M, N, total;
	static Node[] adjList;
	static boolean[] vis;
	static int[] minEdge;
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
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 집의 수 
			N = Integer.parseInt(st.nextToken());	// 길의 수 
			if (M == 0 && N == 0)	break;
			adjList = new Node[M];
			vis = new boolean[M];
			minEdge = new int[M];
			total = 0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int dist = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, dist, adjList[from]);
				adjList[to] = new Node(from, dist, adjList[to]);
				total += dist;
			}
			
			int min = prim();
			sb.append(total-min).append("\n");
		}
		System.out.println(sb);
		br.close();
		
	}
	private static int prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		pq.offer(new Node(0, 0, null));
		int res = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			res += minEdge[cur.num];
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minEdge[tmp.num] > tmp.weight) {
					minEdge[tmp.num] = tmp.weight;
					pq.offer(new Node(tmp.num, minEdge[tmp.num], null));
				}
			}
		}
		
		return res;
	}
}