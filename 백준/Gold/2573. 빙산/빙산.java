import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static Queue<Pair> queue;
	static boolean[][] vis;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		vis = new boolean[N][M];
		queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int year = 1;
		while (true) {
			meltIce();
			if (countIce() >= 2) {
				res = year;
				break;
			} else if (countIce() == 0) {
				break;
			}
			year++;
		}

		System.out.println(res);
		br.close();
	}

	private static void meltIce() {
		for (int i=0; i< N; i++) {
			for (int j=0; j<M; j++) {
				if (map[i][j] != 0) {
					queue.offer(new Pair(i, j));
					vis[i][j] = true;
				}
			}
		}
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			int cnt = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (vis[nx][ny])
					continue;
				if (map[nx][ny] == 0) {
					cnt++;
				}
			}
			map[cur.x][cur.y] = map[cur.x][cur.y] - cnt >= 0 ? map[cur.x][cur.y] - cnt : 0;
		}
	}

	private static int countIce() {
		vis = new boolean[N][M];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (vis[i][j])
					continue;
				if (map[i][j] != 0) {
					floodfill(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void floodfill(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		vis[x][y] = true;
		queue.offer(new Pair(x, y));
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (vis[nx][ny])
					continue;
				if (map[nx][ny] == 0)
					continue;
				vis[nx][ny] = true;
				queue.offer(new Pair(nx, ny));
			}
		}
	}
}