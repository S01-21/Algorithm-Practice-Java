import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int N, M, D;
	static int[][] map;
	static boolean[] selectedArcher;
	static boolean[][] vis;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken()); // 공격 거리제한
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		res = Integer.MIN_VALUE;
		selectedArcher = new boolean[M];
		comb(0, 0, new int[3]);

		System.out.println(res);
	}

	private static void comb(int cnt, int st, int[] arr) {
		if (cnt == 3) { // 기저 조건: 궁수 3명 뽑았을 경우
			int[][] tmpMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				tmpMap[i] = map[i].clone();
			}
			int sum = 0; // 죽인 적 개수
			do {
				ArrayList<Pair> dead = new ArrayList<>();
				for (int i = 0; i < 3; i++) {
					int pos = arr[i];
					Pair tmp = bfs(N, pos, tmpMap);
					if (tmp != null)
						dead.add(tmp);
				}
				for (Pair tmp : dead) {
					if (tmpMap[tmp.x][tmp.y] == 1) {
						sum++;
						tmpMap[tmp.x][tmp.y] = 0; // 적 죽으면 게임에서 제외
					}
				}

				// 적 이동시키기
				for (int i = N - 1; i > 0; i--) {
					for (int j = 0; j < M; j++) {
						tmpMap[i][j] = tmpMap[i - 1][j];
					}
				}
				for (int j = 0; j < M; j++) {
					tmpMap[0][j] = 0;
				}
			} while (exist(tmpMap));
			res = Math.max(res, sum);
			return;
		}

		for (int i = st; i < M; i++) {
//			selectedArcher[i] = true;
			arr[cnt] = i;
			comb(cnt + 1, i + 1, arr);
		}
	}

	static boolean exist(int[][] prevMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (prevMap[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static Pair bfs(int x, int y, int[][] prevMap) {
		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = prevMap[i].clone();
		}
		vis = new boolean[N][M];
		PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
			@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.dist != o2.dist) {
					return o1.dist - o2.dist;
				} else {
					return o1.y - o2.y;
				}
			}
		});
		Queue<Pair> queue = new ArrayDeque<>();
		queue.offer(new Pair(x, y, 0));

		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + deltas[dir][0];
				int ny = cur.y + deltas[dir][1];
				if (isOut(nx, ny))
					continue;
				if (vis[nx][ny])
					continue;
				if (map[nx][ny] == 1) { // 적 발견
					int d = Math.abs(x - nx) + Math.abs(y - ny);
					if (d <= D) {
						pq.offer(new Pair(nx, ny, d));
						vis[nx][ny] = true;
					}
				} else {
					int d = Math.abs(x - nx) + Math.abs(y - ny);
					queue.offer(new Pair(nx, ny, d));
					vis[nx][ny] = true;
				}
			}
		}

		if (pq.isEmpty()) {
			return null;
		}
		return pq.poll();

	}

	private static boolean exist() { // 적이 하나라도 존재하면 true
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					return true;
			}
		}
		return false;
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= M;
	}

	static class Pair {
		int x, y, dist;

		public Pair(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}