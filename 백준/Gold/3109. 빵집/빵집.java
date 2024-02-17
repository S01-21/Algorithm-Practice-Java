import java.util.*;
import java.io.*;

// 빵집 
public class Main {
	static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] map;
	static int[][] deltas = { { -1, 1 }, { 0, 1 }, { 1, 1 } }; // 우상-우-우하
	static int res; // 놓을 수 있는 파이프라인 최대 개수 (결과)
	static boolean[][] vis;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		vis = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			flag = false;
			func(i, 0);
		}
		System.out.println(res);
	}

	private static void func(int x, int y) {
		if (flag)	return;
		vis[x][y] = true;

		for (int dir = 0; dir < 3; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny)) continue;
			if (vis[nx][ny])	continue;
			if (ny == C - 1) { // 빵집 도달
				res++;
				flag = true;
				return;
			}
			if (map[nx][ny] == 'x') { // 벽이면 패스
				continue;
			}
			func(nx, ny);
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}

	static class Pair {
		int x, y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}