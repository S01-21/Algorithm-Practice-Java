import java.util.*;
import java.io.*;

public class Main {
	static int N, res;
	static int[][] coord;
	static int[] parents;
	static ArrayList<Edge> edgeList;
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		coord = new int[N][4];	//x, y, z, 인덱스 기록
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coord[i][0] = Integer.parseInt(st.nextToken());
			coord[i][1] = Integer.parseInt(st.nextToken());
			coord[i][2] = Integer.parseInt(st.nextToken());
			coord[i][3] = i;
		}
		
		edgeList = new ArrayList<>();
		for (int dim = 0; dim < 3; dim++) {
			final int curDim = dim;
			Arrays.sort(coord, Comparator.comparingInt(o -> o[curDim]));
			for (int i = 0; i< N-1; i++) {
				int cost = Math.abs(coord[i][curDim] - coord[i+1][curDim]);
				edgeList.add(new Edge(coord[i][3], coord[i+1][3], cost));
			}
		}
		
		Collections.sort(edgeList);
		
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		int cnt = 0;
		for (Edge edge : edgeList) {
			if (union(edge.from, edge.to)) {
				res += edge.cost;
				if (++cnt == N-1)	break;
			}
		}
		
		System.out.println(res);
		br.close();
	}
	private static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}
