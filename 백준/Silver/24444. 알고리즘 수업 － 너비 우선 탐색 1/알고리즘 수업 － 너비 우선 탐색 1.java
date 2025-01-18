import java.util.*;
import java.io.*;

public class Main {
	static int N, M, R;
	static StringBuilder sb = new StringBuilder();
	static ArrayList<Integer>[] adjList;
	static int[] order;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N + 1];
		order = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
		
		for (int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
            adjList[v].add(u);
		}
		
		for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }

		bfs(R);
		
		for (int i = 1; i <= N; i++) {
			sb.append(order[i]).append("\n");
		}
		System.out.println(sb);
		br.close();
	}
	private static void bfs(int st) {
		boolean[] vis = new boolean[N+1];
		Queue<Integer> queue = new ArrayDeque<>();
		vis[st] = true;
		queue.offer(st);
		int tmp = 1;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			order[cur] = tmp++;
			for (int nxt : adjList[cur]) {
				if (vis[nxt])	continue;
				vis[nxt] = true;
				queue.offer(nxt);
			}
		}
	}
}
