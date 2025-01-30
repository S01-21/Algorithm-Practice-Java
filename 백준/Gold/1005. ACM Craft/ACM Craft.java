import java.util.*;
import java.io.*;

public class Main {
	static int N, K, W;
	static int[] time, dp, indegrees;
	static Node[] adjList;
	static class Node{
		int num;
		Node next;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 건물 개수
			K = Integer.parseInt(st.nextToken());	// 규칙 개수
			
			time = new int[N+1];
			dp = new int[N+1];
			indegrees = new int[N+1];
			adjList = new Node[N+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1 ; i <= N ; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0 ; i < K; i++	) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
				indegrees[to] += 1;
			}
			W = Integer.parseInt(br.readLine());	// 건설할 건물 번호
			
			for (int i = 1; i <= N; i++) {
				if (indegrees[i] == 0) {
					queue.offer(i);
				}
			}
			
			bfs();
			sb.append(dp[W]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void bfs() {
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			
			dp[cur] += time[cur];
			for (Node nxt = adjList[cur]; nxt != null; nxt = nxt.next) {
				dp[nxt.num] = Math.max(dp[nxt.num], dp[cur]);
				if (--indegrees[nxt.num] == 0) {
					queue.offer(nxt.num);
				}
			}
		}
	}
}
