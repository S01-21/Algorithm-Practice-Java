import java.util.*;
import java.io.*;

public class Main {
	private static StringBuilder sb = new StringBuilder();
	static int R, C;
	static char[][] board;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상-하-좌-우
	static int res; // 말이 지날 수 있는 최대 칸 수 (결과)
	static boolean[] apb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		res = Integer.MIN_VALUE;
		apb = new boolean[26];
		func(0, 0, 1);
		System.out.println(res);
	}

	private static void func(int x, int y, int cnt) {
		apb[board[x][y] - 'A'] = true;
		res = Math.max(res, cnt);
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if (apb[board[nx][ny] - 'A'])	continue;
			func(nx, ny, cnt + 1);
			apb[board[nx][ny]- 'A'] = false;
 			
		}
	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= R || y < 0 || y >= C;
	}

	static class Pair {
		int x, y;
		int dist;

		public Pair(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

	}
}