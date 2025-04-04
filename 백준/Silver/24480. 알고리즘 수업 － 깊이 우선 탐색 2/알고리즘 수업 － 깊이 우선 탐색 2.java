import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, M, R, res;
	static ArrayList<Integer>[] adjList;
	static int[] dist;
	static int order;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}
		for (int i = 1; i<= N; i++) {
			Collections.sort(adjList[i], Collections.reverseOrder());
		}
		
		dist = new int[N+1];
		order = 1;
		dist[R] = order;
		order++;
		dfs(R);
		
		for (int i = 1; i <= N; i++) {
			sb.append(dist[i]).append("\n");
		}
		
		System.out.println(sb);
		br.close();
	}
	private static void dfs(int num) {
		for (int nxt : adjList[num]) {
			if (dist[nxt] > 0)	continue;
			dist[nxt] = order;
			order++;
			dfs(nxt);
		}
	}
}
