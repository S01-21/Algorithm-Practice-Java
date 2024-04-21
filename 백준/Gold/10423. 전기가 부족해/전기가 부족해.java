import java.util.*;
import java.io.*;

// 골드 3. 전기가 부족해 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K;
	static int[] parents;
	static ArrayList<Edge> edgeList;
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
		N = Integer.parseInt(st.nextToken());	// 도시의 개수 
		M = Integer.parseInt(st.nextToken());	// 케이블 수 
		K = Integer.parseInt(st.nextToken());	// (이미 설치된) 발전소 개수 
		edgeList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		for (int i=1; i<K; i++) {
			int second = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(first, second, 0));	// 이미 설치된 발전소 간 가중치 0인 케이블 생성
			first = second;
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(u, v, w));
		}
		
		make();
		Collections.sort(edgeList);	// 오름차순 정렬
		int cnt = 0;
		int result = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				result += edge.weight;
				if (++cnt == N-1) {
					break;
				}
			}
		}
		System.out.println(result);
		br.close();
	}
	private static void make() {
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