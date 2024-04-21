import java.util.*;
import java.io.*;

// 실버 2. 특정 거리의 도시 찾기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, X;
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
	static boolean[] vis;
	static int[] minDist;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시 번호
		adjList = new Node[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
		}

		vis = new boolean[N+1];
		minDist = new int[N+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		dijkstra();
		
		for (int i=1; i<=N; i++) {
			if (minDist[i] == K) {
				res++;
				sb.append(i).append("\n");
			}
		}
		if (res == 0) {
			System.out.println(-1);
		} else {
			System.out.println(sb);
		}
		
		br.close();
	}
	private static void dijkstra() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.offer(new Node(X, null));
		minDist[X] = 0;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			if (vis[cur.num])	continue;
			
			vis[cur.num] = true;
			
			for (Node tmp = adjList[cur.num]; tmp != null; tmp = tmp.next) {
				if (vis[tmp.num])	continue;
				if (minDist[tmp.num] > minDist[cur.num] + 1) {
					minDist[tmp.num] = minDist[cur.num] + 1;
					queue.offer(new Node(tmp.num, null));
				}
			}
		}
	}
	
	
}