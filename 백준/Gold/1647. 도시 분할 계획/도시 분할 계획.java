import java.util.*;
import java.io.*;

// 도시 분할 계획
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Edge[] edgeList;
	static int[] parents;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {	// 가중치 오름차순 
			return Integer.compare(this.weight, o.weight);
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 집의 개수
		M = Integer.parseInt(st.nextToken());	// 길의 개수 
		edgeList = new Edge[M];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		}
		
		Arrays.sort(edgeList);
		parents = new int[N + 1];
		for (int i = 1; i<=N; i++) {
			parents[i] = i;
		}
		
		int result = 0;
		int cnt = 0;	// 연결된 간선 수 (N - 2 에 종료)
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {	// 사이클 발생 X
				result += edge.weight;
				if (++cnt == N - 2) {
					break;
				}
			}
		}
		if (N == 2) {
			result = 0;
		}
		System.out.println(result);
	}
	
	static int findSet(int a) {
		if (parents[a] == a) 	return a;
		return parents[a] = findSet(parents[a]);
	}
	static boolean union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}