import java.util.*;
import java.io.*;

public class Solution {
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
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());	// 정점 개수
			E = Integer.parseInt(st.nextToken());	// 간선 개수
			edgeList = new Edge[E];
			parents = new int[V];
			for (int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken()) - 1;
				int to = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(from, to, weight);
			}
			
			Arrays.sort(edgeList);
			make();
			
			long weight = 0;
			int cnt = 0;
			for (Edge edge : edgeList) {
				if (!union(edge.from, edge.to))	continue;
				weight += edge.weight;
				if (++cnt == V-1) break;
			}
			
			sb.append(weight).append("\n");
		}
		
		System.out.println(sb);
	}
	static void make() {
		for (int i=0; i<V; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)	return false;
		parents[bRoot] = aRoot;
		return true;
	}
}