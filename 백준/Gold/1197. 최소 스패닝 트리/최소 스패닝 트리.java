import java.util.*;
import java.io.*;

// 최소 스패닝 트리 
public class Main {
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	static StringBuilder sb = new StringBuilder();
	static int V, E;
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	// 정점 개수 
		E = Integer.parseInt(st.nextToken());	// 간선 개수 
		edgeList = new Edge[E];
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int weight = Integer.parseInt(st.nextToken());	// 음수일 수도 있음 
			edgeList[i] = new Edge(from, to, weight);
		}
		
		// make-set 
		parents = new int[V];
		for (int i=0; i<V; i++) {
			parents[i] = i;
		}
		
		Arrays.sort(edgeList);	// 가중치 기준 오름차순 정렬 
		
		int cnt = 0;
		int result = 0;
		for (Edge edge : edgeList) {
			if (!union(edge.from, edge.to))	continue;
			cnt++;
			result += edge.weight;
			if (cnt == V - 1)	break;
		}
		
		System.out.println(result);
	}
	static int findSet(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB)	return false;
		parents[rootA] = rootB;
		return true;
	}
}