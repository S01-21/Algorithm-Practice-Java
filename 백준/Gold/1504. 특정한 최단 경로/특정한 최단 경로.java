import java.util.*;
import java.io.*;

// 특정한 최단 경로 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, E;
	static int[] minDist;
	static Node[] adjList;
	static boolean[] vis;
	static final int INF = Integer.MAX_VALUE;
	static class Node{
		int num, weight;
		Node next;
		public Node(int num, int weight, Node next) {
			super();
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 정점 개수
		E = Integer.parseInt(st.nextToken());	// 간선 개수
		
		adjList = new Node[N + 1];	// 0번인덱스 사용 x
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		st = new StringTokenizer(br.readLine());
		int	stop1 = Integer.parseInt(st.nextToken());
		int stop2 = Integer.parseInt(st.nextToken());
		
		int res = INF;
		int tmp1 = dijkstra(1, stop1);
		int tmp2 = dijkstra(stop1, stop2);
		int tmp3 = dijkstra(stop2, N);
		if (tmp1 != -1 && tmp2 != -1 && tmp3 != -1) {
			res = tmp1 + tmp2 + tmp3;
		}
		
		tmp1 = dijkstra(1, stop2);
		tmp3 = dijkstra(stop1, N);
		if (tmp1 != -1 && tmp2 != -1 && tmp3 != -1) {
			res = Math.min(res, tmp1 + tmp2 + tmp3);
		}
		
		System.out.println(res != INF ? res : -1);
	}
	
	private static int dijkstra(int start, int end) {
		minDist = new int[N + 1];
		vis = new boolean[N + 1];
		Arrays.fill(minDist, INF);
		minDist[start] = 0;
		
		for (int i = 0; i < N; i++) {
			int min = INF;
			int stopover = -1;
			for (int j=1; j<=N; j++) {
				if (vis[j])	continue;
				if (min > minDist[j]) {
					min = minDist[j];
					stopover = j;
				}
			}
			if (stopover == -1)	break;
			vis[stopover] = true;
			if (stopover == end) break;
			
			for (Node tmp = adjList[stopover]; tmp != null; tmp = tmp.next) {
				if (minDist[tmp.num] > min + tmp.weight) {
					minDist[tmp.num] = min + tmp.weight;
				}
			}
		}
		return minDist[end] != INF ? minDist[end] : -1;
	}
	
}