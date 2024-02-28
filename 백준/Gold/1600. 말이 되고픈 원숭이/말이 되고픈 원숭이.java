import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int K, W, H;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int[] hx = { -1, -1, 1, 1, -2, -2, 2, 2 };
	static int[] hy = { 2, -2, 2, -2, 1, -1, 1, -1 };
	static int[][][] dist;
	static int res;

	static class Monkey {
		int x, y, cnt;

		public Monkey(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Monkey [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];

        if (W == 1 && H == 1) {
			System.out.println(0);
			System.exit(0);
		}
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		res = Integer.MAX_VALUE;
		dist = new int[K+1][H][W];
		for (int i=0; i <= K; i++) {
			for (int j=0; j<H; j++) {
				for (int k=0; k<W; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		
		bfs(0, 0);
		
		System.out.println(res);
	}

	private static void bfs(int x, int y) {
		Queue<Monkey> queue = new ArrayDeque<>();
		dist[0][x][y] = 1;
		queue.offer(new Monkey(x, y, 0));
		
		boolean isEscape = false;
		while (!queue.isEmpty() && !isEscape) {
			Monkey cur = queue.poll();
			if (cur.cnt < K) {
				for (int dir = 0; dir < 8; dir++) {
					int nx = cur.x + hx[dir];
					int ny = cur.y + hy[dir];
					if (isOut(nx, ny))
						continue;
					if (map[nx][ny] == 1)
						continue;
					if (nx == H - 1 && ny == W - 1) {
						dist[cur.cnt+1][nx][ny] = dist[cur.cnt][cur.x][cur.y] + 1;
						isEscape = true;
						break;
					}
//					if (dist[cur.cnt+1][nx][ny] != 0)	continue;
					if (dist[cur.cnt+1][nx][ny] > dist[cur.cnt][cur.x][cur.y] + 1) {
						queue.offer(new Monkey(nx, ny, cur.cnt + 1));
						dist[cur.cnt+1][nx][ny] = dist[cur.cnt][cur.x][cur.y] + 1;
				}
				}
			}
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isOut(nx, ny))
					continue;
				if (map[nx][ny] == 1)
					continue;
				if (nx == H - 1 && ny == W - 1) {
					dist[cur.cnt][nx][ny] = dist[cur.cnt][cur.x][cur.y] + 1;
					isEscape = true;
					break;
				}
//				if (dist[cur.cnt][nx][ny] != 0)	continue;
				if (dist[cur.cnt][nx][ny] > dist[cur.cnt][cur.x][cur.y] + 1) {
					queue.offer(new Monkey(nx, ny, cur.cnt));
					dist[cur.cnt][nx][ny] = dist[cur.cnt][cur.x][cur.y] + 1;
				}
			}
		}
		if (!isEscape) {
			res = -1;
			return;
		}
		for (int i=0; i<=K; i++) {
			res = Math.min(res, dist[i][H-1][W-1]);
		}
		res -= 1;
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= H || y < 0 || y >= W;
	}
}