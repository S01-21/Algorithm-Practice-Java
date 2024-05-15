import java.util.*;
import java.io.*;

// 골드 5. 소셜 네트워킹 어플리케이션 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, K, M;
	static Edge[] edgeList;
	static int[] parents;
	static class Edge{
		int from, to;
		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("Scenario ").append(tc).append(":\n");
			
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			edgeList = new Edge[K];
			
			StringTokenizer st = null;
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(a, b);
			}
			
			makeSet();
			for (Edge edge : edgeList) {
				union(edge.from, edge.to);
			}
			
			M = Integer.parseInt(br.readLine());
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if (find(a) == find(b)) {
					sb.append(1).append("\n");
				} else {
					sb.append(0).append("\n");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void makeSet() {
		parents = new int[N];
		for (int i=0; i<N; i++) {
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