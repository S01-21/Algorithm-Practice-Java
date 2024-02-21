import java.util.*;
import java.io.*;

// 디저트 카페 
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] map;
	static int[][] deltas = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } }; // 우하향-좌하향-좌상향-우상향
	static int res; // 디저트를 가장 많이 먹을 때의 디저트 수 (결과)
	static int maxSum; // 한 지점에서 만들 수 있는 디저트 수 최대 합
	static boolean[][] vis;
	static boolean[] check; // 이전에 나온 숫자 방문 못하도록

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			res = Integer.MIN_VALUE;
			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					vis = new boolean[N][N];
					check = new boolean[101];
					check[map[i][j]] = true;
					vis[i][j] = true;
					dfs(i, j, i, j, 0, 0);
				}
			}

			if (res == Integer.MIN_VALUE) {
				res = -1;
			}
			sb.append(res).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int x, int y, int initX, int initY, int prevDir, int sum) {
		for (int dir = prevDir; dir < 4; dir++) {
			int nx = x + deltas[dir][0];
			int ny = y + deltas[dir][1];
			if (isOut(nx, ny))	continue;
			if ((prevDir % 2 == dir % 2) && (prevDir != dir))	continue;
			if (dir == 1 && ny == 0)	continue;
			if (nx == initX && ny == initY) { // 한바퀴 돌고 원래 자리 도착
				res = Math.max(res, sum + 1);
				return;
			}
			if (!check[map[nx][ny]] && !vis[nx][ny]) {
				check[map[nx][ny]] = true;
				vis[nx][ny] = true;
				dfs(nx, ny, initX, initY, dir, sum + 1);
				check[map[nx][ny]] = false;
				vis[nx][ny] = false;
			}
		}

	}

	static boolean isOut(int x, int y) {
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}