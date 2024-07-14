import java.util.*;
import java.io.*;

// 실버 2. 촌수계산 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, A, B, M;
	static int[] dist;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			super();
			this.num = num;
			this.next = next;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());	// 사람 수 
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());	// 관계의 개수 
		adjList = new Node[N+1];
		dist = new int[N+1];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adjList[x] = new Node(y, adjList[x]);
			adjList[y] = new Node(x, adjList[y]);
		}
		
		int res = bfs(A, B);
		System.out.println(res);
		br.close();
	}
	private static int bfs(int from, int to) {
		Arrays.fill(dist, -1);
		Queue<Integer> queue = new ArrayDeque<>();
		dist[from] = 0;
		queue.offer(from);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			if (cur == to)	break;
			for (Node nxt = adjList[cur]; nxt != null; nxt = nxt.next) {
				if (dist[nxt.num] >=0)	continue;
				dist[nxt.num] = dist[cur] + 1;
				queue.offer(nxt.num);
			}
		}
		return dist[to];
	}
}