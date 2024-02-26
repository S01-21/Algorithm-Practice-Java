import java.util.*;
import java.io.*;

// 최소 스패닝 트리 (프림)
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static boolean[] vis;
	static Vertex[] adjList;
	static int[] minEdge;
	static class Vertex implements Comparable<Vertex>{
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		vis = new boolean[V+1];	// 트리 정점에 속하는지 여부 체크 
		adjList = new Vertex[V+1];
		minEdge = new int[V+1];
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Vertex(to, weight, adjList[from]);
			adjList[to] = new Vertex(from, weight, adjList[to]);
		}
		
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		pq.offer(new Vertex(1, minEdge[1], null));
		int result = 0;
		
		while(!pq.isEmpty()){
			Vertex minVertex = pq.poll();
			if (vis[minVertex.num])	continue;
			
			vis[minVertex.num] = true;
			result += minEdge[minVertex.num];
			
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