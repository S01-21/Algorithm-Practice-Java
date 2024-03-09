import java.util.*;
import java.io.*;

// DFS와 BFS 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, V;
	
	static int[][] adjMatrix;
	static boolean[] vis;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjMatrix = new int[N+1][N+1];	// 0인덱스 사용 X
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = 1;
			adjMatrix[to][from] = 1;
		}
		
		vis = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		bfs(V);
		
		System.out.println(sb);
	}
	private static void bfs(int st) {
		vis = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(st);
		vis[st] = true;
		sb.append(st).append(" ");
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 1; i<= N; i++) {
				if (adjMatrix[cur][i] != 1)	continue;
				if (vis[i])	continue;
				vis[i] = true;
				sb.append(i).append(" ");
				queue.offer(i);
			}
		}
	}
	private static void dfs(int st) {
		vis[st] = true;
		sb.append(st).append(" ");
		
		for (int i=1; i<=N; i++) {
			if (adjMatrix[st][i] != 1)	continue;
			if (vis[i])	continue;
			dfs(i);
		}
	}
}