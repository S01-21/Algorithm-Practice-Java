import java.util.*;
import java.io.*;


public class Main {
	static class Pair{
		double x, y;
		Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Edge implements Comparable<Edge>{
		int from, to;
		double weight;
		Edge(int from, int to, double weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public int compareTo(Edge o) {
			return (int)(this.weight - o.weight);
		}
	}
	static StringBuilder sb = new StringBuilder();
	static int N;
	static Pair[] pairList;
	static PriorityQueue<Edge> edgeList;
	static int[] parents;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pairList = new Pair[N];
		edgeList = new PriorityQueue<>();
		StringTokenizer st = null;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			pairList[i] = new Pair(x, y);
		}
		
		for (int i= 0; i< N-1; i++) {
			for (int j = i + 1; j < N; j++) {
				edgeList.offer(new Edge(i, j, dist(pairList[i], pairList[j])));
			}
		}
		makeSet();
		int cnt = 0;
		double sum = 0;
		while (!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			if (union(edge.from, edge.to)) {
				sum += edge.weight;
				if (++cnt == N-1) break;
			}
		}

		System.out.println(sum);
		br.close();
	}
	static double dist(Pair p1, Pair p2) {
		double diffX = Math.abs(p1.x - p2.x);
		double diffY = Math.abs(p1.y - p2.y);
		return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
	}
	static void makeSet() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if (parents[a] == a)	return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union (int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)	return false;
		parents[rootB] = rootA;
		return true;
	}
}