import java.util.*;
import java.io.*;

// 실버 2. 연결 요소의 개수 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static boolean[] vis;
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 정점 개수 
		M = Integer.parseInt(st.nextToken());	// 간선 개수 
		adjList = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		vis = new boolean[N+1];
		for (int i=1; i<=N; i++) {
			if (!vis[i]) {
				vis[i] = true;
				dfs(i);
				res++;
			}
		}
		System.out.println(res);
		br.close();
	}
	private static void dfs(int num) {
		for (int nxt : adjList[num]) {
			if (vis[nxt])	continue;
			vis[nxt] = true;
			dfs(nxt);
		}
	}
}