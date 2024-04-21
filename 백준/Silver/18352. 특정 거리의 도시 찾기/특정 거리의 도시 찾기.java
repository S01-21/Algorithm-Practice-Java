import java.util.*;
import java.io.*;

// 실버 2. 특정 거리의 도시 찾기 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, K, X;
	static class Pair{
		int num, dist;

		public Pair(int num, int dist) {
			super();
			this.num = num;
			this.dist = dist;
		}
		
	}
	static boolean[] vis;
	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer> res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(st.nextToken()); // 도로의 개수
		K = Integer.parseInt(st.nextToken()); // 거리 정보
		X = Integer.parseInt(st.nextToken()); // 출발 도시 번호
		adjList = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
			
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from].add(to);
		}

		res = new ArrayList<>();
		vis = new boolean[N+1];
		bfs(X);
		
		Collections.sort(res);
		
		if (res.size() == 0) {
			System.out.println(-1);
		} else {
			for (int n : res) {
				sb.append(n).append("\n");
			}
			System.out.println(sb);
		}
		br.close();
	}

	private static void bfs(int start) {
		Queue<Pair> queue = new ArrayDeque<>();
		vis[start] = true;
		queue.offer(new Pair(start, 0));
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			if (cur.dist == K) {
				res.add(cur.num);
			}
			
			for (int nxt : adjList[cur.num]) {
				if (vis[nxt]) continue;
				vis[nxt] = true;
				queue.offer(new Pair(nxt, cur.dist + 1));
			}
		}
	}
}