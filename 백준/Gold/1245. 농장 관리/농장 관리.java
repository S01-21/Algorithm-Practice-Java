import java.util.*;
import java.io.*;

// 골드 5. 농장 관리 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M, res;
	static int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int[][] map;
	static boolean[][] vis, check;

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j])
					continue;
				if (map[i][j] == 0)
					continue;
				bfs(i, j);
			}
		}
		System.out.println(res);
		br.close();
	}

	private static void bfs(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		vis = new boolean[N][M];
		queue.offer(new Pair(x, y));
		if (!isTop(x, y))
			return;
		vis[x][y] = true;
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 8; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (nx < 0 || nx >= N || ny < 0 || ny >= M)
					continue;
				if (vis[nx][ny])
					continue;
				if (map[nx][ny] != map[cur.x][cur.y])
					continue;

				if (isTop(nx, ny)) {
					vis[nx][ny] = true;
					check[nx][ny] = true;
					queue.offer(new Pair(nx, ny));
				} else {
					return;
				}
			}
		}
		res++;
	}

	private static boolean isTop(int x, int y) {
		for (int dir = 0; dir < 8; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (map[nx][ny] > map[x][y]) {
				return false;
			}
		}
		return true;
	}

}