import java.util.*;
import java.io.*;

// 골드 3. 연애 혁명
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static class Edge implements Comparable<Edge>{
		int from, to, weight, d;
		public Edge(int from, int to, int weight, int d) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
			this.d = d;
		}
		@Override
		public int compareTo(Edge o) {
			return o.weight - this.weight;
		}
	}
	static int[] parents;
	static Edge[] edgeList;
	static int res;
	static int total;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 학생 수 
		M = Integer.parseInt(st.nextToken());	// 사랑 관계 수 
		edgeList = new Edge[M];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight, d);
			if (d == 0) {
				total += weight;
			}
		}
		
		make();
		int cnt = 0;
		for (Edge edge: edgeList) {
			if (edge.d == 0)	continue;
			union(edge.from, edge.to);	// 이미 성사된 관계들 연결
			cnt++;
		}
		
		Arrays.sort(edgeList);	// 가중치 기준 오름차순 정렬
		
		for (Edge edge : edgeList) {
			if (!union(edge.from, edge.to)) {
				continue;
			}
			res += edge.weight;
			if (++cnt == N - 1) {
				break;
			}
		}
		
		System.out.println(total - res);
		br.close();
	}
	static void make() {
		parents = new int[N+1];
		for (int i=1; i<=N; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}