import java.util.*;
import java.io.*;

// 네트워크 연결 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Vertex[] adjList;
	static int[] minEdge;
	static boolean[] vis;
	static class Vertex implements Comparable<Vertex> {
		int num, weight;
		Vertex next;
		public Vertex(int num, int weight, Vertex next) {
			super();
			this.num = num;
			this.weight = weight;
			this.next = next;
		}
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 컴퓨터 (정점) 수
		M = Integer.parseInt(br.readLine());	// 연결 선 (간선) 수 
		
		adjList = new Vertex[N];
		minEdge = new int[N];
		vis = new boolean[N];
		
		StringTokenizer st = null;
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Vertex(to, weight, adjList[from]);
			adjList[to] = new Vertex(from, weight, adjList[to]);
		}
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;	// 임의의 정점(0)에서 시작 
		pq.offer(new Vertex(0, 0, null));
		int result = 0;
		int c = 0;
		while(!pq.isEmpty()) {
			
			// 인접한 비트리 정점 중 최소비용간선 찾기 
			Vertex minVertex = pq.poll();
			if (vis[minVertex.num])	continue;
			
			
			// 방문처리 및 탐색정점 처리
			vis[minVertex.num] = true;
			result += minVertex.weight;
			if (++c == N)	break;	// 모든 컴퓨터 연결하면 종료 
			
			
			// 인접 정점과 연결 시 최소가 되는 간선 갱신 
			for (Vertex tmp = adjList[minVertex.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minEdge[tmp.num] > tmp.weight) {
					minEdge[tmp.num] = tmp.weight;
					pq.offer(new Vertex(tmp.num, minEdge[tmp.num], null));
				}
			}

		}
		System.out.println(result);
		
	}
}