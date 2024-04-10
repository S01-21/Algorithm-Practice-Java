import java.util.*;
import java.io.*;

// 골드4. 도시 건설 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static long res;
	static long total;
	static Node[] adjList;
	static int[] minEdge;
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
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 건물 개수 
		M = Integer.parseInt(st.nextToken());	// 도로 개수 
		adjList = new Node[N+1];
		minEdge = new int[N+1];
		vis = new boolean[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
			total += weight;
		}
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minEdge[1] = 0;
		pq.offer(new Node(1, minEdge[1], null));
		long result = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Node min = pq.poll();
			
			if (vis[min.num])	continue;
			
			vis[min.num] = true;
			result += minEdge[min.num];
			
			if (++cnt == N)	break;
			
			for (Node tmp = adjList[min.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minEdge[tmp.num] > tmp.weight) {
					minEdge[tmp.num] = tmp.weight;
					pq.offer(new Node(tmp.num, minEdge[tmp.num], null));
				}
			}
		}
		
		if (cnt != N) {
			res = -1;
		} else {
			res = total - result;
		}
		
		System.out.println(res);
	}
}