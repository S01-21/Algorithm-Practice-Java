import java.util.*;
import java.io.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static int[][] water;
	static int[][] hedgehog;
	static Pair start;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Pair> waterQ;
	static int res;

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
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		water = new int[R][C]; // 물 이동 체크
		hedgehog = new int[R][C]; // 고슴도치 이동 체크
		waterQ = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			Arrays.fill(water[i], -1);
		}
		for (int i = 0; i < R; i++) {
			Arrays.fill(hedgehog[i], -1);
		}

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'S') {
					start = new Pair(i, j);
				} else if (map[i][j] == '*') {
					waterQ.offer(new Pair(i, j));
					water[i][j] = 0;
				}
			}
		}

		res = 0;
		bfsW();
		bfsH(start.x, start.y);

		if (res == 0) {
			sb.append("KAKTUS");
		} else {
			sb.append(res);
		}
		System.out.println(sb);
	}

	private static void bfsW() {
		while (!waterQ.isEmpty()) {
			Pair cur = waterQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isOut(nx, ny))
					continue;
				if (water[nx][ny] >= 0)
					continue;
				if (map[nx][ny] == '.') {
					water[nx][ny] = water[cur.x][cur.y] + 1;
					waterQ.offer(new Pair(nx, ny));
				}
			}
		}
	}

	private static void bfsH(int x, int y) {
		Queue<Pair> queue = new ArrayDeque<>();
		hedgehog[x][y] = 0;
		queue.offer(new Pair(x, y));

		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if (isOut(nx, ny))
					continue;
				if (hedgehog[nx][ny] >= 0)
					continue;
				if (map[nx][ny] == 'D') {
					res = hedgehog[cur.x][cur.y] + 1;
					return;
				}
				if (map[nx][ny] == '.') {
					if (water[nx][ny] <= hedgehog[cur.x][cur.y] + 1 && water[nx][ny] != -1)
						continue;
					hedgehog[nx][ny] = hedgehog[cur.x][cur.y] + 1;
					queue.offer(new Pair(nx, ny));
				}
			}
		}
	}

	private static boolean isOut(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}
}