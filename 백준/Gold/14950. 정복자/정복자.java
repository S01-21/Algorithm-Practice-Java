import java.util.*;
import java.io.*;

// 골드 4. 정복자 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, T, res;
	static int[] parents;
	static Edge[] edgeList;
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
			return this.weight - o.weight;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 도시 개수 
		M = Integer.parseInt(st.nextToken());	// 도로 개수 
		T = Integer.parseInt(st.nextToken());	// 증가하는 도로 비용 
		edgeList = new Edge[M];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(A, B, C);
		}
		
		makeSet();
		
		Arrays.sort(edgeList);
		int cnt = 0;
		
		for (Edge edge: edgeList) {
			if (union(edge.from, edge.to)) {
				res = res + edge.weight + cnt*T;
				if (++cnt == N-1) break;
			}
		}
		
		System.out.println(res);
		br.close();
	}
	private static void makeSet() {
		parents = new int[N+1];
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	private static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}