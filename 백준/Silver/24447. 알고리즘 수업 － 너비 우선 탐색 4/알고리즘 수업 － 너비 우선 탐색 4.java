import java.util.*;
import java.io.*;
import java.math.*;

public class Main {
	static int N, M, R, res;
	static ArrayList<Integer>[] adjList;
	static long[] order, depth;
	static int cnt;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList[N+1];
		order = new long[N+1];
		depth = new long[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
			depth[i] = -1;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		for (int i = 1; i<= N; i++) {
			Collections.sort(adjList[i]);
		}
		
		
		bfs(R);
		
		long res = 0;
		for (int i = 1; i <= N; i++) {
			res += order[i]*depth[i];
		}
		
		System.out.println(res);
		br.close();
	}
	
	private static void bfs(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		order[num] = ++cnt;
		depth[num] = 0;
		queue.offer(num);
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int nxt : adjList[cur]) {
				if (depth[nxt] >= 0)	continue;
				depth[nxt] = depth[cur]+1;
				order[nxt] = ++cnt;
				queue.offer(nxt);
			}
		}
	}
}
