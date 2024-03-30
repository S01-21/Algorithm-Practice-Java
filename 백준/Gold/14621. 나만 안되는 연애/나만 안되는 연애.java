import java.util.*;
import java.io.*;

// 골드3. 나만 안되는 연애 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static Node[] adjList;
	static char[] gender;

	static class Node implements Comparable<Node> {
		int num, weight;
		Node next;

		public Node(int num, int weight, Node next) {
			super();
			this.num = num;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	static int[] minEdge;
	static boolean[] vis;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 학교 개수
		M = Integer.parseInt(st.nextToken()); // 도로 개수
		adjList = new Node[N];
		gender = new char[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			gender[i] = st.nextToken().charAt(0);
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken());

			adjList[u] = new Node(v, d, adjList[u]);
			adjList[v] = new Node(u, d, adjList[v]);
		}

		minEdge = new int[N];
		vis = new boolean[N];

		Arrays.fill(minEdge, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		minEdge[0] = 0;
		pq.offer(new Node(0, minEdge[0], null));
		int result = 0;
		int cnt = 0;
		while (!pq.isEmpty()) {
			Node min = pq.poll();

			if (vis[min.num])
				continue;

			vis[min.num] = true;
			result += min.weight;

			if (++cnt == N)
				break;

			for (Node tmp = adjList[min.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num]) continue;
				if (gender[tmp.num] == gender[min.num])	continue;
				if (minEdge[tmp.num] > tmp.weight) {
					minEdge[tmp.num] = tmp.weight;
					pq.offer(new Node(tmp.num, minEdge[tmp.num], null));

				}
			}
		}

		if (cnt != N) {
			result = -1;
		}

		System.out.println(result);
	}
}