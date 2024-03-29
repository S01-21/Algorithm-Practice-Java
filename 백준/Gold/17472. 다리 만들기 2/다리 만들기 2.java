import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	// BFS 변수들
	static int[][] map;
	static boolean[][] vis;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int num;
	static class Pair{
		int x, y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	// MST 변수들
	static int[] parents;
	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public Edge() {
			
		}
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}
	
	static int res;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					map[i][j] = -1;	// 섬을 -1로 표시
				}
			}
		}
		
		// 1. 섬 (정점) 식별 : BFS
		vis = new boolean[N][M];
		num = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (!vis[i][j] && map[i][j] == -1) {
					num++;
					setIsland(i, j, num);	// 섬 번호 붙이기
				}
			}
		}
		
		// 2. 다리정보(간선) 식별: 간선리스트 (크루스칼)
		List<Edge> list = new ArrayList<>();
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] != 0) {	// 섬이라면
					for (int dir = 0; dir <4; dir++) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if (isOut(nx, ny))continue;
						if (map[nx][ny] != 0) {	// 섬 내부일 경우
							continue;
						} else {	// 바다일 경우
							Edge tmp = new Edge();
							tmp.from = map[i][j];
							
							// 다리 시작 좌표
							int tx = nx;
							int ty = ny;
							
							for (int cnt = 1; (0<=tx && tx < N) && (0 <= ty && ty < M); cnt++) {
								tx += dx[dir];
								ty += dy[dir];
								if (isOut(tx, ty)) continue;
								if (map[tx][ty] == 0)	continue;	// 바다일경우 계속 다리 건설
								if (cnt > 1) {
									tmp.to = map[tx][ty];
									tmp.weight = cnt;
									list.add(tmp);
								}
								break;
							}
						}
					}
				}
			}
		}
		
		Collections.sort(list);
		make();
		int cnt = 0;
		for (Edge edge : list) {
			if (!union(edge.from, edge.to)) {
				continue;
			}
			 res += edge.weight;
			 if (++cnt == num-1) {
				 break;
			 }
		}
		
		if (res == 0 || cnt != num-1) {
			res = -1;
		}
		
		System.out.println(res);
	}
	static void make() {
		parents = new int[num + 1];
		for (int i=1; i<=num; i++) {
			parents[i] = i;
		}
	}
	static int find(int a) {
		if (a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)	return false;
		parents[rootA] = rootB;
		return true;
	}
	private static void setIsland(int x, int y, int num) {
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x,  y));
		vis[x][y] = true;
		map[x][y] = num;
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isOut(nx, ny))	continue;
				if (vis[nx][ny])	continue;
				if (map[nx][ny] == -1) {
					vis[nx][ny] = true;
					map[nx][ny] = num;
					queue.offer(new Pair(nx, ny));
				}
			}
		}
	}
	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}
}